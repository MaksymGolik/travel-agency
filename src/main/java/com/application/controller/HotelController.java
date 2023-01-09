package com.application.controller;


import com.application.dto.HotelCreateRequest;
import com.application.dto.mapper.HotelMapper;
import com.application.dto.mapper.RoomMapper;
import com.application.exception.CustomAccessDeniedHandler;
import com.application.model.Hotel;
import com.application.service.ICountryService;
import com.application.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

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


    @GetMapping("/all")
    public String getAll(Model model) {

        model.addAttribute("hotels", hotelService.readAll().stream()
                .map(HotelMapper::mapToDto).collect(Collectors.toList()));
        return "hotels-list";
    }

    @GetMapping("/all/rooms/{hotel_id}")
    public String getAllRoomInHotel(Model model, @PathVariable(name = "hotel_id") long hotelId) {

        model.addAttribute("rooms", hotelService.readAllRoomsInHotel(hotelId).stream()
                .map(RoomMapper::mapToDto).collect(Collectors.toList()));
        model.addAttribute("hotel", hotelService.readById(hotelId));
        return "room-list-for-hotel";
    }


    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("countries", countryService.readAll());
        model.addAttribute("hotel", new HotelCreateRequest());
        return "create-hotel";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("hotel") HotelCreateRequest hotelCreateRequest,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "create-hotel";
        }
        Hotel hotel = HotelMapper.mapToModel(hotelCreateRequest);
        hotel.setCountry(countryService.readByName(hotelCreateRequest.getCountry()));

        hotelService.saveHotel(hotel);
        return "redirect:/hotels/all";

    }

    @GetMapping("/{hotel_id}/update")
    public String update(Model model,
                         @PathVariable(name = "hotel_id") long hotelId) {

        model.addAttribute("hotel", hotelService.readById(hotelId));
        return "update-hotel";
    }


    @PostMapping("/{hotel_id}/update")
    public String update(@Valid @ModelAttribute("hotel") Hotel hotel,
                         @PathVariable(name = "hotel_id") long hotelId,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "update-hotel";
        }
        Hotel oldHotel = hotelService.readById(hotelId);
        oldHotel.setName(hotel.getName());
        oldHotel.setStarRating(hotel.getStarRating());
        hotelService.update(oldHotel);

        return "redirect:/hotels/all";
    }

    @GetMapping("/{hotel_id}/delete")
    //   @PreAuthorize("hasAuthority('MANAGER')")
    public String delete(@PathVariable(value = "hotel_id") long hotelId) {

        hotelService.delete(hotelId);
        return "redirect:/hotels/all";
    }

















    /*@PostMapping(value = "/create")
    public String create(@PathVariable(value = "country_id") long countryId, HotelCreateRequest hotelCreateRequest) {

        Hotel hotel = HotelCreateRequestMapper.mapToModel(hotelCreateRequest);
        hotel.setCountry(countryService.readById(countryId));
        hotelService.saveHotel(hotel);
        return "home_page";
    }*/


    /*   @GetMapping(value = "/create")
    public ModelAndView create(@PathVariable(value = "country_id") long countryId) {
        ModelAndView mv = new ModelAndView();
        Hotel hotel = new Hotel();
        mv.setViewName("create_hotel");
        hotel.setCountry(countryService.readById(countryId));
        mv.getModel().put("hotel", hotel);
        return mv;
    }*/

}
