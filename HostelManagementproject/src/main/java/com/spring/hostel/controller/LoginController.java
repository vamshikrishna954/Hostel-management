package com.spring.hostel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
//@RequestMapping(path = "/login")
public class LoginController {
	
	@GetMapping(path="/login")
	public ModelAndView login()
	{
		return new ModelAndView("login.html");
	}

}
