package com.codingakash.projects.airBnbApp.service;

import com.codingakash.projects.airBnbApp.entity.Room;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteFutureInventory(Room room);

}
