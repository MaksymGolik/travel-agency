package com.application.controller;

import com.application.dto.BookingCreateRequest;
import com.application.dto.BookingResponse;
import com.application.dto.mapper.BookingMapper;
import com.application.model.Booking;
import com.application.model.Room;
import com.application.model.User;
import com.application.service.IBookingService;
import com.application.service.IRoomService;
import com.application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/*import java.awt.*;*/

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        if (roomIdList.size() == 0) {
            return "empty-reservation";

        }
        LocalDateTime dateIn = bookingCreateRequest.getDateIn();
        LocalDateTime dateOut = bookingCreateRequest.getDateOut();

        String email = principal.getName();
        User user = userService.readByEmail(email);

        List<Room> roomsAvailableList = new ArrayList<>();

        for (Long roomId : roomIdList) {
            roomsAvailableList.add(roomService.readById(roomId));
        }


        int differenceOfYears = dateOut.getYear() - dateIn.getYear();
        int dayIn = dateIn.getDayOfYear();
        int dayOut = dateOut.getDayOfYear() + differenceOfYears * 365;
        int numberOfBookingDay = dayOut - dayIn;


        double valueOfAllAvailableRoomsPerNight = 0;

        for (Room room : roomsAvailableList) {
            valueOfAllAvailableRoomsPerNight += room.getPricePerRoom();
        }

        double totalValue = valueOfAllAvailableRoomsPerNight * numberOfBookingDay;

        bookingService.saveBooking(new Booking(dateIn, dateOut, totalValue, user, roomsAvailableList));
        BookingResponse bookingResponse = BookingMapper.mapToDto(new Booking(dateIn, dateOut, totalValue, user, roomsAvailableList));
        model.addAttribute("bookingResponse", bookingResponse);

        return "booking-info-for-new";

    }


    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{booking_id}/delete")

    public String delete(@PathVariable(value = "booking_id") long bookingId) {

        bookingService.delete(bookingId);
        return "redirect:/bookings/all";
    }
    @GetMapping("/{booking_id}/delete/user/{user_id}")

    public String deleteForUser(@PathVariable(value = "booking_id") long bookingId,
                                @PathVariable(value = "user_id") long userId) {

        bookingService.delete(bookingId);
        return "redirect:/bookings/all/users/{user_id}";
    }




    @PreAuthorize("hasAuthority('MANAGER') " +
            "or authentication.principal.id==@bookingServiceImpl.readById(#bookingId).user.id")
    @GetMapping("/{booking_id}")
    public String get(Model model, @PathVariable(value = "booking_id") long bookingId) {

        model.addAttribute("booking", bookingService.readById(bookingId));
        model.addAttribute("bookings", bookingService.readAll().stream()
                .map(BookingMapper::mapToDto).collect(Collectors.toList()));
        return "booking-info-for-existing";
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/all")
    public String getAll(Model model) {

        model.addAttribute("bookings", bookingService.readAll().stream()
                .map(BookingMapper::mapToDto).collect(Collectors.toList()));
        return "bookings-list";
    }

    @PreAuthorize("hasAuthority('MANAGER') or #userId==authentication.principal.id")
    @GetMapping("/all/users/{user_id}")
    public String getAllForUser(Model model, @PathVariable(value = "user_id") long userId) {

        model.addAttribute("bookings_user", bookingService.readByUserId(userId).stream()
                .map(BookingMapper::mapToDto).collect(Collectors.toList()));
        return "bookings-list-for-user";
    }


}
