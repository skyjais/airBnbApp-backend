package com.codingakash.projects.airBnbApp.service;

import com.codingakash.projects.airBnbApp.entity.Inventory;
import com.codingakash.projects.airBnbApp.entity.Room;
import com.codingakash.projects.airBnbApp.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {


    private final InventoryRepository inventoryRepository;

    @Override
    public void initializeRoomForAYear(Room room) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusYears(1);

        for (; !today.isAfter(endDate); today = today.plusDays(1)) {

            Inventory inventory = Inventory.builder()
                                                    .hotel(room.getHotel())
                                                    .room(room)
                                                    .bookedCount(0).
                                                    city(room.getHotel().getCity())
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
    public void deleteFutureInventory(Room room) {
        LocalDate today = LocalDate.now();


         /*


          inventoryRepository.deleteByDateAfterAndRoom(today , room );

        * Because Spring only deleted future inventory, but some past or today's inventory
        * entries still exist for that room — and your DB doesn’t allow deleting a Room
        *  that's still referenced in inventory.
        * */


        inventoryRepository.deleteByDateGreaterThanEqualAndRoom(today, room);
        //delete Today and after ✅ so there is no inventory left for the room and so that DB allowed to delete room

    }


}
