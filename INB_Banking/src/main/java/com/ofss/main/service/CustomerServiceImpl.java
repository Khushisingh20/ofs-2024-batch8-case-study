package com.ofss.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ofss.main.domain.Customer;
import com.ofss.main.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void registerCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
