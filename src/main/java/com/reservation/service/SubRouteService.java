package com.reservation.service;

import com.reservation.exceptions.SubRouteException;
import com.reservation.payload.RouteDTO;
import com.reservation.payload.SubRouteDTO;

public interface SubRouteService {

    public SubRouteDTO save(Long routeId, SubRouteDTO dto) throws SubRouteException;
}
