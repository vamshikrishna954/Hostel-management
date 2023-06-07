package com.spring.hostel.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.hostel.model.BedInformation;
import com.spring.hostel.model.Customer;
import com.spring.hostel.model.CustomerInfo;
import com.spring.hostel.model.RoomInformation;
import com.spring.hostel.repository.BedInfoDao;
import com.spring.hostel.repository.RoomBedInformationDao;
import com.spring.hostel.service.BookingRoomService;
import com.spring.hostel.service.CustomerInfoService;
import com.spring.hostel.service.CustomerService;
@RestController
@RequestMapping(path = "/cust")
public class CutomerController {
	
	@Autowired
	private CustomerService service;
	
	
	@Autowired
	private CustomerInfoService infoService;
	
	@Autowired
	private BookingRoomService bookService;
	
	@Autowired
	private BedInfoDao bedInfoDao;
	
	@Autowired
	private RoomBedInformationDao roomInfoDao;
	
	@Autowired
	private BedInfoDao bedDao;
	
	@GetMapping(path = "/getHome")
	public ModelAndView getHome(Model model)
	{
		
		return new ModelAndView("home.html");
	}
	
	//Return home page before login
	@GetMapping(path = "/")
	public ModelAndView getHome()
	{
		
		return new ModelAndView("home.html");
	}
	
	//HomePage after login
	@GetMapping(path = "/getLoggedHome")
	public ModelAndView getLoggedhome()
	{
		return new ModelAndView("HomeAfterLogin.html");
	}
	
	//Handler method to LoggedInPage
		@GetMapping(path="/loggedInPage")
		public ModelAndView getLoggedInPage(Model model)
		{
			
			
			return new ModelAndView("HomeAfterLogin.html");
		}
	
	// Handler for getting UserInfoCollection.2
	@GetMapping(path = "/getUserInfo")
	public ModelAndView getUserInfo(Model model)
	{
		
		return new ModelAndView("UserInfoCollect2.html");
	}
	
	
	
	//Handler method for creating customer 
    @PostMapping(path = "/createCustomer")
	public ModelAndView createCustomer(Customer customer,CustomerInfo info,Model model)
	{
		
		String createCustomer = service.createCustomer(customer,info);
		model.addAttribute("info", info);
		return new ModelAndView(createCustomer);
	}
	// Get create csutomer page
    @GetMapping(path="/getCreateCustPage")
    public ModelAndView getUserInfo()
    {
    	return new ModelAndView("UserInfoCollect2.html");
    }

	
	// login page
	@GetMapping(path = "/login")
	public ModelAndView getLogin()
	{
		
		return new ModelAndView("login.html");
		
	}
	
	// Get Otp Page
	@GetMapping(path="/getOtp")
	public ModelAndView getOtp()
	{
		return new ModelAndView("Otp.html");
	}
	// Get About page
	@GetMapping(path = "/about")
	public ModelAndView getAboutPage()
	{
		return new ModelAndView("AboutBeforeLogin.html");
	}
	
	//Get about page after login
	@GetMapping(path = "/aboutAfterLogin")
	public ModelAndView getAboutPageAfterLogin()
	{
		return new ModelAndView("aboutpageAfterLogin.html");
	}

	//Access denied page
	@GetMapping(path = "/accessDenied")
	public ModelAndView accessDeniedPage()
	{
		return new ModelAndView("Denied.html");
	}
	
	//Get Before signup page
	@GetMapping(value  = "/signupBeforeLogin")
	public ModelAndView getBeforeSignUpPage(Model model)
	{
		
		return new ModelAndView("signupBeforeLogin.html");
	}
	
	//Get after sign up page
	@GetMapping(value  = "/signup")
	public ModelAndView getSignUpPage(Model model)
	{
		
		return new ModelAndView("signup.html");
	}
     
	// Get contact details
	@GetMapping(path = "/getContact")
	public ModelAndView getConatctPage()
	{
		return new ModelAndView("Contact.html");
	}
	
	// Get contact details after login
		@GetMapping(path = "/getContactAfterLogin")
		public ModelAndView getConatctPageAfterLogin()
		{
			return new ModelAndView("ContactAfterLogin.html");
		}
		
	
	// Request handler method for fetching all customer details
	
	@GetMapping(path = "/getAll")
	public ModelAndView getAllCustomers(){
		 service.getAllCustomers();
		 return new ModelAndView("listAll.html");
	}
	
	
	//Request handler method for fetching customer by customerId
	
	@GetMapping(path="/{id}")
	public Optional<Customer> getCustomerById(@PathVariable("id") Integer id)
	{
		return service.getCustomerById(id);
	}

	//Request handler method for fetching customer details by room number
	@GetMapping(path="/room/{id}")
	public List<Customer> getCustomersByRoomNumber(@PathVariable("id") Integer id)
	{
		return service.getCustomersByRoomNumber(id);
	}
	
	//Request handler method for Checking no.of vaccancies in each room 
	
	@GetMapping(path="/roomno/{roomNumber}")
	public List<Customer> getNumberOfVaccanciesByRoomNumber( @PathVariable("roomNumber")Integer roomNumber)
	{
		  List<Customer> numberOfVaccancies = service.getNumberOfVaccancies(roomNumber);
		  return numberOfVaccancies;
		  
	}
	
	// Request handler method for Fetching customer details by mobileNumber
	
	@GetMapping(path="/mobile/{number}")
	public Customer getCustomerDetailsByMobileNumber(@PathVariable("number") String mobileNumber)
	{
		  return service.getCustomerDetailsBymobileNumber(mobileNumber);
	} 
	
	
	// fetching customer details who not paid complete fee
	/*Here it is giving complete details but when u create front end at that time just
	 *  pass name,mobile number, amount paid,and left
	 */
	
	@GetMapping(path="/notpaid")
	public List<Customer> getCustomerWhoNotPaidFee()
	{
		return service.getCustomersWhoNotPaidFee();
	}
	
	// Get delete page
	@GetMapping(path="/getDeletePage")
	public ModelAndView getDeletePage()
	{
		return new ModelAndView("Delete.html");
	}


	// Deleting customer  by customer mobile number
	@PostMapping(path="/deleteCustomerByMobile")
	public ModelAndView deleteCustomer(Customer customer, Model model)
	{
		
		String deletePage = service.deleteCustomerByMobileNumber(customer.getMobileNumber());
		model.addAttribute("mobileNumber", customer.getMobileNumber());
		return new ModelAndView(deletePage);
		
	}
	
	
	//Room Booking before login
	@GetMapping(path="/roomBookingBeforeLogin")
	public ModelAndView getRoomBookingBeforeLogin()
	{
		return new ModelAndView("RoomBookingBeforeLogin.html");
	}
	
	
	// get room booking page
	@GetMapping(path = "/getRoomBooking")
	public ModelAndView getRoomBookingInfo(Model model)
	{
		
		return new ModelAndView("RoomBooking.html");
	}
	
	
	//Get get room info page
	@GetMapping(path="/getGetHomeInfo")
	public ModelAndView getGetHomeInfo(Model model)
	{
		return new ModelAndView("GetHome.html");
	}
	
//	// Get all customers page
//	@GetMapping(path="/getAllCustomers")
//	public ModelAndView getAllCustomers(Model model)
//	{
//		model.addAttribute("info", infoService.getAllCustomerInfo());
//		model.addAttribute("customers", service.getAllCustomers());
//		return new ModelAndView("GetAllCustomers.html");
//	}
	
	//Handler method to get GetAllCustomer1.html page
	@GetMapping(path="/getAllCustomersPage")
	public ModelAndView getShowGetRoomMain()
	{
		return new ModelAndView("GetCustomerByRoomNum.html");
	}
	
	//Handler method to find cutomer by room number
	@PostMapping(path="/getCustomerByRoomNumber")
	public  ModelAndView getCusto(Customer customer, Model model)
	{
		int roomNumber=customer.getRoomNumber();
		List<Customer> customersByRoomNumber = service.getCustomersByRoomNumber(roomNumber);
		int customerCount = service.findAll().size();
		if(customersByRoomNumber.size()!=0)
		{
	       model.addAttribute("customers",service.getCustomersByRoomNumber(roomNumber));
	       model.addAttribute("customerCount", customerCount);
	       return new ModelAndView("GetAllCustomer1.html");
		}
		else
		{
			model.addAttribute("cust", roomNumber);
			return new ModelAndView("NoCustomerAvailableInRoom.html");
		}
	   
	}
	
	
	//Handler method to delete customer and showing the same remaining result
	@GetMapping(path="/deleteCustomer/{customerId}/{room}")
	public ModelAndView getCustomerDetailsAfterDelete(Customer cust,@PathVariable("customerId") Integer id,@PathVariable("room") Integer room, Model model)
	{
		   
		    service.deleteCustomer(id);
		    model.addAttribute("customers",service.getCustomersByRoomNumber(room));
		    return new ModelAndView("GetAllCustomer1.html");
	}
	
	
	// Get Booking list
	
//	@GetMapping(path="/getCustomerByRoomNumInfo")
//	public ModelAndView getCustomerByRoomInfoPage() {
//		return new ModelAndView("GetCustomerByRoomNum.html");
//	}
	
//	//Get Available rooms
//	@GetMapping(path="/getCustByRoomNumber")
//	public ModelAndView getCustomerByRoomNum(Customer customer,Model model)
//	{
//		
//		return new ModelAndView("GetCustomerByRoomNum.html");
//	}
	
	// Get GetCustomerByRoomNumber html page
//	@GetMapping(path="/getCustomerByRoomNumberResult")
//	public ModelAndView getCustomerByRoomNumberResult(Customer customer,Model model)
//	{
//		int roomNumber=customer.getRoomNumber();
//		model.addAttribute("custByRoom", service.getCustomersByRoomNumber(roomNumber));
//		return new ModelAndView("GetCustByRoomResult.html");
//	}
	
	
	// Update customer   
	@GetMapping(path="/updateCustomer")
	public ModelAndView update(Customer customer, CustomerInfo info,Model model)
	{
		
		return new ModelAndView("UpdateCustomer.html");
		
	}
	
	@PostMapping(path="/updateCustomerDetails")
	public ModelAndView updateFee(Customer customer, CustomerInfo info,Model model)
	{
	
		String updateCustomer = service.updateCustomer(customer, info);
		return new ModelAndView(updateCustomer);
		
	}
	
	
	//Inserti room and bed numbers into table
	@PostMapping(path = "/createRoomBedInfo")
	public RoomInformation createInfo(@RequestBody RoomInformation info,BedInformation bedInfo)
	{
		return roomInfoDao.save(info);
	}
	
	//Get Customer by fee page
		@GetMapping(path = "/getCustomerFeePage")
		public ModelAndView getCustomerByFeePage()
		{
			return new ModelAndView("CustomerFee.html");
		}
		
		//Get Customer details by date
		@PostMapping(path = "/getCustomerFeeByDate")
		public ModelAndView getCustomerByFeeDate(@RequestParam("endDate") String date, Model model)
		{
			List<Customer> customerFeeByDate = service.getCustomerFeeByDate(date);
			System.out.println(customerFeeByDate.size());
			if (customerFeeByDate.size()!=0) {
				model.addAttribute("customers", customerFeeByDate);
				return new ModelAndView("CustomerFeeWithDate.html");
			}
			else
			{
				model.addAttribute("dummyerror", "No Data Found..!!");
				return new ModelAndView("CustomerFee.html");
			}
				
		}
		
		
	
//	//Get All Available rooms
//	@GetMapping(path = "/getRoomInfo")
//    public Map<Integer,Integer> getAllAvailableRooms()
//    {
//    	List<Integer> bedId = bedInfoDao.getBedId();
//        List<Integer> customers = service.getAllBeds();
//        List<Integer> bookrooms = bookService.getBedId();
//        Map<Integer,Integer> map=new HashMap<>();
//       
//        List<Integer> set=new ArrayList<>();
//        
//      
//        for(int x=0;x<bedId.size();x++)
//        {
//        	Integer bedNumber=bedId.get(x);
//        	if (!customers.contains(bedNumber)) {
//        		
//        		if (!bookrooms.contains(bedNumber)) {
//					 set.add(bedNumber);
//				}
//				
//			}	
//        }
//        for(int x=0;x<set.size();x++)
//        {
//        	Integer bedNumber= set.get(x);
//        	map.put(bedNumber, bedNumber/10);
//        }
//      
//        return map;
//         
//    }
	
		
	

}

