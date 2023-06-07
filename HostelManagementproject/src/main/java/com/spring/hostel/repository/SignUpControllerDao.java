package com.spring.hostel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.hostel.model.SignupCustomer;
@Repository
public interface SignUpControllerDao extends JpaRepository<SignupCustomer, Integer>  {

	   // Method to find signUp Existing customer
	   @Query(value = "select * from signup_tbl where user_mail=?",nativeQuery = true)
	   SignupCustomer findByEmail(@RequestParam("user_email") String email);
	
		// Native Query to find email by mobile number
		@Query(value="select user_mail from signup_tbl where ",nativeQuery = true)
		List<String> getEmailByMobileNumber(); 
}
