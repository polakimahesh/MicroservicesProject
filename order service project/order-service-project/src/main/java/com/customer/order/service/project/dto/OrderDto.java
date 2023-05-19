package com.customer.order.service.project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDto {

    private String address;
    private int cartId;
    private int customerId;
    private  double grandTotal;

}
