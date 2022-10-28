package com.zulvit.userDatabaseSpring.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    private int count;
}
