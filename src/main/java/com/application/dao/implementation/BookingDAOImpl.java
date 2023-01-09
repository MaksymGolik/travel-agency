package com.application.dao.implementation;

import com.application.dao.IBookingDAO;
import com.application.model.Booking;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("bookingDAOImpl")
public class BookingDAOImpl implements IBookingDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BookingDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Booking> findBookingById(long id) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select b from Booking b where b.id=:id", Booking.class)
                    .setParameter("id", id).getResultStream().findFirst();

        }
    }

    @Override
    public List<Booking> findBookingsByUserId(long id) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select b from Booking b where b.user.id=:id", Booking.class)
                    .setParameter("id", id).getResultStream().collect(Collectors.toList());
        }
    }

    @Override
    public List<Booking> findAll() {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select b from Booking b", Booking.class)
                    .getResultStream().collect(Collectors.toList());
        }
    }


    @Override
    public void addBooking(Booking booking) {

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(booking);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Booking booking) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(booking);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Booking booking) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(booking);
            session.getTransaction().commit();
        }
    }
}