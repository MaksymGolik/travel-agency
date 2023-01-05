package com.application.service.impl;

import com.application.dao.ICountryDAO;
import com.application.dao.IHotelDAO;
import com.application.exception.EntityNotFoundException;
import com.application.model.Country;
import com.application.model.Hotel;
import com.application.service.ICountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CountryServiceImpl implements ICountryService {
    private ICountryDAO countryDAO;

    private IHotelDAO hotelDAO;

    @Autowired
    public CountryServiceImpl(@Qualifier("countryDAOImpl") ICountryDAO countryDAO, IHotelDAO hotelDAO) {
        this.countryDAO = countryDAO;
        this.hotelDAO = hotelDAO;
    }


    @Override
    public void saveCountry(Country country) {
        long id = country.getId();
        if (countryDAO.findCountryById(id).isPresent())
            throw new KeyAlreadyExistsException("Country with this id is already exists");
        countryDAO.createCountry(country);
    }

    @Override
    public Country readById(long id) {
        Optional<Country> country = countryDAO.findCountryById(id);
        if (country.isEmpty()) {
            log.warn("IN readById - no country found by id: {}", id);
            throw new EntityNotFoundException("Country with id " + id + " not found");
        }
        log.info("IN readById - country: {} found by id: {}", country.get(), id);
        return country.get();
    }

    @Override
    public Country readByName(String name) {
        Optional<Country> country = countryDAO.findCountryByName(name);
        if (country.isEmpty()) {
            log.warn("IN readByName - no country found by name: {}", name);
            throw new EntityNotFoundException("Country with name " + name + " not found");
        }
        log.info("IN readByName - country: {} found by name: {}", country.get(), name);
        return country.get();
    }

    @Override
    public void update(Country country) {
        long id = country.getId();
        if (countryDAO.findCountryById(id).isEmpty())
            throw new EntityNotFoundException("Country with id " + id + " not found");
        countryDAO.update(country);
    }

    @Override
    public void delete(long id) {
        Country country = countryDAO.findCountryById(id).orElseThrow(() ->
                new EntityNotFoundException("Country with id " + id + " not found"));
        countryDAO.delete(country);
    }

    @Override
    public List<Country> readAll() {
        return countryDAO.findAll();
    }

    @Override
    public List<Hotel> readAllHotelsInCountry(long id) {
       List<Hotel> hotelList = hotelDAO.findAll();
       List<Hotel> listOfHotelsInCountry = new ArrayList<>();
       for (Hotel hotel : hotelList) {
           if ( hotel.getCountry().getId() == id ) {
               listOfHotelsInCountry.add(hotel);
           }
       }
        return listOfHotelsInCountry;
    }


}
