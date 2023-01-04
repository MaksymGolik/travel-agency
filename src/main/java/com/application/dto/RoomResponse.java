package com.application.dto;

import com.application.model.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class RoomResponse {


    private long id;

    private int numberOfRoom;

    private int peopleCapacity;

    private double pricePerRoom;

    private RoomType roomType;
}
