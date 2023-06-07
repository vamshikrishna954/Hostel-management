package com.spring.hostel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.hostel.model.SignupCustomer;
import com.spring.hostel.service.SignupCustomerService;

@Controller
@RequestMapping(path="/signup")
public class SignupController {
	
		@Autowired
		private SignupCustomerService service;
	
	    //sign up
		@GetMapping(value  = "/getsignup")
		public ModelAndView getSignUpPage(Model model)
		{
			
			return new ModelAndView("signup.html");
		}
	
		// Api to handle creating customer
		@PostMapping(path="/createSignupCustomer")
		public ModelAndView createSignupCustomer(@ModelAttribute SignupCustomer signupCustomer,Model model)
		{
			//System.out.println(signupCustomer.getEmail());
			SignupCustomer email=service.findCustomerByEmail(signupCustomer.getEmail());
			//System.out.println(email.getEmail());
			if (email==null) {
				service.createCustomer(signupCustomer);
				return new ModelAndView("SignUpSucess");
			}
			else
			{
				model.addAttribute("message","Username Exists already..!");
				return new ModelAndView("SignUpFail");
			}
			
		}
		
		// Get Otp Page
				@GetMapping(path="/getOtp")
				public ModelAndView getOtp()
				{
					return new ModelAndView("Otp.html");
				}
				
				//Get Enter otp page
				@PostMapping(path = "/getEnteredOtpPage")
				public ModelAndView getOtpEnteredPage(SignupCustomer signupCustomer,Model model)
				{
					Integer generateOtp = service.generateOtp(signupCustomer);
					if (generateOtp>0) 
					{
						model.addAttribute("otp", generateOtp);
						return new ModelAndView("EnteredOtp.html");
					}
					else 
						model.addAttribute("dummyerror12","Username doesn't Exist..!!");
						return new ModelAndView("Otp.html");
				}
				
				//Verify Otp
				@PostMapping(path = "/verifyOtp")
				public ModelAndView verifyOtp(@RequestParam("userOtp") Integer userOTP, Model model )
				{
					
					boolean verifyOtp = service.verifyOtp(userOTP);
					if (verifyOtp) {
						
						return new ModelAndView("UpdatePassword.html");
						
					} else {
						model.addAttribute("otperror", "Invalid OTP. Please try again.");
						return new ModelAndView("EnteredOtp.html");
					}
				
				}
				
				//Update Password 
				@PostMapping(path="/updatePassword")
				public ModelAndView updatePassword(@RequestParam("password") String password,@RequestParam("confirmPassword") String confirmPassword)
				{
					service.updatePassword(password,confirmPassword);
					return new ModelAndView("PasswordUpdateSuccessful");
				}


}
