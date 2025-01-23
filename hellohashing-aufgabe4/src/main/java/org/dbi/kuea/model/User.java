package org.dbi.kuea.model;

public class User {
    private String username;
    private String password;
    private String passwordHash;

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String toString() {
        return "\nusername: " + username + "\npassword: " + password + "\nhash: " + passwordHash;
    }

    public boolean equals(User user) {
        if (this.username.equals(user.username) &&
                this.passwordHash.equals(user.getPasswordHash()))
            return true;
        return false;
    }
}
