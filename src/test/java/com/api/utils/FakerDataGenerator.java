package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDataGenerator {

	private static Faker faker = new Faker(new Locale("en-IND"));
	public static final String COUNTRY = "India";
	private static final Random RANDOM = new Random();

	private static final int MST_SERVICE_LOCATION_ID = 0;
	private static final int MST_PLATFORM_ID = 2;
	private static final int MST_WARRANTY_STATUS_ID = 1;
	private static final int MST_OEM_ID = 1;

	private static final int PRODUCT_ID = 1;
	private static final int MST_MODEL_ID = 1;

	private final static int VALID_PROBLEM_ID[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 19, 20, 22, 24,
			26, 27, 28, 29 };

	private FakerDataGenerator() {

	}

	public static CreateJobPayload generateFakeCreateJobPayload() {
		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomeraddressData();
		CustomerProduct customerProduct = generateFakeCustomerProducData();
		List<Problems> problemsList = generateFakeProblemsListData();

		CreateJobPayload createJobPayload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
				MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);
		return createJobPayload;
	}

	public static Iterator<CreateJobPayload> generateFakeCreateJobPayload(int count) {

		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();

		for (int i = 1; i <= count; i++) {

			Customer customer = generateFakeCustomerData();
			CustomerAddress customerAddress = generateFakeCustomeraddressData();
			CustomerProduct customerProduct = generateFakeCustomerProducData();
			List<Problems> problemsList = generateFakeProblemsListData();

			CreateJobPayload createJobPayload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
					MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);
			payloadList.add(createJobPayload);
		}

		return payloadList.iterator();
	}

	private static Customer generateFakeCustomerData() {
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String mobile_number = faker.numerify("782#######");
		String mobile_number_alt = faker.numerify("782#######");
		String email_id = faker.internet().emailAddress();

		Customer customer = new Customer(firstName, lastName, mobile_number, mobile_number_alt, email_id, "");
		return customer;
	}

	private static CustomerAddress generateFakeCustomeraddressData() {
		String flat_number = faker.address().buildingNumber();
		String apartment_name = faker.address().firstName();
		String street_name = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("#####");
		String state = faker.address().state();

		CustomerAddress customerAddress = new CustomerAddress(flat_number, apartment_name, street_name, landmark, area,
				pincode, COUNTRY, state);

		return customerAddress;
	}

	private static CustomerProduct generateFakeCustomerProducData() {
		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String serial_number = faker.numerify("###############");
		String popurl = faker.internet().url();

		CustomerProduct customerProduct = new CustomerProduct(dop, serial_number, serial_number, serial_number, popurl,
				PRODUCT_ID, MST_MODEL_ID);
		return customerProduct;
	}

	private static List<Problems> generateFakeProblemsListData() {

		int randomCount = RANDOM.nextInt(3) + 1;
		int randomIndex;
		String fakeRemark;
		List<Problems> problemsList = new ArrayList<Problems>();
		Problems problems;

		for (int i = 1; i <= randomCount; i++) {
			randomIndex = RANDOM.nextInt(VALID_PROBLEM_ID.length);
			fakeRemark = faker.lorem().sentence(5);
			problems = new Problems(VALID_PROBLEM_ID[randomIndex], fakeRemark);
			problemsList.add(problems);
		}
		return problemsList;
	}

}
