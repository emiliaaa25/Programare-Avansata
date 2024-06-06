package org.example;

import java.sql.*;
import java.util.Objects;

public class BookDAO {
    static int id = 0;
    static int idBookAuthor = 0;
    static int idBookGenre = 0;

    public void create(int year, String name, String[] author, String[] genre, String language) throws SQLException {
        if (findByName(name) != null) {
            System.out.println("Cartea exista deja in baza de date");
            return;
        }

        id++;
        Connection con = DBCPDataSource.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into books (id, name, year, language, authors, genres) values (?, ?, ?, ?, ?, ?)")) {

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

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, year);
            pstmt.setString(4, language);
            pstmt.setString(5, authorsArray.toString());
            pstmt.setString(6, genresArray.toString());
            pstmt.executeUpdate();

        }

        PreparedStatement pstmt = null;
        try {
            for (var i : author) {
                AuthorDAO authors = new AuthorDAO();
                if (authors.findByName(i) == null) {
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
                GenreDAO genres = new GenreDAO();
                if (genres.findByName(i) == null) {
                    genres.create(i);
                }

                pstmt = con.prepareStatement("insert into book_genre (id, book_id, genre_id) values (?, ?, ?)");
                pstmt.setInt(1, ++idBookGenre);
                pstmt.setInt(2, id);
                pstmt.setInt(3, genres.findByName(i));

                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println(e);
            System.out.println("Eroare la adaugarea in book_genre");
        }
        if (pstmt != null) pstmt.close();
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select id from books where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }

    }

    public String findById(int id) throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select name from books where id='" + id + "'")) {
            return rs.next() ? rs.getString(1) : null;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    public void findAll() throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select * from books")) {
            while (rs.next()) {
                int id_book = rs.getInt(1);
                System.out.print("ID: " + id_book + " TITLU_CARTE: " + rs.getString(2) + "AUTORI: " + rs.getString(3) + " AN: " + rs.getInt(4) + " LIMBA: " + rs.getString(5) + "GENURI: " + rs.getString(6) + "\n");
            }
        } catch (Exception e) {
            System.err.println(e);
            System.out.println("ERROARE FINDALL: Nu exista carti");
        }
    }
}
