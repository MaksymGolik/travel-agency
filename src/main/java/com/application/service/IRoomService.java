package com.application.service;

import com.application.model.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface IRoomService {
    void saveRoom(Room room);
    Room readById(long id);
    void update(Room room);
    void delete(long id);
    List<Room> readAll();
    List<Room> findAvailableByPeriod(LocalDateTime dateIn, LocalDateTime dateOut);
    List<Room> findAvailableByCountryPeriod(String countryName, LocalDateTime dateIn, LocalDateTime dateOut);
    List<Room> findAvailableByCountryHotelPeriod(String countryName, String hotelName, LocalDateTime dateIn, LocalDateTime dateOut);

}
