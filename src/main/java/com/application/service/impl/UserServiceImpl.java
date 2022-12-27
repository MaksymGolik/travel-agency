package com.application.service.impl;

import com.application.dao.IUserDAO;
import com.application.dto.UserCreateRequest;
import com.application.dto.UserResponse;
import com.application.exception.EntityNotFoundException;
import com.application.model.User;
import com.application.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




@Service("userServiceImpl")
@Slf4j
public class UserServiceImpl implements IUserService {
    private IUserDAO userDAO;

    @Autowired
    public UserServiceImpl(IUserDAO userDAO){
        this.userDAO = userDAO;
    }

    //private PasswordEncoder passwordEncoder;
    @Override
    public User create(User user) {
        String email = user.getEmail();
        if(userDAO.findUserByEmail(email).isPresent())
            throw new KeyAlreadyExistsException("User with this email is already exists");
        userDAO.addUser(user);
        return userDAO.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));
    }

    @Override
    public boolean saveUser(UserCreateRequest userCreateRequest) {

        return false;
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
    public User update(User user) {
//        User oldUser = readById(user.getId());
//        return userRepository.save(user);
        return new User();
    }

    @Override
    public void delete(long id) {
//        User user = readById(id);
//        userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
//        List<User> users = userRepository.findAll();
//        return users.isEmpty() ? new ArrayList<>() : users;
        return new ArrayList<>();
    }

    @Override
    public UserResponse findByLoginAndPassword(UserCreateRequest userCreateRequest) {
        return null;
    }

    @Override
    public boolean matchPassword(UserCreateRequest userCreateRequest) {
        return false;
    }
}
