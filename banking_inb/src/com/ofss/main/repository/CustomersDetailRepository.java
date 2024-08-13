package com.ofss.main.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomersDetailRepository {

    private Connection connection;

    public CustomersDetailRepository(Connection connection) {
        this.connection = connection;
    }

    public boolean addCustomer(String firstName, String lastName, String dob, String address, String email, String phoneNumber) throws SQLException {
        String sql = "INSERT INTO customer_detail (first_name, last_name, date_of_birth, address, email, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, dob);
            stmt.setString(4, address);
            stmt.setString(5, email);
            stmt.setString(6, phoneNumber);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }

}