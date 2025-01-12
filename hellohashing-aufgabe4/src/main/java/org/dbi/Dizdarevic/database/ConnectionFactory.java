package org.dbi.Dizdarevic.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionFactory {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionFactory.class);
    private static final String PROPERTIES_FILE_NAME = "dbi.properties";

    public static Connection getConnection() {
        Connection con = null;
        Properties props = new Properties();

        try (InputStream is = ConnectionFactory.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            if (is == null) {
                LOG.error("Properties file '{}' not found in classpath.", PROPERTIES_FILE_NAME);
                return null;
            }
            props.load(is);

            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            if (url == null || user == null || password == null) {
                LOG.error("Missing database connection properties in '{}'.", PROPERTIES_FILE_NAME);
                return null;
            }

            con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false); // Enable manual transaction management if needed
            LOG.info("Database connection established successfully.");
        } catch (IOException e) {
            LOG.error("Error loading properties file: {}", PROPERTIES_FILE_NAME, e);
        } catch (SQLException e) {
            LOG.error("Error establishing database connection.", e);
        }

        return con;
    }
}
