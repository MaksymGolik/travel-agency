package com.application.service.impl;

import com.application.dao.IBookingDAO;
import com.application.exception.EntityNotFoundException;
import com.application.model.Booking;
import com.application.model.User;
import com.application.service.IBookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("bookingServiceImpl")
@Slf4j
public class BookingService implements IBookingService {

    IBookingDAO bookingDAO;

    @Autowired
    public BookingService(IBookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    @Override
    public Booking readById(long id) {
        Optional<Booking> booking = bookingDAO.findBookingById(id);
        if (booking.isEmpty()) {
            log.warn("IN readById - no booking found by id: {}", id);
            throw new EntityNotFoundException("Booking with id " + id + " not found");
        }
        log.info("IN readById - booking: {} found by id: {}", booking.get(), id);
        return booking.get();
    }

    @Override
    public List<Booking> readByUserId(long id) {
        return bookingDAO.findBookingsByUserId(id);
    }

    @Override
    public void saveBooking(Booking booking) {
        bookingDAO.addBooking(booking);
    }

    @Override
    public void update(Booking booking) {
        long id = booking.getId();
        if (bookingDAO.findBookingById(id).isEmpty())
            throw new EntityNotFoundException("Booking with id " + id + " not found");
        bookingDAO.update(booking);
    }

    @Override
    public void delete(long id) {
        Booking booking = bookingDAO.findBookingById(id).orElseThrow(() ->
                new EntityNotFoundException("Booking with id " + id + " not found"));
        bookingDAO.delete(booking);
    }

    @Override
    public boolean checkIfAvailableDate(LocalDateTime dateIn, LocalDateTime dateOut) {
        if (dateOut.isBefore(dateIn)) {
            throw new DateTimeException("The date of check_out is later than the date of check_in");
        }
        List<Booking> bookingList = bookingDAO.findAll();
        for (Booking booking : bookingList) {
            if (booking.getDateIn().isBefore(dateIn) && booking.getDateOut().isAfter(dateIn)
                    || booking.getDateIn().isBefore(dateOut) && booking.getDateOut().isAfter(dateOut)
                    || booking.getDateIn().isAfter(dateIn) && booking.getDateOut().isBefore(dateOut)
                    || booking.getDateIn().isBefore(dateIn) && booking.getDateOut().isAfter(dateOut)
            )
                return false;
        }

        return true;
    }
}
