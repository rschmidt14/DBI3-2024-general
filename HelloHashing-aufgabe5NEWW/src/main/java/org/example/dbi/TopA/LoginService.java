package org.example.dbi.TopA;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
    private final DataBaseService databaseService;

    public LoginService(DataBaseService databaseService) {
        this.databaseService = databaseService;
    }

    public boolean loginUser(String username, String password) {
        String hashedPassword = String.valueOf(password.hashCode()); // Passwort-Hashing
        String query = "SELECT passwordHash FROM users WHERE username = ? ";

        try (Connection conn = databaseService.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);  // Benutzername
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("passwordHash");  // Gehashte Passwort aus DB holen

                // Vergleicht pw
                if (BCrypt.checkpw(password, storedHash)) {
                    return true;  // Passwort stimmt überein
                } else {
                    System.out.println("Falsches Passwort!");
                    return false;  // Passwort stimmt nicht überein
                }
            } else {
                System.out.println("Benutzername nicht gefunden!");
                return false;  // Benutzername nicht gefunden
            }
        }
            catch (SQLException e) {
            System.out.println("Login fehlgeschlagen: " + e.getMessage());
            return false;
        }
    }
}
