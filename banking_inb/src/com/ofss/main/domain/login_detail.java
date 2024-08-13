package com.ofss.main.domain;

public class login_detail {

  
    private int customerId;
    private String username;
    private String password;
    private int attempts;

    public login_detail() {
    }

    public login_detail(int customerId, String username, String password) {
        this.customerId = customerId;
        this.username = username;
        this.password = password;
        this.attempts = 0; // Default value
    }

    // Getters and setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    @Override
    public String toString() {
        return "LoginDetail{" +
                "customerId=" + customerId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", attempts=" + attempts +
                '}';
    }
}