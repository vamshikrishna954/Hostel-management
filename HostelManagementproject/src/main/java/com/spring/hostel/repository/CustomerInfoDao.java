package com.spring.hostel.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.hostel.model.CustomerInfo;
@Repository
public interface CustomerInfoDao extends JpaRepository<CustomerInfo, Integer> {
  
	 @Query(value = "select * from cust_info where cust_id=?",nativeQuery = true)
	 public CustomerInfo getCustomerInfo(int customerId);
	 
	// Native Query to find bed number
		@Query(value="select bed_id from cust_info",nativeQuery = true)
		List<Integer> getAllBedNumbers();

}
