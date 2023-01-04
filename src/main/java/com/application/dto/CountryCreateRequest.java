package com.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class CountryCreateRequest {

    @NotEmpty
    private String name;

}
