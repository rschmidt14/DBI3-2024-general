package org.dbi.hess;

import java.sql.*;

public class UserService {

    public void saveUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password_hash) VALUES ('" + user.getUsername() + "', '" + user.getPasswordHash() + "')";
        try (Connection conn = ConnectionFactory.getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = '" + username + "'";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return new User(rs.getString("username"), null, rs.getString("password_hash"));
            }
        }
        return null;
    }


}
