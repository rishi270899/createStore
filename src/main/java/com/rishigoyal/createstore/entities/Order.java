package com.rishigoyal.createstore.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "customer_name")
    private String customerName;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private String Status;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name= "created_at")
    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order")
    private List<OrderItems> orderItems;


    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }




}
