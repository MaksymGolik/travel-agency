package com.application.dao.implementation;

import com.application.dao.IRoomDAO;
import com.application.model.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class RoomDAOImpl implements IRoomDAO {

    private final SessionFactory sessionFactory;

    public RoomDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }




    @Override
    public Optional<Room> findRoomByNumberOfRoom(int numberOfRoom) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select r from Room r where r.numberOfRoom=:numberOfRoom", Room.class)
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


}
