package com.codingakash.projects.airBnbApp.service;

import com.codingakash.projects.airBnbApp.dto.HotelDto;
import com.codingakash.projects.airBnbApp.dto.RoomDto;
import com.codingakash.projects.airBnbApp.entity.Hotel;
import com.codingakash.projects.airBnbApp.entity.Room;
import com.codingakash.projects.airBnbApp.entity.User;
import com.codingakash.projects.airBnbApp.exception.ResourceNotFoundException;
import com.codingakash.projects.airBnbApp.exception.UnAuthorizedException;
import com.codingakash.projects.airBnbApp.repository.HotelRepository;
import com.codingakash.projects.airBnbApp.repository.InventoryRepository;
import com.codingakash.projects.airBnbApp.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.codingakash.projects.airBnbApp.utils.AppUtils.getCurrentUser;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;
    private final InventoryRepository inventoryRepository;


    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creating room in hotel with hotelId :" , hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel Not found with this id  " + hotelId));


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw new UnAuthorizedException("this User does not own this hotel with hotelId "+hotelId);
        }

        Room room = modelMapper.map(roomDto , Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);
// TODO  Create inventory as soon as room is created and hotel is active

if(hotel.getActive()){

    inventoryService.initializeRoomForAYear(room);
}

        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomInHotel(Long hotelId) {
        log.info("Getting room in hotel with hotelId :" , hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel Not found with this id  " + hotelId));


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw new UnAuthorizedException("this User does not own this hotel with hotelId "+hotelId);
        }

        return hotel.getRooms()
                .stream()
                .map((element)-> modelMapper.map(element , RoomDto.class ))
                .collect(Collectors.toList());

    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting room by id :" , roomId);
     Room room = roomRepository
             .findById(roomId)
             .orElseThrow(()->new ResourceNotFoundException("Room not found with this id :" + roomId));

     return modelMapper.map(room , RoomDto.class);

    }

    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        log.info("Deleting room by id :" , roomId);
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(()->new ResourceNotFoundException("Room not found with this id :" + roomId));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!user.equals(room.getHotel().getOwner())){
            throw new UnAuthorizedException("this User does not own this room with id "+roomId);
        }

        inventoryService.deleteAllInventories(room);

        roomRepository.deleteById(roomId);


        }

    @Override
    public RoomDto updateRoomById(Long hotelId, Long roomId, RoomDto roomDto) {

        log.info("Updating the room with ID: {}", roomId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+hotelId));

        User user = getCurrentUser();
        if(!user.equals(hotel.getOwner())) {
            throw new UnAuthorizedException("This user does not own this hotel with id: "+hotelId);
        }

        Room room = roomRepository.findById(roomId)
                .orElseThrow(()->new ResourceNotFoundException("Room not found with id "+roomId));

     modelMapper.map(roomDto, room);
     room.setId(roomId);

     room = roomRepository.save(room);



     return modelMapper.map(room, RoomDto.class);

    }
}
