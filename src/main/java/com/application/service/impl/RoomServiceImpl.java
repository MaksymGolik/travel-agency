package com.application.service.impl;

import com.application.dao.IRoomDAO;
import com.application.dto.RoomCreateRequest;
import com.application.dto.mapper.RoomCreateRequestMapper;
import com.application.exception.EntityNotFoundException;
import com.application.model.Room;
import com.application.service.IRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;
import java.util.Optional;


@Service("roomServiceImpl")
@Slf4j
public class RoomServiceImpl implements IRoomService {

    private IRoomDAO roomDAO;


    @Autowired
    public RoomServiceImpl(@Qualifier("roomDAOImpl") IRoomDAO roomDAO) {
        this.roomDAO = roomDAO;

    }


    @Override
    public void saveRoom(Room room) {
        int numberOfRoom = room.getNumberOfRoom();
        if (roomDAO.findRoomByNumberOfRoom(numberOfRoom).isPresent())
            throw new KeyAlreadyExistsException("Room with this number is already exists");
        roomDAO.addRoomToTheHotel(room);
    }

    @Override
    public Room readByNumberOfRoom(int numberOfRoom) {
        Optional<Room> room = roomDAO.findRoomById(numberOfRoom);
        if (room.isEmpty()) {
            log.warn("IN readByNumberOfRoom - no room found by id: {}", numberOfRoom);
            throw new EntityNotFoundException("Room with number " + numberOfRoom + " not found");
        }
        log.info("IN readByNumberOfRoom - room: {} found by number: {}", room.get(), numberOfRoom);
        return room.get();
    }

    @Override
    public Room readById(long id) {
        Optional<Room> room = roomDAO.findRoomById(id);
        if (room.isEmpty()) {
            log.warn("IN readRoomById - no room found by id: {}", id);
            throw new EntityNotFoundException("Room with id " + id + " not found");
        }
        log.info("IN readRoomById - room: {} found by id: {}", room.get(), id);
        return room.get();
    }

    @Override
    public Room update(Room user) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Room> getAll() {
        return null;
    }
}
