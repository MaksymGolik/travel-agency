package com.application.controller;

import com.application.dto.BookingCreateRequest;
import com.application.dto.RoomResponse;
import com.application.dto.SearchAvailableRoomsRequest;
import com.application.dto.RoomCreateRequest;
import com.application.dto.mapper.RoomMapper;
import com.application.exception.CustomAccessDeniedException;
import com.application.model.Room;
import com.application.service.ICountryService;
import com.application.service.IHotelService;
import com.application.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/rooms")
public class RoomController {

    IRoomService roomService;
    IHotelService hotelService;
    ICountryService countryService;



    @Autowired
    public RoomController(IRoomService roomService,
                          IHotelService hotelService,
                          ICountryService countryService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
        this.countryService = countryService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("hotels", hotelService.readAll());
        model.addAttribute("room", new RoomCreateRequest());
        return "create-room";
    }


    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("room") RoomCreateRequest roomCreateRequest,
                         BindingResult result ) {
        if (result.hasErrors()) {
            return "create-room";
        }
        Room room   = RoomMapper.mapToModel(roomCreateRequest);
        room.setHotel(hotelService.readByName(roomCreateRequest.getHotel()));
        roomService.saveRoom(room);
        return "redirect:/rooms/all";

    }

    @GetMapping("/all")
    public String getAll(Model model) {

        model.addAttribute("rooms", roomService.readAll().stream()
                .map(RoomMapper::mapToDto).collect(Collectors.toList()));
        return "rooms-list";
    }


    @GetMapping("/{room_id}/update")
    public String update(Model model,
                         @PathVariable(name = "room_id") long roomId) {

        model.addAttribute("room", roomService.readById(roomId));
        return "update-room";
    }


    @PostMapping("/{room_id}/update")
    public String update(@Valid @ModelAttribute("room") Room room,
                         @PathVariable(name = "room_id") long roomId,
                         BindingResult result   )

    {
        if (result.hasErrors()) {
            return "update-room";
        }
        Room oldRoom = roomService.readById(roomId);
        oldRoom.setNumberOfRoom(room.getNumberOfRoom());
        oldRoom.setPeopleCapacity(room.getPeopleCapacity());
        oldRoom.setPricePerRoom(room.getPricePerRoom());
        oldRoom.setRoomType(room.getRoomType());
        roomService.update(oldRoom);
        return "redirect:/rooms/all";
    }

    @GetMapping("/{room_id}/delete")
     // @PreAuthorize("hasAuthority('MANAGER')")
    public String delete(@PathVariable(value = "room_id") long roomId) {

        roomService.delete(roomId);
        return "redirect:/rooms/all";
    }

    @GetMapping("/search_available")
    public String searchAvailable(Model model){
        model.addAttribute("countries", countryService.readAll());
        model.addAttribute("hotels", hotelService.readAll());
        model.addAttribute("criteria", new SearchAvailableRoomsRequest());
        model.addAttribute("rooms",roomService.readAll());


        return "search-rooms";
    }

    @PostMapping("/search_available")
    public String searchAvailable(@ModelAttribute("period") SearchAvailableRoomsRequest searchAvailableRoomsRequest,
                                  Model model){
        List<Room> rooms;
        LocalDateTime dateIn = LocalDate.parse(searchAvailableRoomsRequest.getDateIn()).atTime(14,0,0);
        LocalDateTime dateOut = LocalDate.parse(searchAvailableRoomsRequest.getDateOut()).atTime(12,0,0);
        String country = searchAvailableRoomsRequest.getCountry();
        String hotel = searchAvailableRoomsRequest.getHotelName();

        if (dateIn.isAfter(dateOut) ) {
            throw new CustomAccessDeniedException("Date check in is after or is the same as date check out. Please, choose correct range of date. ");
        }
        if (dateIn.isBefore(LocalDateTime.now())) {
            throw new CustomAccessDeniedException("Date check in is before now. You can not to reservation in the past. Please, choose correct range of date. ");
        }

        if(!hotel.isEmpty() && !country.isEmpty()){
            rooms = roomService.findAvailableByCountryHotelPeriod(country,hotel,dateIn,dateOut);
        } else if(!country.isEmpty()){
            rooms = roomService.findAvailableByCountryPeriod(country,dateIn,dateOut);
        } else rooms = roomService.findAvailableByPeriod(dateIn,dateOut);

        // TODO

     //   Map<RoomResponse,List<Long>> catalog = RoomMapper.mapToRoomCatalog(rooms);

        model.addAttribute("rooms", rooms.stream().map(RoomMapper::mapToDto).collect(Collectors.toList()));
        BookingCreateRequest booking = new BookingCreateRequest();
        booking.setDateIn(dateIn);
        booking.setDateOut(dateOut);
        model.addAttribute("booking", booking);
        return "rooms-list-available-for-booking";
    }
}
