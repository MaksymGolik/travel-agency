package com.application.controller;

import com.application.dto.BookingCreateRequest;
import com.application.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*import java.awt.*;*/

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {



    IBookingService bookingService;

    @Autowired
    public BookingController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("booking") BookingCreateRequest bookingCreateRequest){
        List<Long> roomIdList = bookingCreateRequest.getRoomIdList();
        String dateIn = bookingCreateRequest.getDateIn();
        String dateOut = bookingCreateRequest.getDateOut();
        return null;
    }






}
