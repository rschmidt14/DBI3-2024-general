package org.dbi.Manta_Maka;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    private final Connection connection;

    public UserService(Connection connection) {
        this.connection = connection;
    }

    // Methode zum Speichern eines Benutzers
    public void saveUser(User user) throws SQLException {
        // SICHERER Code
        /*String sql = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPasswordHash());
            statement.executeUpdate();
        }*/
        // UNSICHERER Code
        String sql = "INSERT INTO users (username, password_hash) VALUES ('"
                + user.getUsername() + "', '"
                + user.getPasswordHash() + "')";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
        // Ende UNSICHERE Methode
    }

    // Methode zum Abrufen eines Benutzers anhand des Benutzernamens
    public User findUserByUsername(String username) throws SQLException {
        String sql = "SELECT username, password_hash FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String passwordHash = resultSet.getString("password_hash");
                    return new User(username, passwordHash);
                }
            }
        }
        return null; // Benutzer nicht gefunden
    }
}
