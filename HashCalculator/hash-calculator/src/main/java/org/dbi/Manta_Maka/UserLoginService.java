package org.dbi.Manta_Maka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginService {

    public static boolean login(String username, String password) {
        String query = "SELECT password_hash FROM users WHERE username = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPassword = resultSet.getString("password_hash");
                    return BCryptHelper.checkPassword(password, hashedPassword);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Optional: Log the error or rethrow it
        }
        return false; // Login failed
    }
}
