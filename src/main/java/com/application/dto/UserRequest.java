package com.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;



@AllArgsConstructor
@Data

public class UserRequest {
    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}
