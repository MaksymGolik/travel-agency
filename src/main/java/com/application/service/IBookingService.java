package com.application.service;

import com.application.model.Booking;

import java.util.List;

public interface IBookingService {
    Booking readById(long id);
    List<Booking> readByUserId(long id);
    void saveBooking(Booking booking);
    void update(Booking booking);
    void delete(long id);
}
