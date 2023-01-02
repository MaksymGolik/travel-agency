package com.application.service;

import com.application.model.Country;


public interface ICountryService {


    void saveCountry(Country country);

    Country readById(long id);

    Country readByName(String name);

    void update(Country country);

    void delete(long id);
}
