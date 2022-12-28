package com.application.dto;

import com.application.model.Hotel;
import com.application.model.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@AllArgsConstructor
@Data
public class RoomCreateRequest {


    @NotEmpty
    private int numberOfRoom;

    @NotEmpty
    private int peopleCapacity;


    @NotEmpty
    private double pricePerRoom;


    @NotEmpty
    private RoomType roomType;


    @NotEmpty
    private Hotel hotel;
}
