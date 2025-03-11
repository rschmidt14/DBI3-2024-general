package org.example.dbi.TopA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class DataBaseService {
    private static final String URL = "jdbc:postgresql://localhost:5432/DBI"; //DB
    private static final String USER = "postgres";  //  Benutzername
    private static final String PASSWORD = "0000";  //  Passwort

    static {
        try {
            Class.forName("org.postgresql.Driver"); // Treiber
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL-Treiber nicht gefunden!", e);
        }
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL-Treiber nicht gefunden!", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public void initializeDatabase() {

        try (Connection conn = getConnection()) {

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler bei der Verbindung zur Datenbank.", e);
        }
    }
}
