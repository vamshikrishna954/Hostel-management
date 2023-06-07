package com.spring.hostel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.hostel.model.Contact;
import com.spring.hostel.service.ContactService;

@RestController
@RequestMapping(path="/contact")
public class ContactController {
	
	@Autowired
	private ContactService service;
	
	//save contact information
	
	@PostMapping(path = "/save")
	public ModelAndView saveContactInfo(Contact contact, Model model)
	{
		Contact saveContact = service.saveContact(contact);
		if (saveContact!=null) {
			model.addAttribute("success","Submitted Successfully.");
			return new ModelAndView("Contact.html");
		}
		else
		{
			model.addAttribute("contactError", "Something went Wrong.");
			return new ModelAndView("Contact.html");
		}
	}

}
