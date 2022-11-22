package com.zulvit.userDatabaseSpring.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "invoice")
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "order_id")
    private Long order_id;
    @Column(name = "product_id")
    private Long product_id;
    @Column(name = "price")
    private double price;
    @Column(name = "amount")
    private int amount;
}
