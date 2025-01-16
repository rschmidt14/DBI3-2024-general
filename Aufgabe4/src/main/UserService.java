package Aufgabe4.src.main;

import java.sql.*;

public class UserService {

    public boolean registerUser(String username, String passwordHash) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO users (username, password_hash) VALUES ('" + username + "', '" + passwordHash + "')";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.out.println("Could not close resources!");
            }
        }
    }

    public User loginUser(String username, String passwordHash) {
        String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password_hash = '" + passwordHash + "'";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return new User(rs.getString("username"), rs.getString("password_hash"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.out.println("fail");
            }
        }
    }
}
