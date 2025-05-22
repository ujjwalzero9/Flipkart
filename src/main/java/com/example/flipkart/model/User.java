package com.example.flipkart.model;

/**
 * Represents a user/customer of the platform.
 */
public class User {
    private final String userId;
    private final String fullName;

    public User(String userId, String fullName) {
        this.userId   = userId;
        this.fullName = fullName;
    }

    public String getUserId()   { return userId; }
    public String getFullName() { return fullName; }

    @Override
    public String toString() {
        return String.format("User[%s: %s]", userId, fullName);
    }
}
