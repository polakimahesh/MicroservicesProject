package com.customer.order.service.project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetQuantityDto {
    private int productId;
    private  int orderItemId;
    private int quantity;

}
