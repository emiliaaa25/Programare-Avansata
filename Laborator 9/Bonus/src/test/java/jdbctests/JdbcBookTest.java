package jdbctests;

import org.example.dao.JdbcBookDAO;
import org.example.entities.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcBookTest {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "student";
    private static final String PASSWORD = "STUDENT";

    private Connection connection=null;
    private JdbcBookDAO jdbcBookDAO;

    @BeforeEach
    void init() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        jdbcBookDAO = new JdbcBookDAO(connection);
    }

    @AfterEach
    void cleanup() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void findBook() throws SQLException {
        Book book = new Book();
        book.setTitle("Test Book");
        jdbcBookDAO.create(1991, "Test Book", new String[]{"Test Author"}, new String[]{"Test Genre"}, "Test Publishing House", "Test Language");
        int id = jdbcBookDAO.findByName("Test Book");
        String name = jdbcBookDAO.findById(112);
        assertEquals(112, id);
        assertEquals("Test Book", name);
    }
}