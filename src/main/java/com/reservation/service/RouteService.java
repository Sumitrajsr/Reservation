package com.reservation.service;

import com.reservation.exceptions.RouteException;
import com.reservation.payload.RouteDTO;

public interface RouteService {

    public RouteDTO save(Long busId,RouteDTO dto) throws RouteException;
}
