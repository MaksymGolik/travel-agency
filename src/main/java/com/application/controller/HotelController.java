package com.application.controller;


import com.application.dto.HotelCreateRequest;
import com.application.dto.mapper.HotelCreateRequestMapper;
import com.application.model.Hotel;
import com.application.service.ICountryService;
import com.application.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


 /*   @GetMapping(value = "/create")
    public ModelAndView create(@PathVariable(value = "country_id") long countryId) {
        ModelAndView mv = new ModelAndView();
        Hotel hotel = new Hotel();
        mv.setViewName("create_hotel");
        hotel.setCountry(countryService.readById(countryId));
        mv.getModel().put("hotel", hotel);
        return mv;
    }*/

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("hotel", new HotelCreateRequest());
        return "create-hotel";
    }

    /*@PostMapping(value = "/create")
    public String create(@PathVariable(value = "country_id") long countryId, HotelCreateRequest hotelCreateRequest) {

        Hotel hotel = HotelCreateRequestMapper.mapToModel(hotelCreateRequest);
        hotel.setCountry(countryService.readById(countryId));
        hotelService.saveHotel(hotel);
        return "home_page";
    }*/

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("hotel") HotelCreateRequest hotel, BindingResult result) {
        if (result.hasErrors()) {
            return "create-hotel";
        }
        Hotel hotel1 = HotelCreateRequestMapper.mapToModel(hotel);
        hotel1.setCountry(countryService.readByName(hotel.getCountry()));

        hotelService.saveHotel(hotel1);
        return "hotel_page";

    }


}
