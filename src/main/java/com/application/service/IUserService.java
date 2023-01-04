package com.application.service;

import com.application.model.User;

import java.util.List;

public interface IUserService {
    void saveUser(User user);
    User readById(long id);
    User readByEmail(String email);
    void update(User user);
    void delete(long id);
    List<User> readAll();
}