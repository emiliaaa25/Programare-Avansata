package org.example.dao;

import org.example.DBCPDataSource;
import org.example.entities.Book;
import org.example.entities.Genre;
import org.example.entities.PublishingHouse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JdbcBookDAO implements BookDAO {
    static int id = 0;
    static int idBookAuthor = 0;
    static int idBookGenre = 0;
    private final Connection connection;

    private static int publishingHouseIdCounter = 0;

    public JdbcBookDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(int year, String name, String[] author, String[] genre, String language, String publishingHouseName) throws SQLException {
        if (findByName(name) != null) {
            System.out.println("Cartea exista deja in baza de date");
            return;
        }

        id++;
        Connection con = DBCPDataSource.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into books (id, name, year, language, publishingHouse, authors, genres) values (?, ?, ?, ?, ?, ?, ?)")) {

            StringBuilder authorsArray = new StringBuilder(author[0]);
            for (var authorsI : author) {
                if (Objects.equals(authorsI, author[0])) continue;
                authorsArray.append(", ").append(authorsI);
            }

            StringBuilder genresArray = new StringBuilder(genre[0]);
            for (var genresI : genre) {
                if (Objects.equals(genresI, genre[0])) continue;
                genresArray.append(", ").append(genresI);
            }

            PublishingHouseDAO publishingHouseDAO = new JdbcPublishingHouseDAO(con);
            Integer publishingHouseId = publishingHouseDAO.findByName(publishingHouseName);
            if (publishingHouseId == null) {
                int newPublishingHouseId = ++publishingHouseIdCounter;
                publishingHouseDAO.create(newPublishingHouseId, publishingHouseName);
                publishingHouseId = newPublishingHouseId;
            }

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, year);
            pstmt.setString(4, language);
            pstmt.setInt(5, publishingHouseId);
            pstmt.setString(6, authorsArray.toString());
            pstmt.setString(7, genresArray.toString());
            pstmt.executeUpdate();
        }

        PreparedStatement pstmt = null;
        try {
            for (var i : author) {
                AuthorDAO authors = new JdbcAuthorDAO(con);
                if (authors.findByName(i) == -1) {
                    authors.create(i);
                }

                pstmt = con.prepareStatement("insert into book_author (id, book_id, author_id) values (?, ?, ?)");
                pstmt.setInt(1, ++idBookAuthor);
                pstmt.setInt(2, id);
                pstmt.setInt(3, authors.findByName(i));
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println(e);
            System.out.println("Eroare la adaugarea in book_author");
        } finally {
            if (pstmt != null) pstmt.close();
        }

        try {
            for (var i : genre) {
                GenreDAO genres = new JdbcGenreDAO(con);
                Integer genreId = genres.findByName(i);
                if (genreId == null) {
                    genres.create(i);
                    genreId = genres.findByName(i);  // Get the id of the newly created Genre
                }

                Genre genreEntity = genres.findById(genreId);  // Use findById() to get the Genre object

                pstmt = con.prepareStatement("insert into book_genre (id, book_id, genre_id) values (?, ?, ?)");
                pstmt.setInt(1, ++idBookGenre);
                pstmt.setInt(2, id);
                pstmt.setInt(3, genreEntity.getId());  // Use getId() to get the id of the Genre
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println(e);
            System.out.println("Eroare la adaugarea in book_genre");
        }

        if (pstmt != null) pstmt.close();
    }

    @Override
    public Integer findByName(String name) throws SQLException {
        String sql = "SELECT id FROM books WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public String findById(int id) throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select name from books where id='" + id + "'")) {
            return rs.next() ? rs.getString(1) : null;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    @Override
    public List<Book> findAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        Connection con = DBCPDataSource.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select * from books")) {
            while (rs.next()) {
                Book book = new Book(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getObject(5, PublishingHouse.class), rs.getString(6));
                books.add(book);
            }
        } catch (Exception e) {
            System.err.println(e);
            System.out.println("ERROARE FINDALL: Nu exista carti");
        }
        return books;
    }
}
