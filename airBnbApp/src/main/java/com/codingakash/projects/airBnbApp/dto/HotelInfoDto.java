package com.codingakash.projects.airBnbApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.bytebuddy.asm.Advice;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class HotelInfoDto {

    private HotelDto hotel;
    private List<RoomDto> rooms;

}
