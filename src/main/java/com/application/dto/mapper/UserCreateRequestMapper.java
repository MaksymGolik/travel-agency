package com.application.dto.mapper;

import com.application.dto.UserCreateRequest;
import com.application.model.User;

public class UserCreateRequestMapper {
    public static User mapToModel(UserCreateRequest userCreateRequest){
        return User.builder()
                .email(userCreateRequest.getEmail())
                .password(userCreateRequest.getPassword())
                .firstName(userCreateRequest.getFirstName())
                .lastName(userCreateRequest.getLastName())
                .phoneNumber(userCreateRequest.getPhoneNumber())
                .build();
    }
}
