package com.application.service.impl;

import com.application.dto.UserRequest;
import com.application.dto.UserResponse;
import com.application.model.User;
import com.application.repository.UserRepository;
import com.application.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




@Service("userServiceImpl")
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

  //  private PasswordEncoder passwordEncoder;
    @Override
    public User create(User user) {

        String email = user.getEmail();
        if (userRepository.getUserByEmail(email).getEmail().equals(email)) {
            userRepository.save(user);
            return userRepository.getUserByEmail(email);
        } else throw new KeyAlreadyExistsException("User with this email is already exists");
    }

    @Override
    public boolean saveUser(UserRequest userRequest) {

        return false;
    }

    @Override
    public User readById(long id) {
        Optional<User> optional = userRepository.findById(id);
        if(optional.isEmpty()) {return null;}
        return optional.get();
    }

    @Override
    public User readByEmail(String email) {
        User result = userRepository.getUserByEmail(email);
        if (result == null) {
            log.warn("IN readByEmail - no user found by email: {}", email);
            return null;
        }
        log.info("IN readByEmail - user: {} found by email: {}", result, email);
        return result;
    }

    @Override
    public User update(User user) {
        User oldUser = readById(user.getId());
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        User user = readById(id);
        userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    @Override
    public UserResponse findByLoginAndPassword(UserRequest userRequest) {
        return null;
    }

    @Override
    public boolean matchPassword(UserRequest userRequest) {
        return false;
    }
}
