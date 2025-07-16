package com.codingakash.projects.airBnbApp.repository;

import com.codingakash.projects.airBnbApp.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
