package org.example.dao;

import org.example.DBCPDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDAOFactory implements DAOFactory {
    @Override
    public AuthorDAO createAuthorDAO() throws SQLException {
        Connection connection = DBCPDataSource.getConnection();
        return new JdbcAuthorDAO(connection);
    }

    @Override
    public BookDAO createBookDAO() throws SQLException {
        Connection connection = DBCPDataSource.getConnection();

        return new JdbcBookDAO(connection);
    }

    @Override
    public GenreDAO createGenreDAO() throws SQLException {
        Connection connection = DBCPDataSource.getConnection();

        return new JdbcGenreDAO(connection);
    }

    @Override
    public PublishingHouseDAO createPublishingHouseDAO() throws SQLException {
        Connection connection = DBCPDataSource.getConnection();

        return new JdbcPublishingHouseDAO(connection);
    }
}