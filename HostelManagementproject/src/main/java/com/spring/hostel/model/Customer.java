package com.spring.hostel.model;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name="cust_tbl")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//If i use generation type as IDENTITY then db will takecare of autoincrement
	@Column(name="cust_id")
	private Integer customerId;
	
	@Column(name="cust_name")
	private String customerName;
	
	@Column(name="mobile_num",unique = true)
	private String mobileNumber;
	
	@Column(name="joindate_withtime")
	private String joinDate;
	
	@Column(name="enddate_withtime")
	private String endDate;
	
	@Column(name="fee_paid")
	private double paidAmount;
	
	@Column(name="fee_left")
	private double left;

	
	@Column(name="room_num")
	private Integer roomNumber;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	private CustomerInfo info;
	
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer( String customerName, String mobileNumber, String joinDate,
			String endDate, double paidAmount, double left,Integer roomNumber,
			CustomerInfo info) {
		super();
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.joinDate = joinDate;
		this.endDate = endDate;
		this.paidAmount = paidAmount;
		this.left = left;
		this.roomNumber = roomNumber;
		this.info = info;
	}


}
