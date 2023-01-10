package com.application.controller;

import com.application.dto.BookingCreateRequest;
import com.application.dto.BookingResponse;
import com.application.dto.UserCreateRequest;
import com.application.dto.mapper.BookingMapper;
import com.application.model.Booking;
import com.application.model.Room;
import com.application.model.User;
import com.application.service.IBookingService;
import com.application.service.IRoomService;
import com.application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/*import java.awt.*;*/

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {


    IBookingService bookingService;
    IRoomService roomService;

    IUserService userService;


    @Autowired
    public BookingController(IBookingService bookingService, IRoomService roomService,
                             IUserService userService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.roomService = roomService;
    }


    @PostMapping("/create")
    public String create(@ModelAttribute("booking") BookingCreateRequest bookingCreateRequest,
                         Principal principal, Model model, BindingResult result) {
        List<Long> roomIdList = bookingCreateRequest.getRoomIdList();

        LocalDateTime dateIn = bookingCreateRequest.getDateIn();
        LocalDateTime dateOut = bookingCreateRequest.getDateOut();

        String email = principal.getName();
        User user = userService.readByEmail(email);

        List<Room> roomsAvailableList = new ArrayList<>();
        for (Long roomId : roomIdList) {
            roomsAvailableList.add(roomService.readById(roomId));
        }

        int dayIn = dateIn.getDayOfYear();
        int dayOut = dateOut.getDayOfYear();
        int numberOfBookingDay = Math.abs(dayIn - dayOut);

        double valueOfAllAvailableRoomsPerNight = 0;

        for (Room room : roomsAvailableList) {
            valueOfAllAvailableRoomsPerNight += room.getPricePerRoom();
        }

        double totalValue = valueOfAllAvailableRoomsPerNight * numberOfBookingDay;

        bookingService.saveBooking(new Booking(dateIn, dateOut, totalValue, user, roomsAvailableList));
        BookingResponse bookingResponse = BookingMapper.mapToDto(new Booking(dateIn, dateOut, totalValue, user, roomsAvailableList));
        model.addAttribute("bookingResponse", bookingResponse);
        return "bookings-info";

    }


    @GetMapping("/{booking_id}/delete")
    // @PreAuthorize("hasAuthority('MANAGER')")
    public String delete(@PathVariable(value = "booking_id") long bookingId) {

        bookingService.delete(bookingId);
        return "redirect:/rooms/all";
    }


}
