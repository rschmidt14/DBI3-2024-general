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
    private static String FIND_USERS = "SELECT username, passwordHash FROM db_user WHERE username = '$1'";

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

    public User findUserSingleResult(String userName)
    {
        Connection con = ConnectionFactory.getConnection();
        try (Statement stmt = con.createStatement())
        {
            ResultSet resultSet = stmt.executeQuery(FIND_USERS);
            while (resultSet.next())
            {
                String foundUserName = resultSet.getString("username");
                if (userName.equals(foundUserName))
                {
                    String passwordHash = resultSet.getString("passwordHash");
                    User foundUser = new User(userName);
                    foundUser.setPasswordHash(passwordHash);
                    return foundUser;
                }
            }
            resultSet.close();
            con.close();
        }
        catch (SQLException e)
        {
            LOG.error("SQL exception when trying to find user: " + userName, e);
        }
        return null;
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

