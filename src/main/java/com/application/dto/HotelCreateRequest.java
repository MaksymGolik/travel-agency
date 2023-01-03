package com.application.dto;
import com.application.model.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelCreateRequest {


    @NotEmpty
    private String name;

    @NotNull
    private int starRating;


    @NotEmpty
    private String country;
}
