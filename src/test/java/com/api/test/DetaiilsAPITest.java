package com.api.test;

import static com.api.utils.SpecUtil.response_Spec_OK;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.api.constants.Role;
import com.api.request.model.Detail;
import com.api.services.DashboardService;

public class DetaiilsAPITest {
	
	private DashboardService dashboardService;
	private Detail detailPayload;
	
	@BeforeMethod(description = "Instatiating the Dashboard service object and creating detail payload")
	public void setup() {
		dashboardService = new DashboardService();
		detailPayload = new Detail("created_today");
	}
	
	
	@Test(description = "verify if details API is working properly" , groups = {"api","smoke","sanity"})
	public void detailAPITest() {
		dashboardService
			.details(Role.FD, detailPayload)
			.then()
			.spec(response_Spec_OK())
			.body("message",equalTo("Success") );
	}

}
