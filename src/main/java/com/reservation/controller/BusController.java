package com.reservation.controller;

import com.reservation.entity.Bus;
import com.reservation.entity.Route;
import com.reservation.exceptions.BusException;
import com.reservation.payload.BusDto;
import com.reservation.payload.SearchListOfBusesDto;
import com.reservation.repository.RouteRepository;
import com.reservation.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bus")
public class BusController {

    private final BusService busService; // Assume you have a service class
    @Autowired
    private RouteRepository routeRepository;



    public BusController(BusService busService) {
        this.busService = busService;
    }
    //http://localhost:5757/api/v1/bus/add
    @PostMapping("/add")
    public ResponseEntity<?> createBus(@RequestBody BusDto dto) throws ParseException , BusException {


        BusDto busDto = busService.createBus(dto);
        return new ResponseEntity<>(busDto, HttpStatus.CREATED);

    }
    //http://localhost:5757/api/v1/bus?fromLocation=&toLocation&fromDate
    @GetMapping
    public ResponseEntity<List<SearchListOfBusesDto>> searchBuses(
            @RequestParam("fromLocation") String fromLocation,
            @RequestParam("toLocation") String toLocation,
            @RequestParam("fromDate") String fromDate
    ) throws BusException {
        List<SearchListOfBusesDto> buses = busService.searchBus(fromLocation, toLocation, fromDate);
        return new ResponseEntity<>(buses,HttpStatus.OK);


    }



}
