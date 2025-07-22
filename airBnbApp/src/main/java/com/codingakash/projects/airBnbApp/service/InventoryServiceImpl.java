package com.codingakash.projects.airBnbApp.service;

import aj.org.objectweb.asm.commons.Remapper;
import com.codingakash.projects.airBnbApp.dto.HotelDto;
import com.codingakash.projects.airBnbApp.dto.HotelPriceDto;
import com.codingakash.projects.airBnbApp.dto.HotelSearchRequest;
import com.codingakash.projects.airBnbApp.entity.Hotel;
import com.codingakash.projects.airBnbApp.entity.Inventory;
import com.codingakash.projects.airBnbApp.entity.Room;
import com.codingakash.projects.airBnbApp.repository.HotelMinPriceRepository;
import com.codingakash.projects.airBnbApp.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {


    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;
    private final HotelMinPriceRepository hotelMinPriceRepository;

    @Override
    public void initializeRoomForAYear(Room room) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusYears(1);

        for (; !today.isAfter(endDate); today = today.plusDays(1)) {

            Inventory inventory = Inventory.builder()
                                                    .hotel(room.getHotel())
                                                    .room(room)
                                                    .bookedCount(0)
                                                    .reservedCount(0)
                                                    .city(room.getHotel().getCity())
                                                    .date(today)
                                                    .price(room.getBasePrice())
                                                    .surgeFactor(BigDecimal.ONE)
                                                    .totalCount(room.getTotalCount())
                                                    .closed(false).build();

            inventoryRepository.save(inventory);


        }
    }

    @Override
    @Transactional
    public void deleteAllInventories(Room room) {
        log.info("Deleting the inventories of room with id: {}", room.getId());
        inventoryRepository.deleteByRoom(room);


    }

    @Override
    public Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest) {
        Pageable pageable = PageRequest.of(hotelSearchRequest.getPage(), hotelSearchRequest.getSize());

        log.info("Searching Hotels for {} city , from {} , to {}" , hotelSearchRequest.getCity(), hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate());
        Long dateCount = ChronoUnit.DAYS.between(hotelSearchRequest.getStartDate(),
                hotelSearchRequest.getEndDate()) + 1;

Page<HotelPriceDto> hotelPage =
        // business logic - 90 days
      //  inventoryRepository.findHotelsWithAvailableInventory
        hotelMinPriceRepository.findHotelsWithAvailableInventory
                (hotelSearchRequest.getCity()
                ,hotelSearchRequest.getStartDate(),
                 hotelSearchRequest.getEndDate(),
                 hotelSearchRequest.getRoomsCount(),
                 dateCount ,
                 pageable);

        return   hotelPage ;
    }


}
