package com.application.dto.mapper;

import com.application.dto.BookingResponse;
import com.application.model.Booking;

import java.util.stream.Collectors;

public class BookingMapper {
    public static BookingResponse mapToDto(Booking booking){
        return BookingResponse.builder()
                .id(booking.getId())
                .dateIn(booking.getDateIn())
                .dateOut(booking.getDateOut())
                .totalPrice(booking.getTotalPrice())
                .guest(UserResponseMapper.mapToDto(booking.getUser()))
                .rooms(booking.getRooms().stream().map(RoomMapper::mapToDto).collect(Collectors.toList()))
                .build();
    }
}
