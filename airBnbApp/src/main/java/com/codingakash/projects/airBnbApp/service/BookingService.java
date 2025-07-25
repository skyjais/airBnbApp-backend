package com.codingakash.projects.airBnbApp.service;

import com.codingakash.projects.airBnbApp.dto.BookingDto;
import com.codingakash.projects.airBnbApp.dto.BookingRequest;
import com.codingakash.projects.airBnbApp.dto.GuestDto;
import com.codingakash.projects.airBnbApp.entity.enums.BookingStatus;
import com.stripe.model.Event;

import java.util.List;

public interface BookingService {
    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);

    String initiatePayments(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

    BookingStatus getBookingStatus(Long bookingId);
}

