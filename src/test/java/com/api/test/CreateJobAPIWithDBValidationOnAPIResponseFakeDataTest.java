package com.api.test;

import static com.api.constants.Role.FD;
import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.response.model.CreateJobResponseModel;
import com.api.services.JobService;
import com.api.utils.FakerDataGenerator;
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.CustomerProductDao;
import com.database.dao.JobHeadDao;
import com.database.dao.MapJobProblemDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;
import com.database.model.JobHeadDBModel;
import com.database.model.MapJobProblemDBModel;

import io.restassured.http.ContentType;

public class CreateJobAPIWithDBValidationOnAPIResponseFakeDataTest {
	private CreateJobPayload createJobPayload;
	private JobService jobService;
	
	@BeforeMethod(description = "Instantiating the Jobservice object reference and setting up the fakeData")
	public void setup() {
		jobService = new JobService();
		createJobPayload = FakerDataGenerator.generateFakeCreateJobPayload();
	}

	@Test(description = "Verify Create job Api is able to create Inwarranty job", groups = { "api", "smoke",
			"regression" })
	public void createJobAPITest() {

		 CreateJobResponseModel createJobResponseModel = jobService
					.create(FD, createJobPayload)
				.then().log().all()
				.statusCode(200)
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"))
				.extract().as(CreateJobResponseModel.class);
		 
		 		int customerId = createJobResponseModel.getData().getTr_customer_id();;
		 		System.out.println("=============================================");
				System.out.println("CUSTOMER ID : "+customerId);
		
				Customer expectedCustomerData = createJobPayload.customer();
				CustomerDBModel actualCustomerData = CustomerDao.getCustomerInfo(customerId);
				
				Assert.assertEquals(actualCustomerData.getFirst_name(), expectedCustomerData.first_name());
				Assert.assertEquals(actualCustomerData.getLast_name(), expectedCustomerData.last_name());
				Assert.assertEquals(actualCustomerData.getMobile_number(), expectedCustomerData.mobile_number());
				Assert.assertEquals(actualCustomerData.getMobile_number_alt(), expectedCustomerData.mobile_number_alt());
				Assert.assertEquals(actualCustomerData.getEmail_id(), expectedCustomerData.email_id());
				Assert.assertEquals(actualCustomerData.getEmail_id_alt(), expectedCustomerData.email_id_alt());
				
				int tr_customer_address_id = actualCustomerData.getTr_customer_address_id();
				System.out.println("CUSTOMER ADDRESS ID : " +tr_customer_address_id);
				
				CustomerAddress customerAddress = createJobPayload.customer_address();
				CustomerAddressDBModel customerAddressDBdata = CustomerAddressDao.getCustomerAddress(tr_customer_address_id);
	
				Assert.assertEquals(customerAddressDBdata.getFlat_number(), customerAddress.flat_number());
				Assert.assertEquals(customerAddressDBdata.getApartment_name(), customerAddress.apartment_name());
				Assert.assertEquals(customerAddressDBdata.getStreet_name(), customerAddress.street_name());
				Assert.assertEquals(customerAddressDBdata.getLandmark(), customerAddress.landmark());
				Assert.assertEquals(customerAddressDBdata.getArea(), customerAddress.area());
				Assert.assertEquals(customerAddressDBdata.getPincode(), customerAddress.pincode());
				Assert.assertEquals(customerAddressDBdata.getCountry(), customerAddress.country());
				Assert.assertEquals(customerAddressDBdata.getState(), customerAddress.state());
				
				int tr_customer_product_id = createJobResponseModel.getData().getTr_customer_product_id();
				System.out.println("CUSTOMER PRODUCT ID : "+ tr_customer_product_id);
				
				CustomerProduct customer_product = createJobPayload.customer_product();
				CustomerProductDBModel customerProductFromDB = CustomerProductDao.getCustomerProductFromDB(tr_customer_product_id);
				Assert.assertTrue(customer_product.dop().contains(customerProductFromDB.getDop()));
				Assert.assertEquals(customerProductFromDB.getImei1(), customer_product.imei1());
				Assert.assertEquals(customerProductFromDB.getImei2(), customer_product.imei2());
				Assert.assertEquals(customerProductFromDB.getSerial_number(), customer_product.serial_number());
				Assert.assertEquals(customerProductFromDB.getPopurl(), customer_product.popurl());
				Assert.assertEquals(customerProductFromDB.getMst_model_id(), customer_product.mst_model_id());
				
				JobHeadDBModel jobHeadinfoFromDB = JobHeadDao.getJobHeadinfoFromDB(tr_customer_product_id);
			//	Assert.assertEquals(jobHeadinfoFromDB.getMst_service_location_id(), createJobPayload.mst_service_location_id(), "===LOCATION ID MISMATCH===");
				Assert.assertEquals(jobHeadinfoFromDB.getMst_platform_id(), createJobPayload.mst_platform_id(), "===PLATFORM ID MISMATCH===");
				Assert.assertEquals(jobHeadinfoFromDB.getMst_warrenty_status_id(), createJobPayload.mst_warrenty_status_id(), "===WARRENTY STATUS ID MISMATCH===");
				Assert.assertEquals(jobHeadinfoFromDB.getMst_oem_id(), createJobPayload.mst_oem_id(), "===OEM ID MISMATCH===");
				
				
				int tr_jobhead_id = createJobResponseModel.getData().getId();  
				System.out.println("JOBHEAD ID : "+tr_jobhead_id);
				
				MapJobProblemDBModel problemDataFromDB = MapJobProblemDao.getProblemDataFromDB(tr_jobhead_id);
				
				Assert.assertEquals(problemDataFromDB.getMst_problem_id(), createJobPayload.problems().get(0).id() , "===MST_PROBLEM_ID_MISMATCH===");
				Assert.assertEquals(problemDataFromDB.getRemark(), createJobPayload.problems().get(0).remark());
	}

	@Test(description = "Verify Create job Api is able to create Inwarranty job", groups = { "api", "smoke",
			"regression" }, dataProviderClass = com.dataproviders.DataProvidersUtils.class, dataProvider = "createJobAPIFakerDataProvider")
	public void createJobAPIwithDefaultCountTest(CreateJobPayload payLoad) {

		given().baseUri(getProperty("BASE_URI")).header("Authorization", getToken(FD)).contentType(ContentType.JSON)
				.body(payLoad).log().all().when().post("job/create").then().log().all().statusCode(200)
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"));
	}

}
