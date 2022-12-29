package com.application.dao.implementation;

import com.application.dao.IUserDAO;
import com.application.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userDAOImpl")
public class UserDAOImpl implements IUserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select u from User u where u.email=:email",User.class)
                    .setParameter("email", email).getResultStream().findFirst();

        }
    }

    @Override
    public Optional<User> findUserById(long id) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select u from User u where u.id=:id",User.class)
                    .setParameter("id", id).getResultStream().findFirst();

        }
    }

    @Override
    public void addUser(User user) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(User user) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(User user) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(user);
        }
    }
}