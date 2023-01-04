package com.application.dao;

import com.application.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(long id);
    void addUser(User user);

    void update(User user);
    void delete(User user);
    List<User> findAll();
}
