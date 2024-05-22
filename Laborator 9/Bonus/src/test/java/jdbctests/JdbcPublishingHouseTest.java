package jdbctests;

import org.example.dao.JdbcPublishingHouseDAO;
import org.example.entities.PublishingHouse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcPublishingHouseTest {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "student";
    private static final String PASSWORD = "STUDENT";

    private Connection connection = null;
    private JdbcPublishingHouseDAO jdbcPublishingHouseDAO;

    @BeforeEach
    void init() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        jdbcPublishingHouseDAO = new JdbcPublishingHouseDAO(connection);
    }

    @AfterEach
    void cleanup() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void testPublishingHouse() throws SQLException {
        PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouse.setName("Test Publishing House");
        jdbcPublishingHouseDAO.create(1, "Test Publishing House");
        int id = jdbcPublishingHouseDAO.findByName("Test Publishing House");
        assertEquals(1, id);
    }
}