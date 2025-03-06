package org.dbi.hess;

public class User {
    private String username;
    private String passwordHash;

    public User(String username, String password, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return this.username;

    }

    public String getPasswordHash() {
        return this.passwordHash;
    }
}

