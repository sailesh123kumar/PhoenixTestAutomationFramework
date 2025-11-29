package com.api.test.datadriven;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constants.Model;
import com.api.constants.OEM;
import com.api.constants.Platform;
import com.api.constants.Product;
import static com.api.constants.Role.*;
import com.api.constants.ServiceLocation;
import com.api.constants.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;
import  static com.api.utils.DateTimeUtil.*;
import static com.api.utils.SpecUtil.*;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;


public class CreateJobAPIFakerDataDrivenTest {
	
	@Test(description = "Verify Create job Api is able to create Inwarranty job", groups = {"datadriven" , "regression"} ,
			dataProviderClass = com.dataproviders.DataProvidersUtils.class ,dataProvider = "createJobAPIFakerDataProvider")
	public void createJobAPITest(CreateJobPayload payLoad) {
		
		
		given()
			.spec(request_SpecWithAuth(FD,payLoad))
		.when()
			.post("job/create")
		.then()
			.spec(response_Spec_OK())
			.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
			.body("message", equalTo("Job created successfully. "))
			.body("data.mst_service_location_id", equalTo(1))
			.body("data.job_number", startsWith("JOB_"));
	}
	
	
	

}
