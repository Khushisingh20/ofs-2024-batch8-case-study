package com.ofss.main.service;

import com.ofss.main.domain.Customer;
import com.ofss.main.domain.Login;

public interface CustomerService {
    void registerCustomer(Customer customer, Login login, String accountType);
}
