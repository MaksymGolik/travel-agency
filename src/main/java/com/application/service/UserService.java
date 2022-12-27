package com.application.service;

import com.application.dto.UserRequest;
import com.application.dto.UserResponse;
import com.application.model.User;

import java.util.List;

public interface UserService {
    User create(User user);
    boolean saveUser(UserRequest userRequest);
    User readById(long id);
    User readByEmail(String email);
    User update(User user);
    void delete(long id);
    List<User> getAll();
    UserResponse findByLoginAndPassword(UserRequest userRequest);
    boolean matchPassword(UserRequest userRequest);
}
