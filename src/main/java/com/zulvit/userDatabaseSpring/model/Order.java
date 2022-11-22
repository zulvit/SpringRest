package com.zulvit.userDatabaseSpring.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
