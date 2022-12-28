package com.application.service;

import com.application.dto.UserCreateRequest;
import com.application.dto.UserResponse;
import com.application.model.User;

import java.util.List;

public interface IUserService {
    void saveUser(UserCreateRequest userCreateRequest);
    User readById(long id);
    User readByEmail(String email);
    User update(User user);
    void delete(long id);
    List<User> getAll();
    UserResponse findByLoginAndPassword(UserCreateRequest userCreateRequest);
    boolean matchPassword(UserCreateRequest userCreateRequest);
}
