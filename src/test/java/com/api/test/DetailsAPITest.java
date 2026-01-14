package com.api.test;

import static com.api.utils.SpecUtil.response_Spec_OK;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.api.constants.Role;
import com.api.request.model.Detail;
import com.api.services.DashboardService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Job Management")
@Feature("Job Details")
@Listeners(com.listeners.APITestListener.class)
public class DetailsAPITest {
	
	private DashboardService dashboardService;
	private Detail detailPayload;
	
	@BeforeMethod(description = "Instatiating the Dashboard service object and creating detail payload")
	public void setup() {
		dashboardService = new DashboardService();
		detailPayload = new Detail("created_today");
	}
	
	@Story("Job details are shown correctly")
	@Description("verify if details API is working properly")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "verify if details API is working properly" , groups = {"api","smoke","sanity"})
	public void detailAPITest() {
		dashboardService
			.details(Role.FD, detailPayload)
			.then()
			.spec(response_Spec_OK())
			.body("message",equalTo("Success") );
	}

}
