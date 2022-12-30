package com.application.dao.implementation;


import com.application.dao.IHotelDAO;
import com.application.model.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository ("hotelDAOImpl")
public class HotelDAOImpl implements IHotelDAO {

    private final SessionFactory sessionFactory;

    public HotelDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<Hotel> findHotelByName(String name) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select r from Hotel r where r.name=:name", Hotel.class)
                    .setParameter("name", name).getResultStream().findFirst();

        }
    }

    @Override
    public Optional<Hotel> findHotelById(long id) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select r from Room r where r.id=:id", Hotel.class)
                    .setParameter("id", id).getResultStream().findFirst();

        }
    }

    @Override
    public void createHotel(Hotel hotel) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(hotel);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Hotel hotel) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(hotel);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Hotel hotel) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(hotel);
            session.getTransaction().commit();
        }
    }
}
