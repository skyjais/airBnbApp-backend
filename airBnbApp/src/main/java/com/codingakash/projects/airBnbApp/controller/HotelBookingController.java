package com.codingakash.projects.airBnbApp.controller;

import com.codingakash.projects.airBnbApp.dto.BookingDto;
import com.codingakash.projects.airBnbApp.dto.BookingRequest;
import com.codingakash.projects.airBnbApp.dto.GuestDto;
import com.codingakash.projects.airBnbApp.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
@Slf4j
public class HotelBookingController {

    private final BookingService bookingService;

    @PostMapping("/init")
    public ResponseEntity<BookingDto> initialiseBoking(@RequestBody BookingRequest bookingRequest){
        return ResponseEntity.ok(bookingService.initialiseBooking(bookingRequest));
    }


    @PostMapping("/{bookingId}/addGuests")
    public ResponseEntity<BookingDto> addGuests(@PathVariable Long bookingId,
                                                @RequestBody List<GuestDto> guestDtoList){
        return ResponseEntity.ok(bookingService.addGuests(bookingId, guestDtoList));
    }


}
