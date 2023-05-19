package com.customer.product.service.project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetAvailabilityDto {
    private Integer productId;
    private Integer quantity;
}
