package com.codingakash.projects.airBnbApp.controller;

import com.codingakash.projects.airBnbApp.dto.HotelDto;
import com.codingakash.projects.airBnbApp.dto.HotelInfoDto;
import com.codingakash.projects.airBnbApp.dto.HotelPriceDto;
import com.codingakash.projects.airBnbApp.dto.HotelSearchRequest;
import com.codingakash.projects.airBnbApp.service.HotelService;
import com.codingakash.projects.airBnbApp.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hotels")
public class HotelBrowseController {


    private final InventoryService inventoryService;
    private final HotelService hotelService;
    @GetMapping("/search")
   public ResponseEntity<Page<HotelPriceDto>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest){

  var page = inventoryService.searchHotels(hotelSearchRequest);

        return ResponseEntity.ok(page);
    }


    @GetMapping("{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId){
        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
    }

}
