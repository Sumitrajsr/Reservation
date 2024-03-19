package com.reservation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busId;

    @Column(name = "bus_Number",nullable = false,unique = true)
    private String busNumber;
    @Column(name="bus_type")
    private String busType;
    @Column(name="price")
    private double price;
    @Column(name="total_seats")
    private int totalSeats;
    @Column(name="available_seats")
    private int availableSeats;



}
