package com.customer.customer.service.project.Customer;

import com.customer.customer.service.project.dto.CustomerDeleteDto;
import com.customer.customer.service.project.dto.CustomerDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
     private  CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

   public HashMap<String,Object> createCustomer(CustomerDto customerDto){
        Customer customer =new Customer();
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        customer.setName(customerDto.getName());
        customer.setPhoneNo(customerDto.getPhoneNo());
        customer.setLocation((customerDto.getLocation()));
        String encodePassword = passwordEncoder.encode(customerDto.getPassword());
        customer.setPassword(encodePassword);
        customerRepository.save(customer);
        response1.put("message","Created Customer " +customerDto.getName() +" Successfully with id! "+customer.getId());
        response.put("isSuccess",true);
        response.put("message",response1);
        return  response;
   }

   public HashMap<String,Object> getSingleCustomer(Integer id){
       HashMap<String,Object> response = new HashMap<>();
       HashMap<String,Object> response1= new HashMap<>();
       var customer=  customerRepository.findById(id);
       if(customer.isEmpty()){
           response1.put("message","incorrect customer id "+id+", please enter the valid id!");
           response.put("isSuccess",false);
           response.put("message",response1);
           return  response;
       }
       response.put("isSuccess",true);
       response.put("message",customer);
       return  response;
   }


   public HashMap<String,Object> updateCustomer(CustomerDto customerDto){
       HashMap<String,Object> response = new HashMap<>();
       HashMap<String,Object> response1= new HashMap<>();
       response1.put("message","incorrect customer id "+customerDto.getCustomerId()+", please enter the valid id!");
       var customer = customerRepository.findById(customerDto.getCustomerId()).orElse(null);
       if(customer==null){
           response.put("isSuccess",false);
           response.put("message",response1);
           return  response;
       }else{
           customer.setName(customerDto.getName());
           customer.setPhoneNo(customerDto.getPhoneNo());
           customer.setLocation(customerDto.getLocation());
           customerRepository.save(customer);
           response1.put("message","Updated Customer " +customerDto.getName() +" Successfully with id! "+customerDto.getCustomerId());
           response.put("isSuccess",true);
           response.put("message",response1);
           return  response;
       }
   }
    public HashMap<String,Object>  deleteCustomer(CustomerDeleteDto customerDto ){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();

        var customer = customerRepository.findById(customerDto.getCustomerId()).orElse(null);
        if(customer==null){
            response1.put("message","incorrect customer id "+customerDto.getCustomerId()+", please enter the valid id!");
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }else{
            customerRepository.deleteById(customerDto.getCustomerId());
            response1.put("message","customer deleted successfully! "+customerDto.getCustomerId());
            response.put("isSuccess",true);
            response.put("message",response1);
            return  response;
        }
    }

}
