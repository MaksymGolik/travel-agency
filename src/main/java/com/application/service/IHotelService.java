package com.application.service;

import com.application.model.Hotel;
import com.application.model.Room;


public interface IHotelService {


    void saveHotel (Hotel hotel);
    Hotel readById(long id);
    Hotel readByName(String name);
    void update(Hotel user);
    void delete (long id);


}
