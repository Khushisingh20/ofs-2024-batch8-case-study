package com.ofss.main.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDetailRepository {

    private Connection connection;

        public LoginDetailRepository(Connection connection) {
            this.connection = connection;
        }
    
        public int getAttempts(String username) throws SQLException {
            String sql = "SELECT attempts FROM login_detail WHERE username = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("attempts");
                    }
                }
            }
            return 0;
        }
    
        public void incrementAttempts(String username) throws SQLException {
            String sql = "UPDATE login_detail SET attempts = attempts + 1 WHERE username = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.executeUpdate();
            }
        }
    
        public void resetAttempts(String username) throws SQLException {
            String sql = "UPDATE login_detail SET attempts = 0 WHERE username = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.executeUpdate();
            }
        }
        
        public boolean validateLogin(String username, String password) throws SQLException {
            String sql = "SELECT customer_id FROM login_detail WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return true;
                    }
                }
            }
            return false;
        }
        
        public void lockAccount(String username) throws SQLException {
            String sql = "UPDATE customer_detail SET locked_status = 1 WHERE customer_id = (SELECT customer_id FROM login_detail WHERE username = ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.executeUpdate();
            }
        }
    
        public void unlockAccount(String username) throws SQLException {
            String sql = "UPDATE customer_detail SET locked_status = 0 WHERE customer_id = (SELECT customer_id FROM login_detail WHERE username = ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.executeUpdate();
            }
        }
    
       

        
    }