package com.reservation.controller;

import com.reservation.entity.Passenger;
import com.reservation.payload.ReserVationDto;
import com.reservation.repository.PassengerRepository;
import com.reservation.service.ReservationService;
import com.reservation.utils.Emailservice;
import com.reservation.utils.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private ExcelService excelService;
    @PostMapping
    public String bookTicket(@RequestParam Long busId,
                                     @RequestParam Long routeId,
                                     @RequestBody Passenger passenger
    ){
        String s = reservationService.bookTicket(busId, routeId, passenger);

        return s;

    }
//    http://localhost:5757/api/reservationpassengers/excel
    @GetMapping("/passengers/excel")
    public ResponseEntity<byte[]> generateExcel(){
        try{
            List<Passenger> passengers=fetchPassengersFromDatabase();
            byte[] excelBytes= excelService.generateExcel(passengers);
            HttpHeaders headers=new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment","passenger_data.xlsx");
            return new ResponseEntity<>(excelBytes,headers, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private List<Passenger>fetchPassengersFromDatabase(){
        return passengerRepository.findAll();
    }
}
