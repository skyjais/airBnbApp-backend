package com.codingakash.projects.airBnbApp.dto;


import com.codingakash.projects.airBnbApp.entity.User;
import com.codingakash.projects.airBnbApp.entity.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class GuestDto {

    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;

}
