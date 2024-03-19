package com.reservation.repository;

import com.reservation.entity.Driver;
import com.reservation.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Long> {
    Driver findByBusId(Long busId);
}
