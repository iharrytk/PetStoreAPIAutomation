package PetStoreUserAPIAutomation;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestResponse {
	
	RequestSpecification req;
	ResponseSpecification response_200;
	
	@BeforeClass
	public void setUp() {
		
		System.out.println("Generating Request Specification");
		
		req =new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2")
							.log(LogDetail.ALL)
								.build();
		System.out.println("Generated Request Specification");
		
		System.out.println("Generating Response Specification");
		response_200=new ResponseSpecBuilder()
								.expectStatusCode(200)
										.log(LogDetail.ALL)
											.build();
		System.out.println("Generated Response Specification");
									
		
	}

}
