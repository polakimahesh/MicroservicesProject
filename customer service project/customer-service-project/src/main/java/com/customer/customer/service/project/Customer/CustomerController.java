package com.customer.customer.service.project.Customer;


import com.customer.customer.service.project.dto.CustomerDeleteDto;
import com.customer.customer.service.project.dto.CustomerDto;
import com.customer.customer.service.project.security.AuthRequest;
import com.customer.customer.service.project.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthService service;


    @Autowired
    private AuthenticationManager authenticationManager;

    // get all the customers
    @GetMapping("/users")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    //create customer
    @PostMapping("/register-customer")
    public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        var customer = customerService.createCustomer(customerDto);
        if (Boolean.TRUE.equals(customer.get("isSuccess"))) {
            return ResponseEntity.ok(customer.get("message"));
        } else
            return ResponseEntity.badRequest().body(customer.get("message"));

    }
    //get single customer

    @GetMapping("/{id}")
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> getSingleCustomer(@PathVariable int id) {
        var customer = customerService.getSingleCustomer(id);
        if (Boolean.TRUE.equals(customer.get("isSuccess"))) {
            return ResponseEntity.ok(customer.get("message"));
        } else
            return ResponseEntity.badRequest().body(customer.get("message"));
    }

    //update customer by using postmapping
    @PostMapping("/update-customer")
    public ResponseEntity<Object> updateCustomer(@Valid @RequestBody CustomerDto customerDto) {
        HashMap<String, Object> customer = customerService.updateCustomer(customerDto);
        if (Boolean.TRUE.equals(customer.get("isSuccess"))) {
            return ResponseEntity.ok(customer.get("message"));
        } else
            return ResponseEntity.badRequest().body(customer.get("message"));
    }

    @PostMapping("/delete-customer")
    public ResponseEntity<Object> deleteCustomer(@Valid @RequestBody CustomerDeleteDto customerDeleteDto) {
        var customer = customerService.deleteCustomer(customerDeleteDto);
        if (Boolean.TRUE.equals(customer.get("isSuccess"))) {
            return ResponseEntity.ok(customer.get("message"));
        } else
            return ResponseEntity.badRequest().body(customer.get("message"));
    }


    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }

}
