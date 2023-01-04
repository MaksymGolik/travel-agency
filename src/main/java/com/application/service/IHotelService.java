package com.application.service;

import com.application.model.Hotel;


import java.util.List;


public interface IHotelService {


    void saveHotel(Hotel hotel);

    Hotel readById(long id);

    List<Hotel> readByName(String name);

    void update(Hotel hotel);

    void delete(long id);

    List<Hotel> readAll();


}
