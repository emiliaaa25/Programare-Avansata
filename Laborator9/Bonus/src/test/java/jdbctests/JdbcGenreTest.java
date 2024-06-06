package jdbctests;

import org.example.dao.JdbcGenreDAO;
import org.example.entities.Genre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcGenreTest {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "student";
    private static final String PASSWORD = "STUDENT";

    private Connection connection = null;
    private JdbcGenreDAO jdbcGenreDAO;

    @BeforeEach
    void init() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        jdbcGenreDAO = new JdbcGenreDAO(connection);
    }

    @AfterEach
    void cleanup() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void testGenre() throws SQLException {
        Genre genre = new Genre();
        genre.setName("Test Genre");
        jdbcGenreDAO.create("Test Genre");
        int id = jdbcGenreDAO.findByName("Test Genre");
        assertEquals(1, id);
    }
}