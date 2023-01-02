package com.application.controller;


import com.application.dto.HotelCreateRequest;
import com.application.dto.mapper.HotelCreateRequestMapper;
import com.application.model.Hotel;
import com.application.service.ICountryService;
import com.application.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    IHotelService hotelService;
    ICountryService countryService;


    @Autowired
    public HotelController(IHotelService hotelService, ICountryService countryService) {
        this.hotelService = hotelService;
        this.countryService = countryService;
    }


    @GetMapping(value = "/create")
    public ModelAndView create(@PathVariable(value = "country_id") long countryId) {
        ModelAndView mv = new ModelAndView();
        Hotel hotel = new Hotel();
        mv.setViewName("create_hotel");
        hotel.setCountry(countryService.readById(countryId));
        mv.getModel().put("hotel", hotel);
        return mv;
    }

    @PostMapping(value = "/create")
    public String create(@PathVariable(value = "country_id") long countryId, HotelCreateRequest hotelCreateRequest) {

        Hotel hotel = HotelCreateRequestMapper.mapToModel(hotelCreateRequest);
        hotel.setCountry(countryService.readById(countryId));
        hotelService.saveHotel(hotel);
        return "home_page";
    }


}
