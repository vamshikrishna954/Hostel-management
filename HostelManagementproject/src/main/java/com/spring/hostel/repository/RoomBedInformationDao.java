package com.spring.hostel.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.hostel.model.RoomInformation;

@Repository
public interface RoomBedInformationDao extends JpaRepository<RoomInformation, Integer> {

	
}
