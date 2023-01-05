package com.application.dto.mapper;


import com.application.dto.CountryCreateRequest;
import com.application.dto.CountryResponse;
import com.application.model.Country;

public class CountryMapper {


    public static Country mapToModel(CountryCreateRequest countryCreateRequest){
        return Country.builder()
                .name(countryCreateRequest.getName())
                .build();
    }


    public static CountryResponse mapToDto(Country country){
        return CountryResponse.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }



}
