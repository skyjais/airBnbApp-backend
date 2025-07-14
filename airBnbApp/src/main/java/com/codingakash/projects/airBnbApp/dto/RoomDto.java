package com.codingakash.projects.airBnbApp.dto;

import com.codingakash.projects.airBnbApp.entity.Hotel;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class RoomDto {

    private long id;
    private String type;
    private BigDecimal basePrice;
    private String[] photos;
    private String[] amenities;
    private Integer totalCount;
    private Integer capacity;


//    private LocalDateTime createdAt;
//
//    private LocalDateTime updatedAt;

}
