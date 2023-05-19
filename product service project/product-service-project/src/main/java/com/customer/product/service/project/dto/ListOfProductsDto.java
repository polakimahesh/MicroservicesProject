package com.customer.product.service.project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListOfProductsDto {
    private  Integer productId;
    private String name;
    private Integer quantity;
    private Double price;
    private  String description;

}
