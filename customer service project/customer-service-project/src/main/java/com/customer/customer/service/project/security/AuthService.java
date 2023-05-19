package com.customer.customer.service.project.security;

import com.customer.customer.service.project.Customer.Customer;
import com.customer.customer.service.project.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtService jwtService;

    public String generateToken(String username) {
        return "Bearer "+ jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}
