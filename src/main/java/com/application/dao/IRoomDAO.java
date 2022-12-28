package com.application.dao;

import com.application.model.Room;


import java.util.Optional;

public interface IRoomDAO {


    Optional<Room> findRoomByNumberOfRoom(int numberOfRoom);

    Optional<Room> findRoomById(long id);;
    void addRoomToTheHotel (Room room);
    void delete (Room room);
    void update (Room room);




}
