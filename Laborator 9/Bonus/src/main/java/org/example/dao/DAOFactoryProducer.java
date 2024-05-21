package org.example.dao;

import org.example.repositories.AbstractRepository;

import java.io.InputStream;
import java.util.Properties;

public class DAOFactoryProducer {
    private static final String PROPERTIES_FILE = "config.properties";

    public static DAOFactory getDAOFactory() {
        Properties properties = new Properties();
        try (InputStream input = DAOFactoryProducer.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new RuntimeException("Unable to find " + PROPERTIES_FILE);
            }
            properties.load(input);
            String factoryType = properties.getProperty("dao.factory");

            if ("JDBC".equalsIgnoreCase(factoryType)) {
                return new JdbcDAOFactory();
            } else if ("JPA".equalsIgnoreCase(factoryType)) {
                getRepositoryFactory();
                return null;
            } else {
                throw new RuntimeException("Invalid factory type specified in " + PROPERTIES_FILE);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading " + PROPERTIES_FILE, e);
        }
    }

    public static RepositoryFactory getRepositoryFactory() {
        Properties properties = new Properties();
        try (InputStream input = DAOFactoryProducer.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new RuntimeException("Unable to find " + PROPERTIES_FILE);
            }
            properties.load(input);
            String factoryType = properties.getProperty("dao.factory");

            if ("JDBC".equalsIgnoreCase(factoryType)) {
                getDAOFactory();
                return null;
            } else if ("JPA".equalsIgnoreCase(factoryType)) {
                return new JpaRepositoryFactory();
            } else {
                throw new RuntimeException("Invalid factory type specified in " + PROPERTIES_FILE);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading " + PROPERTIES_FILE, e);
        }
    }
}


