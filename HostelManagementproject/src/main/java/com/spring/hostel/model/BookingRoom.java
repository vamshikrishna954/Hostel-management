package com.spring.hostel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
@Entity
@Table(name="book_room_tbl")
public class BookingRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//If i use generation type as IDENTITY then db will takecare of autoincrement
	@Column(name="book_id")
	private Integer bookingId;
	
	@Column(name="cust_name")
	private String customerName;
	
	@Column(name="mobile_num")
	private String mobileNumber;
	
	@Column(name="joindate_withtime")
	private String joinDate;
	
	@Column(name="enddate_withtime")
	private String endDate;
	
	@Column(name="room_num")
	private Integer roomNumber;
	
	@Column(name="bed_id")
	private Integer bedId;
	@Column(name="email")
	private String email;
}
