package com.reservation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subRouteId;

    private String fromLocation;
    private String toLocation;

    @Column(name = "fromDateTime")
    private String fromDate;

    @Column(name = "toDateTime")
    private String toDate;

    @Column(name = "fromTime")
    private String fromTime;

    @Column(name = "toTime")
    private String toTime;

    private String totalDuration;

    @Column(name="route_id",nullable = false)
    private Long routeId; // Many-to-One mapping with Route

    // Constructors, getters, and setters
}
