package com.codingakash.projects.airBnbApp.entity;


import com.codingakash.projects.airBnbApp.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column( unique = true , nullable = false )
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @Column( nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
}
