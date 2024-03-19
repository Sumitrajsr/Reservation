package com.reservation.controller;

import com.reservation.entity.Driver;
import com.reservation.exceptions.DriverException;
import com.reservation.payload.DriverDTO;
import com.reservation.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @PostMapping("{busId}")
    public ResponseEntity<DriverDTO> addDriver(@PathVariable Long busId, @RequestBody DriverDTO dto) throws DriverException {
        DriverDTO add = driverService.add(busId, dto);
        return new ResponseEntity<>(add, HttpStatus.CREATED);


    }
}
