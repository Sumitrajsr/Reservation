package com.reservation.service.Impl;

import com.reservation.entity.Bus;
import com.reservation.entity.Passenger;
import com.reservation.entity.Route;
import com.reservation.entity.SubRoute;
import com.reservation.repository.BusRepository;
import com.reservation.repository.PassengerRepository;
import com.reservation.repository.RouteRepository;
import com.reservation.repository.SubRouteRepository;
import com.reservation.service.ReservationService;
import com.reservation.utils.Emailservice;
import com.reservation.utils.ExcelService;
import com.reservation.utils.PdfTicketGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private SubRouteRepository subrouteRepository;
    @Autowired
    private PdfTicketGenerationService pdfTicketGenerationService;
    @Autowired
    private Emailservice emailservice;
    @Autowired
    private ExcelService excelService;


    public String bookTicket(Long busId, Long routeId, Passenger passenger) {
        boolean busIsPresent=false;
        boolean routePresent=false;
        boolean subRoutePresent=false;
        Optional<Bus> byId = busRepository.findById(busId);
        if(byId.isPresent()){
            busIsPresent=true;
            Bus bus = byId.get();
        }
        Optional<Route> byRouteId = routeRepository.findById(routeId);
        if(byRouteId.isPresent()){
            routePresent=true;
            Route route = byRouteId.get();
        }

        Optional<SubRoute> bySubroute = subrouteRepository.findById(routeId);
        if(bySubroute.isPresent()){
            subRoutePresent=true;
            SubRoute subRoute = bySubroute.get();
        }
        if(busIsPresent&&routePresent||busIsPresent&&subRoutePresent){
                Passenger p= new Passenger();
                p.setFirstName(passenger.getFirstName());
                 p.setLastName(passenger.getLastName());
                p.setMobile(passenger.getMobile());
                p.setEmail(passenger.getEmail());
                p.setRouteId(routeId);
                p.setBusId(busId);
            Passenger saved = passengerRepository.save(p);
            byte[] bytes = pdfTicketGenerationService.generatePdfTicket(saved, byRouteId.get().getFromLocation(), byRouteId.get().getToLocation(), byRouteId.get().getFromDate(), byRouteId.get().getFromTime(), byRouteId.get().getToTime(), byRouteId.get().getTotalDuration());
            emailservice.sendEmailWithAttachment(passenger.getEmail(),"Your Ticket Booked","Your Reservation id"+passenger.getId(),bytes,"Ticket" );
        }
        return "Done";


    }



}
