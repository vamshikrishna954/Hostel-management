package com.spring.hostel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.spring.hostel.model.BedInformation;

@Repository
public interface BedInfoDao extends JpaRepositoryImplementation<BedInformation, Integer> {

	@Query(value = "select bed_id from bed_info",nativeQuery = true)
	List<Integer> getBedId();
	
}
