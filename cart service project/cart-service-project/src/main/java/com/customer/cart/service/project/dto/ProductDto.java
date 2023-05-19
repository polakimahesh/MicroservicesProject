package com.customer.cart.service.project.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ProductDto {
    @NotNull(message = "Product id is required")
    private int id;
    @NotBlank(message = "Name is must be required")
    private String name;
    private int quantity;
    private double price;
    @NotNull(message = "Description is mandatory")
    private String description;
//    private  double totalPrice;
}
