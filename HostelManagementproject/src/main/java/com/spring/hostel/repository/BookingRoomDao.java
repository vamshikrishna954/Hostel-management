package com.spring.hostel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.hostel.model.BookingRoom;

@Repository
public interface BookingRoomDao extends JpaRepository<BookingRoom, Integer> {

	//Selects all bed id 
	@Query(value="select bed_id from book_room_tbl",nativeQuery = true)
	List<Integer> getAllBedNumbers();
	
	//Selecting all customers based on the dates
	@Query(value = "select * from book_room_tbl where joindate_withtime=?",nativeQuery = true)
	List<BookingRoom> getBookedCustomerDetails(String date);
	
	  
}
