package com.spring.hostel.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
@Entity
@Table(name="cust_info")
public class CustomerInfo {
	
	@Id
	@Column(name="bed_id")
	private Integer bedId;

	
	@Column(name="adhar_num")
	private String adharNumber;
	
	private String email;

	private String purpose;
	
	private String city;
	
	private String state;
	
	private Integer pincode;
	
	@OneToOne
	@JoinColumn(name="cust_id")
	private Customer customer;
	
	
	
	//Defalut constructor
	
	public CustomerInfo() {
		// TODO Auto-generated constructor stub
	}



	public CustomerInfo(Integer bedId, String adharNumber, String email, String purpose, String city, String state,
			Integer pincode, Customer customer) {
		super();
		this.bedId = bedId;
		this.adharNumber = adharNumber;
		this.email = email;
		this.purpose = purpose;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.customer = customer;
	}

	

	
}
