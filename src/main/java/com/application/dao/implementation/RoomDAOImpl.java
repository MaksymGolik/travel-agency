package com.application.dao.implementation;

import com.application.dao.IRoomDAO;
import com.application.model.Booking;
import com.application.model.Hotel;
import com.application.model.Room;
import com.application.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository("roomDAOImpl")
public class RoomDAOImpl implements IRoomDAO {

    private final SessionFactory sessionFactory;

    public RoomDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<Room> findRoomById(long id) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select r from Room r where r.id=:id", Room.class)
                    .setParameter("id", id).getResultStream().findFirst();

        }
    }

    @Override
    public Optional<Room> findRoomByNumberOfAndHotel(int numberOfRoom, Hotel hotel) {
        long id = hotel.getId();
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select r from Room r where r.hotel.id=:id and r.numberOfRoom=:numberOfRoom", Room.class)
                    .setParameter("numberOfRoom", numberOfRoom).getResultStream().findFirst();

        }
    }

    @Override
    public void addRoomToTheHotel(Room room) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(room);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Room room) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(room);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Room room) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(room);
            session.getTransaction().commit();
        }

    }

    @Override
    public List<Room> findAll() {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select r from Room r", Room.class)
                    .getResultStream().collect(Collectors.toList());
        }
    }

    @Override
    public List<Room> findAvailableByPeriod(LocalDateTime dateIn, LocalDateTime dateOut) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select r from Room r where r.id not in " +
                            "(select distinct r.id from Booking b join b.rooms r where b.dateIn<:dateOut and b.dateOut>:dateIn)", Room.class)
                    .setParameter("dateOut",dateOut).setParameter("dateIn",dateIn)
                    .getResultStream().collect(Collectors.toList());
        }
    }

    @Override
    public List<Room> findAvailableByCountryPeriod(String countryName, LocalDateTime dateIn, LocalDateTime dateOut) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select r from Room r where r.id not in " +
                            "(select distinct r.id from Booking b join b.rooms r where b.dateIn<:dateOut and b.dateOut>:dateIn) " +
                            "and r.hotel.country.name=:countryName", Room.class)
                    .setParameter("dateOut",dateOut).setParameter("dateIn",dateIn).setParameter("countryName",countryName)
                    .getResultStream().collect(Collectors.toList());
        }
    }

    @Override
    public List<Room> findAvailableByCountryHotelPeriod(String countryName, String hotelName, LocalDateTime dateIn, LocalDateTime dateOut) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select r from Room r where r.id not in " +
                            "(select distinct r.id from Booking b join b.rooms r where b.dateIn<:dateOut and b.dateOut>:dateIn) " +
                            "and r.hotel.country.name=:countryName and r.hotel.name=:hotelName", Room.class)
                    .setParameter("dateOut",dateOut).setParameter("dateIn",dateIn)
                    .setParameter("countryName",countryName).setParameter("hotelName", hotelName)
                    .getResultStream().collect(Collectors.toList());
        }
    }
}
