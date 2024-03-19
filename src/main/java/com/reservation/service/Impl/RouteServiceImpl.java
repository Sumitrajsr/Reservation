package com.reservation.service.Impl;


import com.reservation.exceptions.ResourceNotFoundException;
import com.reservation.entity.Bus;
import com.reservation.entity.Route;
import com.reservation.exceptions.RouteException;
import com.reservation.payload.RouteDTO;
import com.reservation.repository.BusRepository;
import com.reservation.repository.RouteRepository;
import com.reservation.service.RouteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public RouteDTO save(Long busId, RouteDTO dto) throws RouteException {
        Bus bus = busRepository.findById(busId).orElseThrow(
                () -> new ResourceNotFoundException("Bus not Added")
       );
        Route route = modelMapper.map(dto, Route.class);
        route.setBusId(busId);
        Route r = routeRepository.findByBusId(busId);
        if(r !=null){
            throw new ResourceNotFoundException("Route for this Bus is allready present");
        }
        else{
            Route savedRoute = routeRepository.save(route);
            return modelMapper.map(savedRoute,RouteDTO.class);
        }
    }
}
