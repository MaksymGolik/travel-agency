package com.application.service;

import com.application.dto.RoomCreateRequest;
import com.application.dto.UserCreateRequest;
import com.application.dto.UserResponse;
import com.application.model.Hotel;
import com.application.model.Room;
import com.application.model.RoomType;
import com.application.model.User;

import java.util.List;

public interface IRoomService {
    void saveRoom(Room room);
    Room readByNumberOfRoom (int id);
    Room readById(long id);

    Room update(Room user);
    void delete(long id);
    List<Room> getAll();

}
