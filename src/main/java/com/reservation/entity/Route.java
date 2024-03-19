package com.reservation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;

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
    @Column(name="bus_id",unique = true,nullable = false)
    private Long busId;



}

