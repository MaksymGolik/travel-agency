package com.application.dao;

import com.application.model.Booking;

import java.util.List;
import java.util.Optional;

public interface IBookingDAO {
    Optional<Booking> findBookingById(long id);
    List<Booking> findBookingsByUserId(long id);

    List<Booking> findAll();
    void addBooking(Booking booking);
    void update(Booking booking);
    void delete(Booking booking);
}
