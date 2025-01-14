package org.dbi.Jani;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:postgresql://localhost:5433/hash_calculator";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password-------------------";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            System.out.println("Verbindung erfolgreich hergestellt!");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Verbindung fehlgeschlagen: " + e.getMessage());
        }
    }
}

