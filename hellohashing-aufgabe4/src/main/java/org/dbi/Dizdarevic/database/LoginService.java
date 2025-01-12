package org.dbi.Dizdarevic.database;
//Hello hashing namen aendern
import org.dbi.Dizdarevic.HelloHashing;
import org.dbi.Dizdarevic.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginService
{
    private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);
    private DatabaseService databaseService;

    public LoginService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public boolean isUserExisting(String userName) {
        User foundUser = databaseService.findUserSingleResult(userName);
        if(foundUser != null)
            return true;
        return false;
    }

    public void registerUser(User user)
    {
        if(isUserExisting(user.getUsername())) {
            LOG.info("User name "+user.getUsername()+ " is already taken");
        } else {
            databaseService.insertUser(user);
            LOG.info("User: "+user.getUsername()+" registered");
        }
    }

    public boolean loginUser(User user) {
        User foundUser = databaseService.findUserSingleResult(user.getUsername());
    
        if (foundUser != null) {
            System.out.println("Stored hash: " + foundUser.getPasswordHash());
            System.out.println("Entered hash: " + user.getPasswordHash());
    
            if (user.getPasswordHash().equals(foundUser.getPasswordHash())) {
                System.out.println("User: " + user.getUsername() + " logged in successfully.");
                return true;
            } else {
                System.out.println("Hashes do not match. Login failed.");
                return false;
            }
        } else {
            System.out.println("User not found: " + user.getUsername());
            return false;
        }
    }

    public boolean validateUser(User user) {
        if(user != null && user.getUsername() != null && user.getPassword() != null
            && user.getUsername().length() > 0 && user.getPassword().length() > 0)
            return true;
        return false;
    }
}
