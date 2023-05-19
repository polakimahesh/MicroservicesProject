package com.customer.order.service.project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemResponseDto {
//    private int itemId;
    private String itemName;
    private int itemQuantity;
    private double itemPrice;
    private String itemDescription;
    private double itemTotal;
}
