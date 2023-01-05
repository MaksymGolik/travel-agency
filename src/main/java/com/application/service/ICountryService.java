package com.application.service;

import com.application.model.Country;
import com.application.model.Hotel;


import java.util.List;


public interface ICountryService {


    void saveCountry(Country country);

    Country readById(long id);

    Country readByName(String name);

    void update(Country country);

    void delete(long id);

    List<Country> readAll();

    List<Hotel> readAllHotelsInCountry(long id);
}
