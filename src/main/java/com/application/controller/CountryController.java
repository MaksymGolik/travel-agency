package com.application.controller;


import com.application.dto.mapper.CountryMapper;
import com.application.dto.mapper.HotelMapper;
import com.application.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/countries")
public class CountryController {

    ICountryService countryService;

    @Autowired
    public CountryController(ICountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    public String getAll(Model model) {

        model.addAttribute("countries", countryService.readAll().stream()
                .map(CountryMapper::mapToDto).collect(Collectors.toList()));
        return "countries-list";
    }

    @GetMapping("/all/hotels/{country_id}")
    public String getAllHotelInCountry (Model model,  @PathVariable(name = "country_id") long countryId) {

        model.addAttribute("hotels", countryService.readAllHotelsInCountry(countryId).stream()
                .map(HotelMapper::mapToDto).collect(Collectors.toList()));
        model.addAttribute("country", countryService.readById(countryId));
        return "hotel-list-for-country";
    }


}
