package com.codingakash.projects.airBnbApp.service;

import com.codingakash.projects.airBnbApp.dto.HotelDto;
import com.codingakash.projects.airBnbApp.dto.HotelInfoDto;
import com.codingakash.projects.airBnbApp.dto.RoomDto;
import com.codingakash.projects.airBnbApp.entity.Hotel;
import com.codingakash.projects.airBnbApp.entity.Room;
import com.codingakash.projects.airBnbApp.exception.ResourceNotFoundException;
import com.codingakash.projects.airBnbApp.repository.HotelRepository;
import com.codingakash.projects.airBnbApp.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;
    private final RoomRepository roomRepository;


    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {

        log.info("Creating new hotel with name :{}", hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("Created new hotel with id :{}", hotelDto.getId());
        return modelMapper.map(hotel, HotelDto.class);

    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting the hotel with id :{}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel Not found with this id  " + id));
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {

        log.info("Updating the hotel with id :{}", id);
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with this id :"+id));

        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDto.class);

        /*
        *
        * modelMapper.map(hotelDto, hotel);
         What happens?
         It copies all fields from hotelDto into the existing hotel object.

         So the hotel entity is updated with new values from DTO.

         Then you save the updated entity to DB:
        * */
    }

    @Override
    @Transactional
    public void deleteHotelById(Long id) {
        Hotel hotel= hotelRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with this Id :"+id));



        for(Room room : hotel.getRooms()){
            inventoryService.deleteAllInventories(room);
            roomRepository.deleteById(room.getId());
        }
         //return true
        hotelRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void activateHotel(Long hotelId) {
        log.info("Activating the hotel with id {}" , hotelId);
        Hotel hotel= hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with this Id :"+hotelId));

        hotel.setActive(true);

        //assuming only do it once
        for(Room room : hotel.getRooms()){
            inventoryService.initializeRoomForAYear(room);
        }

    }

    @Override
    public HotelInfoDto getHotelInfoById(Long hotelId) {
         Hotel hotel = hotelRepository.findById(hotelId)
                 .orElseThrow(()->new ResourceNotFoundException("Hotel not found with id :"+hotelId));

        List<RoomDto> rooms = hotel.getRooms()
                .stream()
                .map((element)-> modelMapper.map(element,RoomDto.class))
                .toList();

        return new HotelInfoDto(modelMapper.map(hotel, HotelDto.class) , rooms);

    }
}
