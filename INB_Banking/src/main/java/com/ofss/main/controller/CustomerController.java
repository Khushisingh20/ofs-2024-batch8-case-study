package com.ofss.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ofss.main.domain.Customer;
import com.ofss.main.domain.Login;
import com.ofss.main.domain.RegisterRequest; // Ensure this import is correct
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
            // Extract customer and login details from the request
            Customer customer = registerRequest.getCustomer();
            Login login = registerRequest.getLogin();

            // Check if either customer or login is null
            if (customer == null || login == null) {
                return ResponseEntity.badRequest().body(new ResponseMessage("Customer or Login is missing"));
            }

            logger.info("Registering customer and login details.");
            customerService.registerCustomer(customer, login);

            return ResponseEntity.ok().body(new ResponseMessage("Registration Successful"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Registration Failed: " + e.getMessage()));
        }
    }
}

