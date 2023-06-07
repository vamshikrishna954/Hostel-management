package com.spring.hostel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.hostel.model.CustomerInfo;
import com.spring.hostel.repository.CustomerInfoDao;

@Service
public class CustomerInfoService {
	
	@Autowired
	private CustomerInfoDao infoDao;
	
	public List<CustomerInfo> getAllCustomerInfo()
	{
		return infoDao.findAll();
	}

}
