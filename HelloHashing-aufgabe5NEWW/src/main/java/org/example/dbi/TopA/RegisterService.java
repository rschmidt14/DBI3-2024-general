package org.example.dbi.TopA;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class RegisterService {
    private final DataBaseService databaseService;

    public RegisterService(DataBaseService databaseService) {
        this.databaseService = databaseService;
    }

    // Unsichere
    public boolean registerUser(String username, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        // UNSICHERE
        String query = "INSERT INTO users (username, passwordHash) VALUES ('" + username + "', '" + hashedPassword + "')";

        try (Connection conn = databaseService.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query); // Führe die unsichere Abfrage aus
            return true;
        } catch (SQLException e) {
            System.out.println("Registrierung fehlgeschlagen: " + e.getMessage());
            return false;
        }
    }
}
