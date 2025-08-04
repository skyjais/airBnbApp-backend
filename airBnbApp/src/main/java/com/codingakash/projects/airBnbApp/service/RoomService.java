package com.codingakash.projects.airBnbApp.service;

import com.codingakash.projects.airBnbApp.dto.HotelDto;
import com.codingakash.projects.airBnbApp.dto.RoomDto;

import java.util.List;

public interface RoomService {

    RoomDto createNewRoom(Long hotelId ,RoomDto roomDto);

    List<RoomDto> getAllRoomInHotel(Long hotelId);

    RoomDto getRoomById(Long roomId);

    void deleteRoomById(Long roomId);

    RoomDto updateRoomById(Long hotelId, Long roomId, RoomDto roomDto);
}
