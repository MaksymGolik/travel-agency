package com.application.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelCreateRequest {


    @NotEmpty
    private String name;

    @NotEmpty
    private int starRating;
}
