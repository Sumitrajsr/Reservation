package com.reservation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    private String driverName;
    @Column(name="driver_LicenseNumber",nullable = false,unique = true)
    private String driverLicenseNumber;
    @Column(name="aadhar_Number",nullable = false,unique = true)
    private String aadharNumber;
    private String address;
    @Column(name="contact_Number",nullable = false,unique = true)
    private String contactNumber;
    private String alternateContactNumber;
    @Column(name="email_id",nullable = false,unique = true)
    private String emailId;

    @Column(name="bus_id",unique = true,nullable = false)
    private Long busId;



}
