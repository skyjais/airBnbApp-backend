package com.codingakash.projects.airBnbApp.controller;


import com.codingakash.projects.airBnbApp.dto.InventoryDto;
import com.codingakash.projects.airBnbApp.dto.UpdateInventoryRequestDto;
import com.codingakash.projects.airBnbApp.entity.Inventory;
import com.codingakash.projects.airBnbApp.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/inventory")
public class InventoryController {


    private final InventoryService inventoryService;

    @GetMapping("rooms/{roomId}")
    @Operation(summary = "Get all inventory of a room", tags = {"Admin Inventory"})
    public ResponseEntity<List<InventoryDto>> getAllInventoryByRoom(@PathVariable Long roomId){
        return ResponseEntity.ok(inventoryService.getAllInventoryByRoom(roomId));
    }

    @PatchMapping("rooms/{roomId}")
    @Operation(summary = "Update the inventory of a room", tags = {"Admin Inventory"})
    public ResponseEntity<Void> updateInventory (@PathVariable Long roomId
    , @RequestBody UpdateInventoryRequestDto updateInventoryRequestDto){

        inventoryService.updateInventory(roomId, updateInventoryRequestDto);
        return ResponseEntity.noContent().build();
    }


}
