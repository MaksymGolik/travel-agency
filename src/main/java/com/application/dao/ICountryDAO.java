package com.application.dao;

import com.application.model.Country;
import com.application.model.Hotel;

import java.util.Optional;

public interface ICountryDAO {
    Optional<Country> findCountryById (long id);

    Optional<Country> findCountryByName (String name);

    void createCountry (Country country);
    void delete (Country country);
    void update (Country country);
}
