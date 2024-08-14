package com.ofss.main.service;

import com.ofss.main.domain.Login;
import com.ofss.main.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public int validateLogin(Login login) {
        Login storedLogin = loginRepository.findById(login.getLoginId()).orElse(null);

        if (storedLogin == null) {
            return -4; // Invalid CustomerId
        }

        if ("NEW".equals(storedLogin.getLoginStatus())) {
            return -1; // Approval Pending From Administrator
        }

        if ("LOCKED".equals(storedLogin.getLoginStatus())) {
            return -2; // Your customerId is locked
        }

        if (storedLogin.getLoginAttempts() > 3) {
            return -3; // CustomerId is locked because login attempts > 3
        }

        if (storedLogin.getPassword().equals(login.getPassword())) {
            // Reset login attempts on successful login
            storedLogin.setLoginAttempts(0);
            loginRepository.save(storedLogin);
            return 1; // All valid details
        } else {
            // Increment login attempts
            int attempts = storedLogin.getLoginAttempts() + 1;
            storedLogin.setLoginAttempts(attempts);

            if (attempts > 3) {
                storedLogin.setLoginStatus("LOCKED");
            }

            loginRepository.save(storedLogin);
            return attempts; // Invalid Password remaining attempts
        }
    }
}
