package org.dbi.kuea.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dbi.kuea.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseService {
    private static final String CREATE_TABLE_USER =
            "CREATE TABLE IF NOT EXISTS db_user (username VARCHAR PRIMARY KEY, passwordHash VARCHAR)";

    private static final String FIND_USERS = "SELECT username, passwordHash FROM db_user WHERE username = ?";

    private static final String INSERT_USER = "INSERT INTO db_user VALUES (? ,?)";

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseService.class);

    public DatabaseService() {
        initializeDatabase();
    }

    public void initializeDatabase() {
        try (Connection con = org.dbi.kuea.database.ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(CREATE_TABLE_USER)) {
            stmt.executeUpdate();
            con.commit();
            LOG.info("Database initialized");
        } catch (SQLException e) {
            LOG.error("SQL exception during database initialization", e);
        }
    }

    public User findUserSingleResult(String userName) {
        try (Connection con = org.dbi.kuea.database.ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(FIND_USERS)) {
            stmt.setString(1, userName);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String passwordHash = resultSet.getString("passwordHash");
                User foundUser = new User(username);
                foundUser.setPasswordHash(passwordHash);
                return foundUser;
            }
        } catch (SQLException e) {
            LOG.error("SQL exception when trying to find user: " + userName, e);
        }
        return null;
    }

    public void insertUser(User user) {
        try (Connection con = org.dbi.kuea.database.ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_USER)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            stmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            LOG.error("SQL exception during user insertion: " + user, e);
        }
    }
}
