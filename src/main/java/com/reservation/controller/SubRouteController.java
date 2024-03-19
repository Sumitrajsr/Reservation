package com.reservation.controller;

import com.reservation.exceptions.SubRouteException;
import com.reservation.payload.RouteDTO;
import com.reservation.payload.SubRouteDTO;
import com.reservation.repository.SubRouteRepository;
import com.reservation.service.RouteService;
import com.reservation.service.SubRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subRoute")
public class SubRouteController {
    @Autowired
    private SubRouteService routeService;

    @PostMapping("/{routeId}")
    public ResponseEntity<SubRouteDTO> addRoute(@PathVariable Long routeId, @RequestBody SubRouteDTO dto) throws SubRouteException {

        SubRouteDTO save = routeService.save(routeId, dto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);

    }
}
