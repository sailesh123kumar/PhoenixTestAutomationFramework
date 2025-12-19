package com.database.dao;

import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;

public class DAODemoRunner {
	
	public static void main(String[] args) {
		
		CustomerDBModel customerInfo = CustomerDao.getCustomerInfo(130940);
		System.out.println(customerInfo);
		
		System.out.println("----------------------------------");
		
		CustomerAddressDBModel customerAddress = CustomerAddressDao.getCustomerAddress(130942);
		System.out.println(customerAddress);
		
		CustomerProductDBModel customerProductFromDB = CustomerProductDao.getCustomerProductFromDB(130930);
		System.out.println(customerProductFromDB);
	}

}
