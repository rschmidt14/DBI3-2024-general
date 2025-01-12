package org.dbi.Dizdarevic.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import org.dbi.Dizdarevic.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseService
{
    private static String CREATE_TABLE_USER =
        "CREATE TABLE IF NOT EXISTS db_user ( username varchar, passwordHash varchar )";
    //todo - complete sql statement CHECK
    private static String FIND_USERS = "SELECT username, passwordHash FROM db_user WHERE username = ?";


    private static String INSERT_USER = "insert into db_user values ('$1', '$2')";

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseService.class);

    public DatabaseService()
    { }

    public void initializeDatabase()
    {
        Connection con = ConnectionFactory.getConnection();
        try (Statement stmt = con.createStatement())
        {
            stmt.executeUpdate(CREATE_TABLE_USER);
            con.commit();
            con.close();
        }
        catch (
            SQLException e)
        {
            LOG.error("SQL exception during database initializaton", e);
        }
    }

    public User findUserSingleResult(String userName) {
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(FIND_USERS)) {
    
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                String foundUserName = rs.getString("username");
                String passwordHash = rs.getString("passwordHash");
    
                User foundUser = new User(foundUserName);
                foundUser.setPasswordHash(passwordHash);
                return foundUser;
            }
        } catch (SQLException e) {
            System.err.println("SQL exception during user lookup for username: " + userName);
            e.printStackTrace();
        }
        return null; // User not found
    }
    

    public void insertUser(User user) {
        String insertUserSQL = "INSERT INTO db_user (username, passwordHash) VALUES (?, ?)";
    
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pstmt = con.prepareStatement(insertUserSQL)) {
    
            // Set the actual values for the placeholders
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPasswordHash());
    
            // Execute the SQL query
            pstmt.executeUpdate();
            con.commit(); // Save changes to the database
            System.out.println("User '" + user.getUsername() + "' registered successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + user.getUsername());
            e.printStackTrace();
        }
    }
}   

