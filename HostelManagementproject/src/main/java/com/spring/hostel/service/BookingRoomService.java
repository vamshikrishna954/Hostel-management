package com.spring.hostel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.spring.hostel.model.BookingRoom;
import com.spring.hostel.model.RoomInformation;
import com.spring.hostel.repository.BookingRoomDao;
import com.spring.hostel.repository.CustomerDao;
import com.spring.hostel.repository.CustomerInfoDao;
import com.spring.hostel.repository.SignUpControllerDao;

@Service
public class BookingRoomService {
	
	@Autowired
	private BookingRoomDao bookDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CustomerInfoDao customerInfoDao;
	
	
	
	@Autowired
	private JavaMailSender sender;
	
	public String bookRoom(BookingRoom room)
	{
	    System.out.println(room.getBedId());
		List<Integer> allBedNumbers = customerDao.getAllBedNumbers();
		int count=0;
		
		// for checking bed is available or not
		for (Integer integer : allBedNumbers) {
			if (room.getBedId().equals(integer)) {
				count++;
				break;
			}
		}
		List<Integer> bedNumbers = bookDao.getAllBedNumbers();
		for(Integer integer:bedNumbers)
		{
			if (room.getBedId().equals(integer)) {
				count++;
				break;
			}
		}
		
		if (count>0) {
			return "BookFail.html ";
		} 
		else
		{
			System.out.println("Room booked suucessfully");
			bookDao.save(room);
			sendMail(room.getEmail(),room.getRoomNumber(),room.getBedId());
			return "BookSuccessful.html";
		}
	}
	
	public void sendMail(String toMail,Integer roomNumber,Integer bedId) 
	{
		
		SimpleMailMessage message=new  SimpleMailMessage();
		message.setFrom("vamshikrishna954m@gmail.com");
		message.setTo(toMail);
		message.setSubject("Happy Hostel");
		message.setText("Welcome to happy hostel..!Room booked Successfully With (RoomNumber"+roomNumber+", bedId "+bedId+").Thankyou..!!");
		sender.send(message);
		System.out.println("Mail sent succesfully..");
	}
	
	
	// find all
	public List<BookingRoom> findAll()
	{
		return bookDao.findAll();
	}
	
	// Delete booked customer
	public void deleteCustomer(Integer bookingId)
	{
		bookDao.deleteById(bookingId);;
	}
	// Get All Bed numbers
	public List<Integer> getBedId()
	{
		return bookDao.getAllBedNumbers();
	}
	
	//Get booked customers by date
	public List<BookingRoom> getBookedCustomersByDate(String date)
	{
		   List<BookingRoom> bookingList= bookDao.getBookedCustomerDetails(date);
		  return bookingList;
	}
	
	
	

}
