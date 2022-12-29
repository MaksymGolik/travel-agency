package com.application.service.impl;

import com.application.dao.IUserDAO;
import com.application.exception.EntityNotFoundException;
import com.application.model.User;
import com.application.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Optional;




@Service("userServiceImpl")
@Slf4j
public class UserServiceImpl implements IUserService {
    private IUserDAO userDAO;

    @Autowired
    public UserServiceImpl(@Qualifier("userDAOImpl") IUserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public void saveUser(User user) {
        String email = user.getEmail();
        if(userDAO.findUserByEmail(email).isPresent())
            throw new KeyAlreadyExistsException("User with this email is already exists");
        userDAO.addUser(user);
    }

    @Override
    public User readById(long id) {
        Optional<User> user = userDAO.findUserById(id);
        if (user.isEmpty()) {
            log.warn("IN readById - no user found by id: {}", id);
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        log.info("IN readById - user: {} found by id: {}", user.get(), id);
        return user.get();
    }

    @Override
    public User readByEmail(String email) {
        Optional<User> user = userDAO.findUserByEmail(email);
        if (user.isEmpty()) {
            log.warn("IN readByEmail - no user found by email: {}", email);
            throw new EntityNotFoundException("User with email " + email + " not found");
        }
        log.info("IN readByEmail - user: {} found by email: {}", user.get(), email);
        return user.get();
    }

    @Override
    public void update(User user) {
        long id = user.getId();
        if(userDAO.findUserById(id).isEmpty())
            throw new EntityNotFoundException("User with id "+ id + " not found");
        userDAO.update(user);
    }

    @Override
    public void delete(long id) {
        User user = userDAO.findUserById(id).orElseThrow(()->
                        new EntityNotFoundException("User with id "+ id + " not found"));
        userDAO.delete(user);
    }
}