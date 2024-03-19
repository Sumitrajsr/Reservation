package com.reservation.service;

import com.reservation.entity.Passenger;

public interface ReservationService {
    String bookTicket(Long busId, Long routeId, Passenger passenger);
}
