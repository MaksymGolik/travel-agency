package com.application.service.impl;

import com.application.dao.IHotelDAO;
import com.application.exception.EntityNotFoundException;
import com.application.model.Hotel;
import com.application.service.IHotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class HotelServiceImpl implements IHotelService {


    private IHotelDAO hotelDAO;


    @Autowired
    public HotelServiceImpl(@Qualifier("hotelDAOImpl") IHotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }


    @Override
    public void saveHotel(Hotel hotel) {
        long id = hotel.getId();
        if (hotelDAO.findHotelById(id).isPresent())
            throw new KeyAlreadyExistsException("Hotel with this id is already exists");
        hotelDAO.createHotel(hotel);
    }


    @Override
    public Hotel readById(long id) {
        Optional<Hotel> hotel = hotelDAO.findHotelById(id);
        if (hotel.isEmpty()) {
            log.warn("IN readById - no hotel found by id: {}", id);
            throw new EntityNotFoundException("Hotel with id " + id + " not found");
        }
        log.info("IN readById - hotel: {} found by id: {}", hotel.get(), id);
        return hotel.get();
    }

    @Override
    public List<Hotel> readByName(String name) {
        List<Hotel> hotels = hotelDAO.findHotelByName(name);
        if (hotels.isEmpty()) {
            log.warn("IN readByName - no hotel found by name: {}", name);
            throw new EntityNotFoundException("Hotel with name " + name + " not found");
        }
        log.info("IN readByName - hotels: {} found by name: {}", hotels, name);
        return hotels;
    }


    @Override
    public void update(Hotel hotel) {
        long id = hotel.getId();
        if (hotelDAO.findHotelById(id).isEmpty())
            throw new EntityNotFoundException("Hotel with id " + id + " not found");
        hotelDAO.update(hotel);
    }


    @Override
    public void delete(long id) {
        Hotel hotel = hotelDAO.findHotelById(id).orElseThrow(() ->
                new EntityNotFoundException("Hotel with id " + id + " not found"));
        hotelDAO.delete(hotel);
    }
}
