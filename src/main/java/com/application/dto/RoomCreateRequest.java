package com.application.dto;

import com.application.model.Hotel;
import com.application.model.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@AllArgsConstructor
@NoArgsConstructor
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


}
