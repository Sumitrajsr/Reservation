package com.reservation.service.Impl;

import com.reservation.exceptions.ResourceNotFoundException;
import com.reservation.entity.SubRoute;
import com.reservation.exceptions.SubRouteException;
import com.reservation.payload.SubRouteDTO;
import com.reservation.repository.RouteRepository;
import com.reservation.repository.SubRouteRepository;
import com.reservation.service.SubRouteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubRouteServiceImpl implements SubRouteService {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private SubRouteRepository subrouteRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public SubRouteDTO save(Long routeId, SubRouteDTO dto) throws SubRouteException {
       routeRepository.findById(routeId).orElseThrow(
                () -> new ResourceNotFoundException("Route not Added")
        );
        SubRoute s = modelMapper.map(dto, SubRoute.class);
        s.setRouteId(routeId);

//        List<SubRoute> sroute = subrouteRepository.findByRouteId(routeId);
//        if(sroute!=null){
//            throw new ResourceNotFoundException("Route for this Bus is already present");
//        }
        SubRoute savedRoute = subrouteRepository.save(s);
        return modelMapper.map(savedRoute,SubRouteDTO.class);

    }
}
