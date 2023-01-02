package com.application.dao.implementation;

import com.application.dao.ICountryDAO;
import com.application.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository("countryDAOImpl")
public class CountryDAOImpl implements ICountryDAO {

    private final SessionFactory sessionFactory;

    public CountryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Country> findCountryById(long id) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select c from Country c where c.id=:id", Country.class)
                    .setParameter("id", id).getResultStream().findFirst();
        }
    }

    @Override
    public Optional<Country> findCountryByName(String name) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select c from Country c where c.name=:name", Country.class)
                    .setParameter("name", name).getResultStream().findFirst();
        }
    }

    @Override
    public void createCountry(Country country) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(country);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Country country) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(country);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Country country) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(country);
            session.getTransaction().commit();
        }
    }
}
