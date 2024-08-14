package com.ofss.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofss.main.domain.Account;
import com.ofss.main.domain.Customer;
import com.ofss.main.domain.Login;
import com.ofss.main.repository.CustomerRepository;
import com.ofss.main.repository.LoginRepository;
import com.ofss.main.repository.AccountRepository; // Ensure this repository exists
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
    private LoginRepository loginRepository; 

    @Autowired
    private AccountRepository accountRepository; // New repository

    @Override
    public void registerCustomer(Customer customer, Login login, String accountType) {
        try {
            if (login == null || customer == null || accountType == null) {
                throw new IllegalArgumentException("Login, Customer or Account Type must not be null");
            }

            logger.info("Saving login details: {}", login);
            Login savedLogin = loginRepository.save(login);

            customer.setLoginId(savedLogin.getLoginId());
            logger.info("Saving customer: {}", customer);
            Customer savedCustomer = customerRepository.save(customer);

            logger.info("Saving account details for account type: {}", accountType);
            Account account = new Account(); // Assuming Account is a class representing account details
            account.setCustomerId(savedCustomer.getCustomerId());
            account.setAccountType(accountType);
            account.setBalance(10000.0); // Default balance; adjust as needed
            accountRepository.save(account);

            logger.info("Customer, login, and account details saved successfully.");
        } catch (Exception e) {
            logger.error("Error saving customer, login, or account details: {}", e.getMessage());
            throw e;
        }
    }
}
