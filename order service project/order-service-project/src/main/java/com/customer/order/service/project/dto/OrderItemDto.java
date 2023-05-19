package com.customer.order.service.project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemDto {
    private int orderId;
    private int productId;
    private int quantity;


}
