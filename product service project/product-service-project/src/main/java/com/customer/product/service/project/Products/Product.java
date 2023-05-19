package com.customer.product.service.project.Products;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name is must be required")
    private String name;
    private Integer quantity;
    private Double price;
    @NotNull(message = "Description is mandatory")
    private String description;


//    public Product(int id){
//        this.id=id;
//    }

}
