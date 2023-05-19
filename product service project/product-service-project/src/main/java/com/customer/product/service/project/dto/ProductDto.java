package com.customer.product.service.project.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ProductDto {

    private Integer productId;
    @NotBlank(message = "Name is must be required")
    private String name;
    private Integer quantity;
    private Double price;
    @NotNull(message = "Description is mandatory")
    private String description;
//    private  double totalPrice;
}
