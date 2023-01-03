package com.application.dto.mapper;

import com.application.dto.UserResponse;
import com.application.model.User;

public class UserResponseMapper {
    public static UserResponse mapToDto(User user){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();
    }
}
