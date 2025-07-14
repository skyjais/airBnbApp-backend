package com.codingakash.projects.airBnbApp.controller;

import com.codingakash.projects.airBnbApp.dto.HotelDto;
import com.codingakash.projects.airBnbApp.dto.RoomDto;
import com.codingakash.projects.airBnbApp.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomAdminController {

private final RoomService roomService;

@PostMapping
public ResponseEntity<RoomDto> createNewRoom(@PathVariable Long hotelId ,
                                             @RequestBody RoomDto roomDto){

    RoomDto room = roomService.createNewRoom(hotelId,roomDto);
    return new ResponseEntity<>(room, HttpStatus.CREATED);

}

@GetMapping
    public ResponseEntity<List<RoomDto>> getAllRoomsInHotel(@PathVariable Long hotelId){
    return ResponseEntity.ok(roomService.getAllRoomInHotel(hotelId));
}


@GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long roomId){
    return ResponseEntity.ok(roomService.getRoomById(roomId));
}


@DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable Long roomId){

    roomService.deleteRoomById(roomId);
     return ResponseEntity.noContent().build();
    }

}
