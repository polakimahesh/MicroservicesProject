package com.customer.product.service.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GetProductList {
    List<ListOfProductsDto> listOfProducts;
}
