package com.application.dto.mapper;

import com.application.dto.HotelCreateRequest;
import com.application.model.Hotel;

public class HotelCreateRequestMapper {

    public static Hotel mapToModel(HotelCreateRequest hotelCreateRequest){
        return Hotel.builder()
                .name(hotelCreateRequest.getName())
                .starRating(hotelCreateRequest.getStarRating())
                .country(hotelCreateRequest.getCountry())
                .build();
    }

}
