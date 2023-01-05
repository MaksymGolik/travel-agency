package com.application.dao;

import com.application.model.Country;

import java.util.List;
import java.util.Optional;

public interface ICountryDAO {
    Optional<Country> findCountryById (long id);

    Optional<Country> findCountryByName (String name);

    void createCountry (Country country);
    void delete (Country country);
    void update (Country country);

    List<Country> findAll();
}
