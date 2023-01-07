package com.application.controller;

import com.application.dto.RoomCreateRequest;
import com.application.dto.mapper.RoomMapper;
import com.application.model.Room;
import com.application.service.IHotelService;
import com.application.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/rooms")
public class RoomController {

    IRoomService roomService;

    IHotelService hotelService;



    @Autowired
    public RoomController(IRoomService roomService, IHotelService hotelService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
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
    //   @PreAuthorize("hasAuthority('MANAGER')")
    public String delete(@PathVariable(value = "room_id") long roomId) {
        roomService.delete(roomId);
        return "redirect:/rooms/all";
    }


}
