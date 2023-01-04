package com.application.dto;

import com.application.model.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class HotelResponse {


    private long id;
    private String name;
    private int starRating;
    private Country country;
}

