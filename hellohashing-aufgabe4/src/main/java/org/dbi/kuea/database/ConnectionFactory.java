package org.dbi.kuea.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionFactory
{
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionFactory.class);
    private static final String PROPERTIES_FILE_NAME = "dbi.properties";

    private ConnectionFactory dataSourceFactory;

    public static Connection getConnection()
    {
        Connection con = null;
        Properties props = new Properties();
        FileInputStream fis = null;
        try
        {
            InputStream is = ConnectionFactory.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
            props.load(is);
        }
        catch (IOException ioe)
        {
            LOG.error("Unable to load properties file: " + PROPERTIES_FILE_NAME);
        }

        try
        {
            con = DriverManager.getConnection(props.getProperty("url"), props);
            con.setAutoCommit(false);
            LOG.info("Connection: " + con);
        }
        catch(SQLException e)
        {
            LOG.error("Cannot establish connection", e);
        }
        return con;
    }
}