package org.example.dao;

import java.sql.SQLException;

public interface DAOFactory {
    AuthorDAO createAuthorDAO() throws SQLException;

    BookDAO createBookDAO() throws SQLException;

    GenreDAO createGenreDAO() throws SQLException;

    PublishingHouseDAO createPublishingHouseDAO() throws SQLException;

}
