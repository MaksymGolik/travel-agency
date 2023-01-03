package com.application.dto;

import com.application.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class UserResponse {
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Role role;
}
