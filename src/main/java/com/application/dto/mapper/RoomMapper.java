package com.application.dto.mapper;

import com.application.dto.RoomCreateRequest;
import com.application.dto.RoomResponse;
import com.application.model.Room;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


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

    public static Map<RoomResponse, List<Long>> mapToRoomCatalog(List<Room> rooms){
        Map<RoomResponse, List<Long>> catalog = new LinkedHashMap<>();
        for(RoomResponse room : rooms
                .stream().map(RoomMapper::mapToDto).collect(Collectors.toList())){
            if(!catalog.containsKey(room)){
                catalog.put(room, List.of(room.getId()));
            } else{
                List<Long> roomId = new ArrayList<>(catalog.get(room));
                roomId.add(room.getId());
                catalog.replace(room,roomId);
            }
        }
        return catalog;
    }
}