package com.application.dao.implementation;

import com.application.dao.IRoomDAO;
import com.application.model.Hotel;
import com.application.model.Room;
import com.application.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

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


}
