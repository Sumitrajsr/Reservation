package com.reservation.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {
    private Long driverId;
    private String driverName;
    private String driverLicenseNumber;
    private String aadharNumber;
    private String address;
    private String contactNumber;
    private String alternateContactNumber;
    private String emailId;
    private Long busId;
}
