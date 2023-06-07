package com.spring.hostel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.hostel.model.Contact;
import com.spring.hostel.repository.ContactDao;

@Service
public class ContactService {
	
	@Autowired
	private ContactDao dao;
	
	public Contact saveContact(Contact contact)
	{
		return dao.save(contact);
	}

}
