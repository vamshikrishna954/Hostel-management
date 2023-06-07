package com.spring.hostel.repository;


import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.hostel.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
	
	//Derived method for finding no.of student in each room by room number
	
	List<Customer> findByRoomNumber(Integer roomNumber);
	
	// fecthing Customer details by using phone numberr
	Customer findByMobileNumber(String mobileNumber);
	
	// finding number of vaccancies by room number
	@Query(value = "select count(*),room_num from cust_tbl where room_num=? group by room_num",nativeQuery = true)
	List<Customer> getNumberOfVaccancies(Integer roomNumber);
	
	// Derived method fetching customers who not paid complete fee
	
	List<Customer> findByPaidAmountLessThan(double paidAmount);
	
//	// delete by using mobile number
	@Query(value = "delete from cust_tbl where mobile_num=?",nativeQuery = true)
	public void deleteCustomerByMobileNumber(String mobileNumber);
//	
	// Native Query to find bed number
	@Query(value="select bed_id from cust_info",nativeQuery = true)
	List<Integer> getAllBedNumbers();
	
	@Query(value="select * from cust_info where cust ",nativeQuery = true)
	List<Customer> getCustomerById();
	
	//Get Customer fee details by date
	List<Customer> findByEndDate(String date);
	
	
	
	
	
	
	
	
	
	
	

}
