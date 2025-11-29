package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {
	
	public static final String COUNTRY = "India";
	
	public static void main(String[] args) {
		Faker faker = new Faker(new Locale("en-IND"));
		
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String mobile_number = faker.numerify("782#######");
		String mobile_number_alt = faker.numerify("782#######");
		String email_id = faker.internet().emailAddress();
		
		Customer customer = new Customer(firstName, lastName, mobile_number, mobile_number_alt, email_id, "");
		
		String flat_number = faker.address().buildingNumber();
		String apartment_name = faker.address().firstName();
		String street_name = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("#####");
		String state = faker.address().state();
		
		CustomerAddress customerAddress = new CustomerAddress(flat_number, apartment_name, street_name, landmark, area, pincode, COUNTRY, state);
		
		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String serial_number =  faker.numerify("###############");
		String popurl = faker.internet().url();
		
		CustomerProduct customerProduct = new CustomerProduct(dop, serial_number, serial_number, serial_number, popurl, 1, 1);
		
		//To generate random number between bound
		Random random = new Random();
		int problemId = random.nextInt(26)+1;
		
		String remark = faker.lorem().sentence(5);
		Problems problems = new Problems(problemId, remark);
		
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);
		System.out.println(createJobPayload);
	}

}
