package com.customer.customer.service.project.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CustomerDto {

    private Integer customerId;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message="Phone no is mandatory")
    private Long phoneNo;
    @NotBlank(message ="Location is Mandatory")
    private String location;
    private String password;
}
