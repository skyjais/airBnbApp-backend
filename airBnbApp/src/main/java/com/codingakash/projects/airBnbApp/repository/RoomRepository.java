package com.codingakash.projects.airBnbApp.repository;

import com.codingakash.projects.airBnbApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
