package com.ofss.main.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.ofss.main.domain.Customer;
import com.ofss.main.service.CustomerService;
import com.ofss.main.domain.ResponseMessage; // Ensure this exists

@RestController
@RequestMapping("/inb_bank")
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/register")
	public ResponseEntity<?> registerCustomer(@RequestBody Customer customer) {
		try {
			customerService.registerCustomer(customer);
			return ResponseEntity.ok().body(new ResponseMessage("Registration Successful"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Registration Failed: " + e.getMessage()));
		}
	}
}
