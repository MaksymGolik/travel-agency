package com.application.service.impl;

import com.application.dao.IBookingDAO;
import com.application.dao.IHotelDAO;
import com.application.dao.IRoomDAO;
import com.application.exception.CustomAccessDeniedException;
import com.application.exception.CustomAccessDeniedHandler;
import com.application.exception.EntityNotFoundException;
import com.application.model.Booking;
import com.application.model.Hotel;
import com.application.model.Room;
import com.application.service.IRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service("roomServiceImpl")
@Slf4j
public class RoomServiceImpl implements IRoomService {

    private IRoomDAO roomDAO;

    private IBookingDAO bookingDAO;

    private BookingService bookingService;



    @Autowired
    public RoomServiceImpl(@Qualifier("roomDAOImpl") IRoomDAO roomDAO,IBookingDAO bookingDAO, BookingService bookingService ) {
        this.roomDAO = roomDAO;
        this.bookingDAO = bookingDAO;
        this.bookingService = bookingService;

    }


    @Override
    public void saveRoom(Room room) {
        long numberOfRoom = room.getId();
        if (roomDAO.findRoomById(numberOfRoom).isPresent())
            throw new KeyAlreadyExistsException("Room with this id is already exists");
        roomDAO.addRoomToTheHotel(room);
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
    public void update(Room room) {
        long id = room.getId();
        if(roomDAO.findRoomById(id).isEmpty())
            throw new EntityNotFoundException("Room with id "+ id + " not found");
        roomDAO.update(room);
    }

    @Override
    public void delete(long id) {
        Room room = roomDAO.findRoomById(id).orElseThrow(()->
                new EntityNotFoundException("Room with id "+ id + " not found"));

        List<Booking> bookingList = bookingDAO.findAll();

         for (Booking booking: bookingList) {
            if(booking.getRooms().contains(room)){
                /*this is not thrown*/
                throw new CustomAccessDeniedException("You can not remove room:" + room.getNumberOfRoom() );
            }

        }
        roomDAO.delete(room);



    }

    @Override
    public List<Room> readAll() {
        return roomDAO.findAll();
    }

    @Override
    public List<Room> findAvailableByPeriod(LocalDateTime dateIn, LocalDateTime dateOut) {
        return roomDAO.findAvailableByPeriod(dateIn, dateOut);
    }

    @Override
    public List<Room> findAvailableByCountryPeriod(String countryName, LocalDateTime dateIn, LocalDateTime dateOut) {
        return roomDAO.findAvailableByCountryPeriod(countryName, dateIn, dateOut);
    }

    @Override
    public List<Room> findAvailableByCountryHotelPeriod(String countryName, String hotelName, LocalDateTime dateIn, LocalDateTime dateOut) {
        return roomDAO.findAvailableByCountryHotelPeriod(countryName, hotelName, dateIn, dateOut);
    }
}
