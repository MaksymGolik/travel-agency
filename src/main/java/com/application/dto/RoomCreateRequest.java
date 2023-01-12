package com.application.dto;


import com.application.model.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomCreateRequest {


    @NotNull
    @Positive
    private int numberOfRoom;

    @NotNull
    @Min(0)
    @Max(6)
    private int peopleCapacity;


    @NotNull
    @Positive
    private double pricePerRoom;

    @NotNull
    private RoomType roomType;

    @NotEmpty
    private String hotel;
}