package com.application.dto.mapper;

import com.application.dto.RoomCreateRequest;
import com.application.dto.RoomResponse;
import com.application.model.Room;


public class RoomMapper {

    public static Room mapToModel(RoomCreateRequest roomCreateRequest){
        return Room.builder()
                .numberOfRoom(roomCreateRequest.getNumberOfRoom())
                .peopleCapacity(roomCreateRequest.getPeopleCapacity())
                .pricePerRoom(roomCreateRequest.getPricePerRoom())
                .roomType(roomCreateRequest.getRoomType())
                .build();

    }


    public static RoomResponse mapToDto(Room room){
        return RoomResponse.builder()
                .id(room.getId())
                .numberOfRoom(room.getNumberOfRoom())
                .pricePerRoom(room.getPricePerRoom())
                .peopleCapacity(room.getPeopleCapacity())
                .roomType(room.getRoomType())
                .hotel(room.getHotel())
                .build();
    }




}
