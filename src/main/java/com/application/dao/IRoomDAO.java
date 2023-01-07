package com.application.dao;

import com.application.model.Room;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IRoomDAO {

    Optional<Room> findRoomById(long id);
    void addRoomToTheHotel (Room room);
    void delete (Room room);
    void update (Room room);
    List<Room> findAll();
    List<Room> findAvailableByPeriod(LocalDateTime dateIn, LocalDateTime dateOut);
}