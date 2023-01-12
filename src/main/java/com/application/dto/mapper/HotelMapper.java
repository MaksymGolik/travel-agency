package com.application.dto.mapper;

import com.application.dto.HotelCreateRequest;
import com.application.dto.HotelResponse;
import com.application.model.Hotel;

public class HotelMapper {

    public static Hotel mapToModel(HotelCreateRequest hotelCreateRequest){
        return Hotel.builder()
                .name(hotelCreateRequest.getName())
                .starRating(hotelCreateRequest.getStarRating())
                .build();
    }

    public static HotelResponse mapToDto(Hotel hotel){
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .starRating(hotel.getStarRating())
                .country(hotel.getCountry())
                .build();
    }

}
