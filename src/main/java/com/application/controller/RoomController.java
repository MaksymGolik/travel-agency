package com.application.controller;


import com.application.dto.RoomCreateRequest;
import com.application.dto.UserCreateRequest;
import com.application.model.Room;
import com.application.service.IRoomService;
import com.application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class RoomController {


    @Autowired
    IRoomService roomService;


    @GetMapping(value = "/{id}")

    public String addRoom(@PathVariable String id){
       // roomService.saveRoom(new RoomCreateRequest();
        return "home_page";
    }


}
