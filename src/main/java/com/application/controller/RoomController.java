package com.application.controller;

import com.application.dto.RoomCreateRequest;
import com.application.dto.mapper.RoomMapper;
import com.application.model.Room;
import com.application.service.IHotelService;
import com.application.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/hotels/{hotel_id}/rooms")
public class RoomController {

    IRoomService roomService;

    IHotelService hotelService;



    @Autowired
    public RoomController(IRoomService roomService, IHotelService hotelService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
    }

  //  @PreAuthorize(value = "hasAuthority('MANAGER')")
    @GetMapping(value = "/create")
    public ModelAndView addRoom(@PathVariable(value = "hotel_id") long hotelId){
        ModelAndView mv = new ModelAndView();
        Room room = new Room();
        mv.setViewName("add_room");
        room.setHotel(hotelService.readById(hotelId));
        mv.getModel().put("room", room);

        return mv;
    }

    // @PreAuthorize(value = "hasAuthority('MANAGER')")
    @PostMapping(value = "/create")
    public String addRoom( @PathVariable(value = "hotel_id") long hotelId, RoomCreateRequest roomCreateRequest ){

        Room room = RoomMapper.mapToModel(roomCreateRequest);
        room.setHotel(hotelService.readById(hotelId));
        roomService.saveRoom(room);

        return "redirect:/hotel_page";
    }



    @DeleteMapping("/{room_id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String delete(@PathVariable(value = "room_id") long roomId, @PathVariable String hotel_id) {
        roomService.delete(roomId);
        return "redirect:/hotel_page";
    }


}
