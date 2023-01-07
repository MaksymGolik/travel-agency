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
}
