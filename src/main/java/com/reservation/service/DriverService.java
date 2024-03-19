package com.reservation.service;

import com.reservation.entity.Driver;
import com.reservation.exceptions.DriverException;
import com.reservation.payload.DriverDTO;

public interface DriverService {

    DriverDTO add(Long busId,DriverDTO dto) throws DriverException;
}
