package com.application.service;

import com.application.model.Room;

import java.util.List;

public interface IRoomService {
    void saveRoom(Room room);
    Room readByNumberOfRoom (int id);
    Room readById(long id);

    Room update(Room user);
    void delete(long id);
    List<Room> getAll();

}
