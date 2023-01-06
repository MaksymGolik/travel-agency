package com.application.controller;


import com.application.dto.CountryCreateRequest;
import com.application.dto.HotelCreateRequest;
import com.application.dto.mapper.CountryMapper;
import com.application.dto.mapper.HotelMapper;
import com.application.model.Country;
import com.application.model.Hotel;
import com.application.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("country", new CountryCreateRequest());
        return "create-country";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("country") CountryCreateRequest countryCreateRequest, BindingResult result) {
        if (result.hasErrors()) {
            return "create-country";
        }
        Country country = CountryMapper.mapToModel(countryCreateRequest);
        countryService.saveCountry(country);
        return "redirect:/countries-list";
    }
}