package com.application.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class UserResponse {

    private String login;
    private String password;


}
