package com.application.dto.mapper;

import com.application.dao.ICountryDAO;
import com.application.dto.HotelCreateRequest;
import com.application.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;

public class HotelCreateRequestMapper {


    ICountryDAO iCountryDAO;

    @Autowired
    public HotelCreateRequestMapper(ICountryDAO iCountryDAO) {
        this.iCountryDAO = iCountryDAO;
    }

    public static Hotel mapToModel(HotelCreateRequest hotelCreateRequest){
        return Hotel.builder()
                .name(hotelCreateRequest.getName())
                .starRating(hotelCreateRequest.getStarRating())
                .build();
    }

}
