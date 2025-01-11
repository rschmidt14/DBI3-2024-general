package org.dbi.hess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/your_database";
    private static final String USER = "your_user";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Verbindung erfolgreich hergestellt");
        } catch (SQLException e) {
            System.err.println("Fehler beim Verbinden zur Datenbank: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

