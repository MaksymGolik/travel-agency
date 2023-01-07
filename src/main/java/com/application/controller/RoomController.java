package com.application.controller;

import com.application.dto.RoomCreateRequest;
import com.application.dto.mapper.HotelMapper;
import com.application.dto.mapper.RoomMapper;
import com.application.model.Hotel;
import com.application.model.Room;
import com.application.service.IHotelService;
import com.application.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


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
        return "room-list-for-hotel";

    }





  //  @PreAuthorize(value = "hasAuthority('MANAGER')")
/*    @GetMapping(value = "/create")
    public ModelAndView addRoom(@PathVariable(value = "hotel_id") long hotelId){
        ModelAndView mv = new ModelAndView();
        Room room = new Room();
        mv.setViewName("create-room");
        room.setHotel(hotelService.readById(hotelId));
        mv.getModel().put("room", room);

        return mv;
    }*/

    // @PreAuthorize(value = "hasAuthority('MANAGER')")
  /*  @PostMapping(value = "/create")
    public String addRoom( @PathVariable(value = "hotel_id") long hotelId, RoomCreateRequest roomCreateRequest ){

        Room room = RoomMapper.mapToModel(roomCreateRequest);
        room.setHotel(hotelService.readById(hotelId));
        roomService.saveRoom(room);

        return "hotel-page";
    }*/



    @DeleteMapping("/{room_id}/delete")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String delete(@PathVariable(value = "room_id") long roomId) {
        roomService.delete(roomId);
        return "room-list-for-hotel";
    }




}
