package com.codingakash.projects.airBnbApp.dto;

import com.codingakash.projects.airBnbApp.entity.*;
import com.codingakash.projects.airBnbApp.entity.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {
    private Long id;
//    private Hotel hotel;
//    private Room room;
 //   private User user;
    private Integer roomsCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
//    private Payment payment;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guests;

}
