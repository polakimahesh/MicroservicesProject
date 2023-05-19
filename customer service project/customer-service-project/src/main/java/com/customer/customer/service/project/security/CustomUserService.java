package com.customer.customer.service.project.security;

import com.customer.customer.service.project.Customer.Customer;
import com.customer.customer.service.project.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserService implements UserDetailsService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> credential = repository.findByName(username);
        return credential.map(CustomUser::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
    }
}