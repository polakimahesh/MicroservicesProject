package com.customer.cart.service.project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemDto {
    private int cartId;
    private int productId;
    private String itemName;
    private  int itemQuantity;
    private  double itemPrice;
//    private double totalPrice;


}
