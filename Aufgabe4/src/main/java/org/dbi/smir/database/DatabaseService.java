package org.dbi.smir.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.dbi.smir.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseService
{
    private static String CREATE_TABLE_USER =
            "CREATE TABLE IF NOT EXISTS db_user ( username varchar, passwordHash varchar )";

    private static String FIND_USERS = "SELECT username, passwordHash FROM db_user";

    private static String INSERT_USER = "INSERT INTO db_user (username, passwordHash) VALUES ('$1', '$2')";




    private static final Logger LOG = LoggerFactory.getLogger(DatabaseService.class);

    public DatabaseService()
    { }

    public void initializeDatabase() {
        Connection con = ConnectionFactory.getConnection();
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(CREATE_TABLE_USER);
            con.commit();
            con.close();
        }
        catch (SQLException e) {
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

    public void insertUser(User user)
    {
        Connection con = ConnectionFactory.getConnection();
        String insert_user = INSERT_USER.replace("$1", user.getUsername()).replace("$2", user.getPasswordHash());
        try (Statement stmt = con.createStatement())
        {
            stmt.executeUpdate(insert_user);
            con.commit();
            con.close();
        }
        catch (
            SQLException e)
        {
            LOG.error("SQL exception during user insertion; "+ user, e);
        }
    }
}

