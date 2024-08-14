package com.ofss.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ofss.main.domain.Customer;
import com.ofss.main.domain.Login;
import com.ofss.main.repository.CustomerRepository;
import com.ofss.main.repository.LoginRepository; // Ensure this repository exists
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoginRepository loginRepository; // Ensure this is the correct repository for Login

    @Override
    public void registerCustomer(Customer customer, Login login) {
        try {
            if (login == null || customer == null) {
                throw new IllegalArgumentException("Login or Customer must not be null");
            }

            logger.info("Saving login details: {}", login);
            Login savedLogin = loginRepository.save(login);

            customer.setLoginId(savedLogin.getLoginId());
            logger.info("Saving customer: {}", customer);
            customerRepository.save(customer);
            
            logger.info("Customer and login details saved successfully.");
        } catch (Exception e) {
            logger.error("Error saving customer or login details: {}", e.getMessage());
            throw e;
        }
    }
}
