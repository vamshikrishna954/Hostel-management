package com.spring.hostel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.hostel.model.BookingRoom;
import com.spring.hostel.repository.BedInfoDao;
import com.spring.hostel.service.BookingRoomService;
import com.spring.hostel.service.CustomerInfoService;
import com.spring.hostel.service.CustomerService;

@RestController
@RequestMapping("/bookroom")
public class RoomBookingController {
	
	@Autowired
	private BookingRoomService service;
	
	@Autowired
	private CustomerInfoService infoService;
	
	@Autowired
	private BookingRoomService bookService;
	
	@Autowired
	private BedInfoDao bedInfoDao;
	
	@Autowired
	private CustomerService custService;
	
	

	//Handler method for creating customer 
    @PostMapping(path = "/bookRoom")
	public ModelAndView createCustomer(BookingRoom room)
	{
		
		String bookRoom = service.bookRoom(room);
		return new ModelAndView(bookRoom);
	}
    
    //delete customer
    @GetMapping(path="/deleteBookedCustomer/{id}/{date}")
    public ModelAndView getBookingList(Model model,@PathVariable("id") Integer id, @PathVariable("date") String date)
	{
    	service.deleteCustomer(id);
    	
    	if(bookService.getBookedCustomersByDate(date).size()!=0)
		{
			model.addAttribute("booking",bookService.getBookedCustomersByDate(date));
			return new ModelAndView("Booking.html");
		}
		else
			return new ModelAndView("EmptyBookingList.html");
}
	

     // Get BookingbyDate page
    @GetMapping(path="/getBookingByDatePage")
    public ModelAndView getBookingByDatePage()
    {
    	return new ModelAndView("BookingByDate.html");
    }
    
    //Get Booked customers by details by date
    @PostMapping(path="/getBooking")
	public ModelAndView getBookingList(Model model,BookingRoom room)
	{ 
    	    String date=room.getJoinDate();
			if(bookService.getBookedCustomersByDate(date).size()!=0)
			{
				model.addAttribute("booking",bookService.getBookedCustomersByDate(date));
				return new ModelAndView("Booking.html");
			}
			else
				return new ModelAndView("EmptyBookingList.html");
	}
	
    //Get All Available roomsPage
    @GetMapping(path = "/getAvailable")
    public ModelAndView getAvailablePage()
    {
    	return new ModelAndView("GetAvailableRooms.html");
    }
    
  
  //Get All Available rooms
  	  @GetMapping(path = "/getRoomInfo")
      public ModelAndView getAllAvailableRooms(Model model)
      {
      	  List<Integer> bedId = bedInfoDao.getBedId();
          List<Integer> customers = custService.getAllBeds();
          List<Integer> bookrooms = bookService.getBedId();
          TreeMap<Integer,Integer> map=new TreeMap<>();
         
          List<Integer> set=new ArrayList<>();
          
        
          for(int x=0;x<bedId.size();x++)
          {
          	Integer bedNumber=bedId.get(x);
          	if (!customers.contains(bedNumber)) {
          		
          		if (!bookrooms.contains(bedNumber)) {
  					 set.add(bedNumber);
  				}
  				
  			}	
          }
          
          for(int x=0;x<set.size();x++)
          {
          	Integer bedNumber= set.get(x);
          	map.put(bedNumber, bedNumber/10);
          }
          model.addAttribute("map", map);
          return new ModelAndView("GetAvailableRooms.html");
           
      }
  	
  		
  	
}
