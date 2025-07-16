package com.codingakash.projects.airBnbApp.entity;

import com.codingakash.projects.airBnbApp.entity.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hotel_id" , nullable = false)
    private Hotel hotel;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id" , nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user-id" , nullable = false)
    private User user;


    @Column(nullable = false)
    private Integer roomsCount;

    @Column(nullable = false)
    private LocalDate checkInDate;
    private LocalDate checkOutDate;


    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="payment_id" , nullable = false)
//    private Payment payment;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus bookingStatus;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "booking_guest",
             joinColumns = @JoinColumn(name ="boooking_id"),
             inverseJoinColumns = @JoinColumn(name="guest_id")
    )
    private Set<Guest> guests;

    @Column( nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
}
