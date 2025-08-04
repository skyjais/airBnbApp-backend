package com.codingakash.projects.airBnbApp.service;

import com.codingakash.projects.airBnbApp.dto.HotelDto;
import com.codingakash.projects.airBnbApp.dto.HotelInfoDto;
import com.codingakash.projects.airBnbApp.entity.Hotel;

import java.util.List;

public interface HotelService {

    HotelDto createNewHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long id , HotelDto hotelDto);

   void deleteHotelById(Long hotelId);

   void activateHotel(Long hotelId);

    HotelInfoDto getHotelInfoById(Long hotelId);

    List<HotelDto> getAllHotels();
}
