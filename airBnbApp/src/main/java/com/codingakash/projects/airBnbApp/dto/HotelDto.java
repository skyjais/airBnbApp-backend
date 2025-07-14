package com.codingakash.projects.airBnbApp.dto;

import com.codingakash.projects.airBnbApp.entity.HotelContactInfo;
import com.codingakash.projects.airBnbApp.entity.Room;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class HotelDto {


    private long id;
    private String name;



    private String city;
    private String[] photos;
    private String[] amenities;

//    @CreationTimestamp
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;
//
//    @Embedded
    private HotelContactInfo contactInfo;

  //  @Column(nullable = false)
    private Boolean active;

    // private List<RoomDto> rooms;
}
