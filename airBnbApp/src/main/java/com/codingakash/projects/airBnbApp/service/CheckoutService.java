package com.codingakash.projects.airBnbApp.service;

import com.codingakash.projects.airBnbApp.entity.Booking;

public interface CheckoutService {

    String getCheckoutSession(Booking booking, String successUrl , String failureUrl);

}
