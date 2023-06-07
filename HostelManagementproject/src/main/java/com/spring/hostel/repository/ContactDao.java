package com.spring.hostel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.hostel.model.Contact;

@Repository
public interface ContactDao  extends JpaRepository<Contact, Integer>{

}
