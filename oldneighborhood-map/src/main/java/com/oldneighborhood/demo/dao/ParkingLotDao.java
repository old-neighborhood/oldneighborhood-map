package com.oldneighborhood.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oldneighborhood.demo.entity.ParkingLot;

public interface ParkingLotDao extends JpaRepository<ParkingLot, String>{

}
