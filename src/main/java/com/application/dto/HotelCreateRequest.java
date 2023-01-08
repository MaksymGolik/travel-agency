package com.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelCreateRequest {


    @NotEmpty
    private String name;

    @NotNull
    @Positive
    private int starRating;


    @NotEmpty
    private String country;
}
