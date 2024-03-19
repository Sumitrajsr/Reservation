package com.reservation.controller;

import com.reservation.entity.Route;
import com.reservation.exceptions.RouteException;
import com.reservation.payload.RouteDTO;
import com.reservation.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/route")
public class RouteController {
    @Autowired
    private RouteService routeService;
    @PostMapping("/{busId}")
    public ResponseEntity<RouteDTO> addRoute(@PathVariable  Long busId, @RequestBody RouteDTO dto) throws RouteException {

        RouteDTO save = routeService.save(busId, dto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);

    }
}
