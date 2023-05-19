package com.example.cloudgateway.fallbackController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class FallbackController {
    @RequestMapping("/customerFallBack")
    public ResponseEntity<HashMap<String,String>> customerServiceFallBack(){
        HashMap<String,String> response= new HashMap<>();
        response.put("message","customer service not available at this time!. please try again later");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/productFallBack")
    public ResponseEntity<HashMap<String,String>> productServiceFallBack(){
        HashMap<String,String> response= new HashMap<>();
        response.put("message","product service not available at this time!. please try again later");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @RequestMapping("/cartFallBack")
    public ResponseEntity<HashMap<String,String>> cartServiceFallBack(){
        HashMap<String,String> response= new HashMap<>();
        response.put("message","cart service not available at this time!. please try again later");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @RequestMapping("/orderFallBack")
    public ResponseEntity<HashMap<String,String>> orderServiceFallBack(){
        HashMap<String,String> response= new HashMap<>();
        response.put("message","order service not available at this time!. please try again later");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
