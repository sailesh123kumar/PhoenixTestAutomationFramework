package com.api.test;

import static org.hamcrest.Matchers.*;
import static com.api.utils.SpecUtil.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.api.constants.Role;
import com.api.request.model.Search;
import com.api.services.JobService;


public class SearchAPITest {
	
	
	private JobService jobService;
	private static final String JOB_NUMBER = "JOB_149092";
	private Search searchPayload;
	
	
	@BeforeMethod(description = "Instantiating the Jobservice object reference and setting up the Createjob payload")
	public void setUp() {
		jobService = new JobService();
		searchPayload = new Search(JOB_NUMBER);
	}
	
	
	@Test(description = "verify if the search api is working properly" , groups = {"api", "smoke"})
	public void searchAPITest() {
		jobService
		.search(Role.FD, searchPayload)
		.then()
		.spec(response_Spec_OK())
		.body("message", equalTo("Success"));
	}
	
}
