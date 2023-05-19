package com.customer.product.service.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductServiceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceProjectApplication.class, args);
	}

}
