package com.application.dto;


import com.application.model.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomCreateRequest {


    //@NotNull
    private int numberOfRoom;

  //  @NotNull
    private int peopleCapacity;


   // @NotNull
    private double pricePerRoom;


   // @NotEmpty
    private RoomType roomType;


    @NotEmpty
    private String hotel;




}
