package org.dbi.hess;

public class User {
    private String username;
    private String password;
    private String passwordHash;

    public User(String username, String password, String passwordHash) {
        this.username = username;
        this.password = password;
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return this.username;

    }
    public String getPasswordHash() {
        return this.passwordHash;
    }
}

