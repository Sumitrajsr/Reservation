package com.reservation.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;

    private String lastName;
    private String email;
    private Long mobile;
    @Column(name="bus_id",unique = true)
    private Long busId;
    @Column(name="route_id",unique = true)
    private Long routeId;
}
