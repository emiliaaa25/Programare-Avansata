package jdbctests;

import org.example.dao.JdbcAuthorDAO;
import org.example.entities.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcAuthorTest {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "student";
    private static final String PASSWORD = "STUDENT";

    private Connection connection=null;
    private JdbcAuthorDAO jdbcAuthorDAO;

    @BeforeEach
    void init() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        jdbcAuthorDAO = new JdbcAuthorDAO(connection);
    }

    @AfterEach
    void cleanup() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void testAuthor() throws SQLException {
        Author author = new Author();
        author.setName("John Doe");
        jdbcAuthorDAO.create("John Doe");
       int id= jdbcAuthorDAO.findByName("John Doe");
        assertEquals(91, id);
    }
}