package com.ofss.main.controller;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.InputSource;

import com.ofss.main.domain.Login;
import com.ofss.main.service.LoginService;

@RestController
@RequestMapping("/inb_bank")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> validateLogin(@RequestBody String xmlInput) {
        try {
            // Parse the XML input and extract login details
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource source = new InputSource(new StringReader(xmlInput));
            org.w3c.dom.Document document = builder.parse(source);
            document.getDocumentElement().normalize();

            // Extract customerId and password from XML
            String customerIdStr = document.getElementsByTagName("customerId").item(0).getTextContent();
            String password = document.getElementsByTagName("password").item(0).getTextContent();
            int customerId = Integer.parseInt(customerIdStr);

            // Create Login object and validate
            Login login = new Login();
            login.setLoginId(customerId);
            login.setPassword(password);

            int result = loginService.validateLogin(login);

            // Return appropriate response based on validation result
            switch (result) {
                case 1:
                    return ResponseEntity.ok("Login Successful");
                case -1:
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Approval Pending From Administrator");
                case -2:
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Your customerId is locked");
                case -3:
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("CustomerId is locked because login attempts > 3");
                case -4:
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid CustomerId");
                default:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Password attempt = " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error parsing XML input");
        }
    }
}
