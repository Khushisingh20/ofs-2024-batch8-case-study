package com.ofss.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ofss.main.domain.Customer;
import com.ofss.main.domain.Login;
import com.ofss.main.domain.RegisterRequest;
import com.ofss.main.service.CustomerService;
import com.ofss.main.domain.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/inb_bank")
@CrossOrigin("*")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody RegisterRequest registerRequest) {
        try {
            // Extract customer, login details, and account type from the request
            Customer customer = registerRequest.getCustomer();
            Login login = registerRequest.getLogin();
            String accountType = registerRequest.getAccountType();

            // Check if either customer, login, or accountType is null
            if (customer == null || login == null || accountType == null) {
                return ResponseEntity.badRequest().body(new ResponseMessage("Customer, Login, or Account Type is missing"));
            }

            logger.info("Registering customer, login details, and account type.");
            customerService.registerCustomer(customer, login, accountType);

            return ResponseEntity.ok().body(new ResponseMessage("Registration Successful"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Registration Failed: " + e.getMessage()));
        }
    }
}
