package com.spring.hostel.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.hostel.model.SignupCustomer;
import com.spring.hostel.repository.SignUpControllerDao;

@Service
public class SignupCustomerService {
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private SignUpControllerDao signUpControllerDao;
	
	@Autowired
	private BCryptPasswordEncoder brBCryptPasswordEncoder;
	
    private String dummyEmail;
	
	private Integer dummyOtp;
	
	public void sendMail(String toMail) 
	{
		SimpleMailMessage message=new  SimpleMailMessage();
		message.setFrom("vamshikrishna954m@gmail.com");
		message.setTo(toMail);
		message.setSubject("Happy Hostel");
		message.setText("Welcome to happy hostel..!Your account is signed up successfully if your not please contact");
		sender.send(message);
		System.out.println("Mail sent succesfully..");
	}
	
	
	
	
	
	public SignupCustomer createCustomer(SignupCustomer signupCustomer)
	{
	
		sendMail(signupCustomer.getEmail());
		if (signupCustomer.getUserName().equals("vamshi_roshan")) {
			
			signupCustomer.setUserRole("ADMIN");
		    signupCustomer.setPassword(brBCryptPasswordEncoder.encode(signupCustomer.getPassword()));
		    signupCustomer.setConfirmPassword(brBCryptPasswordEncoder.encode(signupCustomer.getPassword()));
			
		}
		else
		{
			signupCustomer.setUserRole("USER");
			signupCustomer.setPassword(brBCryptPasswordEncoder.encode(signupCustomer.getPassword()));
		    signupCustomer.setConfirmPassword(brBCryptPasswordEncoder.encode(signupCustomer.getPassword()));
		}
		return signUpControllerDao.save(signupCustomer);
	}
	
	public SignupCustomer findCustomerByEmail(String email)
	{
		return signUpControllerDao.findByEmail(email);
	}
	
	//find all booking list
	public List<SignupCustomer> findAll()
	{
		return signUpControllerDao.findAll();
				
	}
	
//	//Otp generation
//	
	public Integer generateOtp(SignupCustomer signupCustomer) {
		
		
		if (findCustomerByEmail(signupCustomer.getEmail())!=null) {
			dummyEmail=signupCustomer.getEmail();
			Random random=new Random();
			int otp=random.nextInt(1000000);
			if (otp<100000) 
			{
				otp+=100000;	
			}
			dummyOtp=otp;
			SimpleMailMessage message=new SimpleMailMessage();
			message.setFrom("vamshikrishna954m@gmail.com");
			message.setTo(signupCustomer.getEmail());
			message.setSubject("Happy Hostel");
			message.setText("Your request  for password change received suucessfully.Your otp  "+otp+" If your are not please contact Admin");
			sender.send(message);
			return otp;	
		}
		else return 0;
	
	}

	//Verify password
    public boolean verifyOtp(Integer userOTP)
    {
    	System.out.println("dummy otp "+dummyOtp);
    	
    	if (userOTP.equals(dummyOtp)) {
			
    		return true;
		}
    	else
    		return false;
    }
    
    //Update password
    public void updatePassword(String password,String confirmPassword)
    {
    	SignupCustomer byEmail = findCustomerByEmail(dummyEmail);
    	System.out.println(byEmail.getEmail());
    	byEmail.setConfirmPassword(this.brBCryptPasswordEncoder.encode(confirmPassword));
    	byEmail.setPassword(this.brBCryptPasswordEncoder.encode(password));
    	signUpControllerDao.save(byEmail);
    }
    
	
	
	
	

}
