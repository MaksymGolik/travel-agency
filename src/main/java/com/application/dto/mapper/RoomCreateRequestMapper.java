package com.application.dto.mapper;

import com.application.dto.RoomCreateRequest;
import com.application.model.Room;


public class RoomCreateRequestMapper {

    public static Room mapToModel(RoomCreateRequest roomCreateRequest){
        return Room.builder()
                .numberOfRoom(roomCreateRequest.getNumberOfRoom())
                .peopleCapacity(roomCreateRequest.getPeopleCapacity())
                .pricePerRoom(roomCreateRequest.getPricePerRoom())
                .roomType(roomCreateRequest.getRoomType())
                .build();

    }


}
