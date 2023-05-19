package com.customer.order.service.project.Order;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
     @CreationTimestamp
     @Column(nullable = false, updatable = false)
     private Date createdAt;
     private String address;

     private Integer customerId;

     private  double grandTotal;


}
