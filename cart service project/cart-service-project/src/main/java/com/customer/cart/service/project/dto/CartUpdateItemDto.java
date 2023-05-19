package com.customer.cart.service.project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartUpdateItemDto {
    private  int cartId;
    private int cartItemId;
    private int productId;
    private int quantity;
}
