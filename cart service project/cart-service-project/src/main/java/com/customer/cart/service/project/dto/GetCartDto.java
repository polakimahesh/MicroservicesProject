package com.customer.cart.service.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GetCartDto {
    private int cartId;
    private double cartTotal;
    private List<CartItemResponseDto> cartItems;
}
