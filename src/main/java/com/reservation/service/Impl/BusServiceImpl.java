package com.reservation.service.Impl;
import com.reservation.exceptions.BusException;
import com.reservation.payload.*;
import com.reservation.repository.BusRepository;
import com.reservation.repository.DriverRepository;
import com.reservation.repository.RouteRepository;
import com.reservation.repository.SubRouteRepository;
import com.reservation.service.BusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reservation.entity.Bus;
import com.reservation.entity.Route;
import com.reservation.entity.SubRoute;
import com.reservation.entity.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private SubRouteRepository subrouteRepository;

    @Autowired
    private SubRouteRepository subRouteRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BusDto createBus(BusDto dto) throws BusException {
        Bus bus = new Bus();
        bus.setBusNumber(dto.getBusNumber());
        bus.setBusType(dto.getBusType());
        bus.setPrice(dto.getPrice());
        bus.setTotalSeats(dto.getTotalSeats());
        bus.setAvailableSeats(dto.getAvailableSeats());
        busRepository.save(bus);

        BusDto map = modelMapper.map(bus, BusDto.class);

        return map;
    }

    @Override
    public List<SearchListOfBusesDto> searchBus(String fromLocation, String toLocation, String fromDate) throws BusException {
        try {
            List<Route> routes = routeRepository.findByFromLocationAndToLocationAndFromDate(fromLocation, toLocation, fromDate);
            List<SubRoute> subroutes = subrouteRepository.findByFromLocationAndToLocationAndFromDate(fromLocation, toLocation, fromDate);
            List<SearchListOfBusesDto> buses = new ArrayList<>();

            if (routes != null) {
                for (Route route : routes) {
                    Optional<Bus> optionalBus = busRepository.findById(route.getBusId());
                    if (optionalBus.isPresent()) {
                        Bus bus = optionalBus.get();
                        SearchListOfBusesDto searchListOfBusesDto = mapToSearchListOfBusesDto(bus, route);
                        buses.add(searchListOfBusesDto);
                    }
                }
            }

            if (subroutes != null) {
                for (SubRoute route : subroutes) {
                    Optional<Route> optionalRoute = routeRepository.findById(route.getRouteId());
                    Optional<Bus> optionalBus = optionalRoute.map(r -> busRepository.findById(r.getBusId())).orElse(Optional.empty());

                    if (optionalRoute.isPresent() && optionalBus.isPresent()) {
                        Bus bus = optionalBus.get();
                        SearchListOfBusesDto searchListOfBusesDto = mapToSearchListOfBusesDto(bus, route);
                        buses.add(searchListOfBusesDto);
                    }
                }
            }

            return buses;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    SearchListOfBusesDto mapToSearchListOfBusesDto (Bus bus,Route route){
        SearchListOfBusesDto searchListOfBusesDto=new SearchListOfBusesDto();
        searchListOfBusesDto.setBusId(bus.getBusId());
       searchListOfBusesDto.setBusNumber(bus.getBusNumber());
       searchListOfBusesDto.setBusType(bus.getBusType());
       searchListOfBusesDto.setPrice(bus.getPrice());
       searchListOfBusesDto.setAvailableSeats(bus.getAvailableSeats());
       searchListOfBusesDto.setTotalSeats(bus.getTotalSeats());
       searchListOfBusesDto.setRouteId(route.getRouteId());
       searchListOfBusesDto.setFromLocation(route.getFromLocation());
       searchListOfBusesDto.setToLocation(route.getToLocation());
       searchListOfBusesDto.setFromDate(route.getFromDate());
       searchListOfBusesDto.setToDate(route.getToDate());
       searchListOfBusesDto.setFromTime(route.getFromTime());
       searchListOfBusesDto.setToTime(route.getToTime());
       searchListOfBusesDto.setTotalDuration(route.getTotalDuration());
       return searchListOfBusesDto;

    }
    SearchListOfBusesDto mapToSearchListOfBusesDto (Bus bus,SubRoute route){
        SearchListOfBusesDto searchListOfBusesDto=new SearchListOfBusesDto();
        searchListOfBusesDto.setBusId(bus.getBusId());
        searchListOfBusesDto.setBusNumber(bus.getBusNumber());
        searchListOfBusesDto.setBusType(bus.getBusType());
        searchListOfBusesDto.setPrice(bus.getPrice());
        searchListOfBusesDto.setAvailableSeats(bus.getAvailableSeats());
        searchListOfBusesDto.setTotalSeats(bus.getTotalSeats());
        searchListOfBusesDto.setRouteId(route.getRouteId());
        searchListOfBusesDto.setFromLocation(route.getFromLocation());
        searchListOfBusesDto.setToLocation(route.getToLocation());
        searchListOfBusesDto.setFromDate(route.getFromDate());
        searchListOfBusesDto.setToDate(route.getToDate());
        searchListOfBusesDto.setFromTime(route.getFromTime());
        searchListOfBusesDto.setToTime(route.getToTime());
        searchListOfBusesDto.setTotalDuration(route.getTotalDuration());
        return searchListOfBusesDto;

    }


}
