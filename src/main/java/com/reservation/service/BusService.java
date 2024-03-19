package com.reservation.service;

import com.reservation.entity.Bus;
import com.reservation.exceptions.BusException;
import com.reservation.payload.BusDto;
import com.reservation.payload.SearchListOfBusesDto;

import javax.transaction.Transactional;
import java.util.List;

public interface BusService {


   public BusDto createBus(BusDto dto) throws BusException;


   List<SearchListOfBusesDto> searchBus(String fromLocation, String toLocation, String fromDate) throws BusException;
}
