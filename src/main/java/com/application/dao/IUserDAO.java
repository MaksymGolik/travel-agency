package com.application.dao;

import com.application.model.User;

import java.util.Optional;

public interface IUserDAO {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(long id);
    void addUser(User user);
}