package com.zulvit.userDatabaseSpring.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int count;
    private int price;
}
