package com.ofss.main.service;
import com.ofss.main.repository.LoginDetailRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDetailService {
    private LoginDetailRepository loginDetailRepository;
    private Connection conn; // Include a connection field

    public LoginDetailService(Connection connection) {
        this.loginDetailRepository = new LoginDetailRepository(connection);
        this.conn = connection;   // Assign the connection
    
    }
    public boolean validateLogin(String username, String password) {
        try {
            int attempts = loginDetailRepository.getAttempts(username);
            
            if (attempts >= 3) {
                System.out.println("Account locked due to multiple failed attempts.");
                return false;
            }
            
            boolean loggedIn = loginDetailRepository.validateLogin(username, password);
            
            if (loggedIn) {
                loginDetailRepository.resetAttempts(username); // Reset attempts on successful login
            } else {
                loginDetailRepository.incrementAttempts(username); // Increment attempts on failed login
            }
            
            return loggedIn;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 

    public void resetAttempts(String username) {
        try {
            loginDetailRepository.resetAttempts(username);
            System.out.println("Attempts reset for user: " + username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void lockAccount(String username) {
        try {
            loginDetailRepository.lockAccount(username);
            System.out.println("Account locked for user: " + username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unlockAccount(String username) {
        try {
            loginDetailRepository.unlockAccount(username);
            System.out.println("Account unlocked for user: " + username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isAccountLocked(String username) {
        try {
            int lockedStatus = getLockedStatus(username);
            return lockedStatus == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return true; // Return true to indicate error (conservative approach)
        }
    }

    private int getLockedStatus(String username) throws SQLException {
        String sql = "SELECT locked_status FROM customer_detail WHERE customer_id = (SELECT customer_id FROM login_detail WHERE username = ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("locked_status");
                }
            }
        }
        return 1; // Default to locked if not found (conservative approach)
    }
}