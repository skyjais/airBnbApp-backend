package com.codingakash.projects.airBnbApp.controller;

import com.codingakash.projects.airBnbApp.dto.BookingDto;
import com.codingakash.projects.airBnbApp.dto.BookingRequest;
import com.codingakash.projects.airBnbApp.dto.BookingStatusResponseDto;
import com.codingakash.projects.airBnbApp.dto.GuestDto;
import com.codingakash.projects.airBnbApp.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class HotelBookingController {

    private final BookingService bookingService;

    @PostMapping("/init")
    @Operation(summary = "Initiate the booking", tags = {"Booking Flow"})
    public ResponseEntity<BookingDto> initialiseBooking(@RequestBody BookingRequest bookingRequest){
        return ResponseEntity.ok(bookingService.initialiseBooking(bookingRequest));
    }


    @PostMapping("/{bookingId}/addGuests")
    @Operation(summary = "Add guest Ids to the booking", tags = {"Booking Flow"})
    public ResponseEntity<BookingDto> addGuests(@PathVariable Long bookingId,
                                                @RequestBody List<GuestDto> guestDtoList){
        return ResponseEntity.ok(bookingService.addGuests(bookingId, guestDtoList));
    }


    @PostMapping("/{bookingId}/payments")
    @Operation(summary = "Initiate payments flow for the booking", tags = {"Booking Flow"})
    public ResponseEntity<Map<String , String>> initiatePayment(@PathVariable Long bookingId){
        String sessionUrl = bookingService.initiatePayments(bookingId);
        return ResponseEntity.ok(Map.of("sessionUlr" , sessionUrl));
    }

    @PostMapping("/{bookingId}/cancel")
    @Operation(summary = "Cancel the booking", tags = {"Booking Flow"})
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId){
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{bookingId}/status")
    @Operation(summary = "Check the status of the booking", tags = {"Booking Flow"})
    public ResponseEntity<BookingStatusResponseDto> getBookingStatus(@PathVariable Long bookingId) {
        return ResponseEntity.ok(new BookingStatusResponseDto(bookingService.getBookingStatus(bookingId)));
    }
}
