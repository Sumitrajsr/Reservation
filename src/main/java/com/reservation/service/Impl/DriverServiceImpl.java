package com.reservation.service.Impl;

import com.reservation.exceptions.DriverException;
import com.reservation.exceptions.ResourceNotFoundException;
import com.reservation.entity.Bus;
import com.reservation.entity.Driver;
import com.reservation.payload.DriverDTO;
import com.reservation.repository.BusRepository;
import com.reservation.repository.DriverRepository;
import com.reservation.service.DriverService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public DriverDTO add(Long busId, DriverDTO dto) throws DriverException {
        Bus bus = busRepository.findById(busId).orElseThrow(() -> new ResourceNotFoundException("Bus is Not Added"));

        Driver driver = modelMapper.map(dto, Driver.class);
        driver.setBusId(busId);;
        Driver d = driverRepository.findByBusId(busId);
        if(d !=null){
            throw new ResourceNotFoundException("Driver for this Bus is already present");
        }
        else{
            Driver savedDriver = driverRepository.save(driver);
            return modelMapper.map(savedDriver,DriverDTO.class);
        }

    }
}
