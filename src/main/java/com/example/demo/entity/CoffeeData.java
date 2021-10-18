package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CoffeeData {
    private String type;
    private int weight;
    private String departureDate;
    private String deliveryDate;
    private String userIin;
}
