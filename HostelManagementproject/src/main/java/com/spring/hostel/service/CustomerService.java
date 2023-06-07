package com.spring.hostel.service;


import java.util.List;
import java.util.Optional;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.hostel.model.Customer;
import com.spring.hostel.model.CustomerInfo;
import com.spring.hostel.repository.CustomerDao;
import com.spring.hostel.repository.CustomerInfoDao;


@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao dao;
	
	@Autowired
	private CustomerInfoDao infoDao;
	
	//create JavaMailSender to mails
	@Autowired
	private JavaMailSender sender;
	
	//send mail
	public void sendMail(String toMail) 
	{
		SimpleMailMessage message=new  SimpleMailMessage();
		message.setFrom("mallepakavamshikrishna@gmail.com");
		message.setTo(toMail);
		message.setSubject("Happy Hostel");
		message.setText("Welcome to happy hostel..!Your account is created successfully");
		sender.send(message);
		System.out.println("Mail sent succesfully..");
	}
	
	//Create customer
	public String createCustomer(Customer customer,CustomerInfo info)
	{
		Customer tempCustomer=getCustomerDetailsBymobileNumber(customer.getMobileNumber());
		// for checking bed is avilable or not
		List<Integer> allBedNumbers = dao.getAllBedNumbers();
		//allBedNumbers.stream().forEach(x->System.out.println(x));
		int count=0;
		if (tempCustomer==null || tempCustomer.getInfo().getAdharNumber()==null && tempCustomer.getInfo().getEmail()==null) 
		{
			double left;
			if(customer.getRoomNumber()>=101 && customer.getRoomNumber()<=105)
			{
			   left=14000-customer.getPaidAmount();
			   customer.setLeft(left);
			}
			
			if(customer.getRoomNumber()>=201 && customer.getRoomNumber()<=205)
			{
			   left=10000-customer.getPaidAmount();
			   customer.setLeft(left);
			}
			
			if(customer.getRoomNumber()>=301 && customer.getRoomNumber()<=305)
			{
			   left=8000-customer.getPaidAmount();
			   customer.setLeft(left);
			}
			
			if(customer.getRoomNumber()>=401)
			{
			   left=6000-customer.getPaidAmount();
			   customer.setLeft(left);
			}
			
			info.setAdharNumber(info.getAdharNumber());
			info.setBedId(info.getBedId());
			info.setCity(info.getCity());
			info.setEmail(info.getEmail());
			info.setPincode(info.getPincode());
			info.setPurpose(info.getPurpose());
			info.setState(info.getState());
			info.setCustomer(customer);
			
			customer.setInfo(info);
			// for checking bed is available or not
			for (Integer integer : allBedNumbers) {
				if (info.getBedId().equals(integer)) {
					count++;
					break;
				}
			}
			if (count>0) {
				return "RegistrationCheckWithBedId.html";
			}
			else
			{
				dao.save(customer);
				sendMail(info.getEmail());
				return "RegisterSuccess.html";
			}		
		}
		else if (tempCustomer.getInfo().getAdharNumber()!=null || tempCustomer.getInfo().getEmail()!=null) {
			return "RegisterCheckWithEmailAndAadhar.html";
		}
		else
		{
			return "RegisterFail.html";
		}
	}
	
	// create mutliple customer
	public Iterable<Customer> createMultipleCustomer(Iterable<Customer> customer)
	{
		return dao.saveAll(customer);	
	}
	
	
	
	//List all customers
	public List<Customer> getAllCustomers()
	{
		return dao.findAll();
	}
	
	
	//Get Customer by customer id
	/* It return optional becauase if any record is not there
	 * with specified id it won't give error instead of error it will
	 * Just returns null values
	 */
	public Optional<Customer> getCustomerById(Integer id)
	{
		return dao.findById(id);
	}
	
   //Get Details of customers by room number
	public List<Customer> getCustomersByRoomNumber(Integer roomNumber)
	{
		return dao.findByRoomNumber(roomNumber);
		
	}
	
	//Fetching details by mobile number
	
	public Customer getCustomerDetailsBymobileNumber(String mobileNumber)
	{
		return dao.findByMobileNumber(mobileNumber);
	}
	
	// Finding number of vaccancies by room number
	public List<Customer> getNumberOfVaccancies(Integer roomNumber)
	{
		return dao.getNumberOfVaccancies(roomNumber);
		
	}
	
	// Fetching details of customers who are not paid fee
	public List<Customer> getCustomersWhoNotPaidFee()
	{
		return dao.findByPaidAmountLessThan(5000.00);
	}
	
	 //Delete customer by using mobile number
	 public String deleteCustomerByMobileNumber(String mobileNumber)
	 {
		 
		  Customer cust = dao.findByMobileNumber(mobileNumber);
		  
		  if (cust!=null)
		  {
			  dao.deleteById(cust.getCustomerId());
			  return "DeleteSuccessful.html";
		  }
		  else 
		  {
              return "DeleteFail.html";
		  }
		 
	 }
//	
	  // Delete customer by customer id
	
	   public void deleteCustomer(Integer id)
	   {
		   dao.deleteById(id);
	   }
	   
	   
	  
	   
	    //find all booking list
		public List<Customer> findAll()
		{
			return dao.findAll();
					
		}
		
		
		
		// Update customer
		public String updateCustomer(Customer customer, CustomerInfo info)
		{
			
			Customer cust=dao.findByMobileNumber(customer.getMobileNumber());
			//CustomerInfo custInfo=infoDao.getCustomerInfo(cust.getCustomerId());
			Integer roomNumber = cust.getRoomNumber();
			Integer bedId = info.getBedId();
			System.out.println((customer.getRoomNumber().equals(roomNumber) && bedId.equals(cust.getInfo().getBedId())));
			
			if(cust!=null && (customer.getRoomNumber().equals(roomNumber) && bedId.equals(cust.getInfo().getBedId())))
			{
				cust.setJoinDate(customer.getJoinDate());
				cust.setEndDate(customer.getEndDate());
				cust.setPaidAmount(customer.getPaidAmount());
				
				double left;
				if(customer.getRoomNumber()>=101 && customer.getRoomNumber()<=105)
				{
				   left=14000-customer.getPaidAmount();
				   cust.setLeft(left);
				}
				
				if(cust.getRoomNumber()>=201 && customer.getRoomNumber()<=205)
				{
				   left=10000-customer.getPaidAmount();
				   customer.setLeft(left);
				}
				
				if(customer.getRoomNumber()>=301 && customer.getRoomNumber()<=305)
				{
				   left=8000-customer.getPaidAmount();
				   cust.setLeft(left);
				}
				
				if(customer.getRoomNumber()>=401)
				{
				   left=6000-customer.getPaidAmount();
				   cust.setLeft(left);
				}
				
				dao.save(cust);
				return "UpdateSuccessful.html";	
			}
			else
				return "UpdateFail.html";	
		}
		
		//Get all avialble rooms
		
		public List<Integer> getAllBeds()
		{
			return dao.getAllBedNumbers();
		}
		
		//Get customer fee details by date
		 public List<Customer> getCustomerFeeByDate(String date)
		 {
			 return dao.findByEndDate(date);
			 
		 }
		
		
		
	  
	  
}
