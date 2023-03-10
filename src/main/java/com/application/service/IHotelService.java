package com.application.service;

import com.application.model.Hotel;
import com.application.model.Room;


import java.util.List;


public interface IHotelService {


    void saveHotel(Hotel hotel);

    Hotel readById(long id);

    Hotel readByName(String name);

    void update(Hotel hotel);

    void delete(long id);

    List<Hotel> readAll();

    List<Room> readAllRoomsInHotel(String name);

    List<Room> readAllRoomsInHotel(long id);


}
