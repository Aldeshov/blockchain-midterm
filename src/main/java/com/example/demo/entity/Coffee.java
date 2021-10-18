package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
@Entity
@Data
@Table(name = "coffee")
@AllArgsConstructor
@NoArgsConstructor
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private int weight;

    private String status;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm")
    private Date departureDate;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm")
    private Date deliveryDate;

    @Column(unique = true)
    private String userIin;
}
