package com.application.dao;

import com.application.model.Room;
import com.application.model.User;


import java.util.List;
import java.util.Optional;

public interface RoomDAO {


    Optional<Room> findRoomByNumberOfRoom(int numberOfRoom);
    void addRoomToTheHotel (Room room);
    void delete (Room room);
    void update (Room room);




}
