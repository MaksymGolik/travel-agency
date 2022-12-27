package com.application.dao.implementation;

import com.application.dao.IUserDAO;
import com.application.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDAOImpl implements IUserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public void addUser(User user) {

    }
}
