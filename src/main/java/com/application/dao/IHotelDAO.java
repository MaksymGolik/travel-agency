package com.application.dao;

import com.application.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface IHotelDAO {
    Optional<Hotel> findHotelByName(String name);

    Optional<Hotel> findHotelById(long id);

    void createHotel (Hotel hotel);
    void delete (Hotel hotel);
    void update (Hotel hotel);

    List<Hotel> findAll();


}
