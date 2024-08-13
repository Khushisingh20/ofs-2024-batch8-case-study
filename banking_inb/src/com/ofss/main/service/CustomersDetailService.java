package com.ofss.main.service;


import com.ofss.main.repository.CustomersDetailRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomersDetailService {

    private CustomersDetailRepository customersDetailRepository;

    public CustomersDetailService(Connection connection) {
        this.customersDetailRepository = new CustomersDetailRepository(connection);
    }

    public boolean registerNewCustomer(String firstName, String lastName, String dob, String address, String email, String phoneNumber) {
        try {
            return customersDetailRepository.addCustomer(firstName, lastName, dob, address, email, phoneNumber);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}