package com.spring.hostel.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="room_info")
public class RoomInformation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="room_no")
	private Integer roomNumber;
	
	@Column(name="bed_no")
	@OneToMany(targetEntity = BedInformation.class,cascade = CascadeType.ALL)
	@JoinColumn(name ="room_no",referencedColumnName = "id")
	private Set<BedInformation> bedNumber=new HashSet<>();

}

