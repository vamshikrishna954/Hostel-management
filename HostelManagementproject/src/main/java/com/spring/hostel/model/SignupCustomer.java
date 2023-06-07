package com.spring.hostel.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="signup_tbl")
public class SignupCustomer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_mail")
	private String email;
	
	@Column(name="user_password")
	private String password;
	
	@Column(name="confirm_password")
	private String confirmPassword;
	
	@Column(name = "role")
	private String userRole;

	
	

	
	
	
	
	
	

}
