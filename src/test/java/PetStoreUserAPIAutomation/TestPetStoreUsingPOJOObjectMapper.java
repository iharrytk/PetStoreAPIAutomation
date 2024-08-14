package PetStoreUserAPIAutomation;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class TestPetStoreUsingPOJOObjectMapper extends RequestResponse {
	
	POJOUser userpayload=new POJOUser(786,"Jon","Jon","Snow","john@gmail.com","jon@123","7895289631",1);
	POJOUser userpayloadupdated=new POJOUser(786,"Haritha","Jon","Snow","john@gmail.com","jon@123","7895289631",1);
	String payloadToBeUpdated;
	
	@Test(priority = 1,description = "TC001:Validate creating a user using POST call")
	public void createUserTest() throws JsonMappingException, JsonProcessingException {
		//Serialization using POJO
		
		System.out.println("Begin Validating:TC001:Validate creating a user using POST call");
		Response resp=given()
		.spec(req)
		.headers("Content-Type", "application/json")
		.body(userpayload)
		.when()
		.post("/user")
		.then()
		.statusCode(200)
		.extract()
		.response();
		
		System.out.println("The api response is"+resp.asPrettyString());
		
		
		//Deserialization using Object Mapper
		ObjectMapper mapper=new ObjectMapper();
		POJOUserResponse response=mapper.readValue(resp.asString(), POJOUserResponse.class);
		int code=response.getCode();
		
		//TestNG assertions
		Assert.assertEquals(code, 200);
		Assert.assertEquals(resp.statusCode(), 200);
		
		
		System.out.println("Endof Validating:TC001:Validate creating a user using POST call");
		
	}
	
	@Test(priority=2 ,description="TC002:Validate the created user is retrived using GET call")
	public void getUser() throws JsonMappingException, JsonProcessingException{
		System.out.println("Begin Validating:TC002:Validate the created user is retrived using GET call");
		Response resp=given()
				.spec(req)
				.headers("Content-Type", "application/json")
				.when()
				.get("/user/"+userpayload.getUsername())
				.then()
				.statusCode(200)
				.extract()
				.response();
		
				payloadToBeUpdated=resp.asPrettyString();
				
				System.out.println("The api response is"+payloadToBeUpdated);
				
				
				//Deserialization using Object Mapper
				ObjectMapper mapper=new ObjectMapper();
				POJOUser response=mapper.readValue(resp.asString(), POJOUser.class);
				String firstname=response.getFirstName();
				
				//TestNG assertions
				Assert.assertEquals(firstname, "Jon");
				Assert.assertEquals(resp.statusCode(), 200);
		
		
		System.out.println("Endof Validating:TC002:Validate the created user is retrived using GET call");
	}
	
	@Test(priority=3 ,description="TC003:Validate the  user is updated using PUT call")
	public void updateUser() throws JsonMappingException, JsonProcessingException  {
		System.out.println("Begin Validating:TC003:Validate the  user is updated using PUT call");
		
		
		
		//Serialization using POJO
		Response resp=given()
				.spec(req)
				.headers("Content-Type", "application/json")
				.body(userpayloadupdated)
				.when()
				.put("/user/"+userpayload.getUsername())
				.then()
				.statusCode(200)
				.extract()
				.response();
		
				String UpdatedPayload=resp.asPrettyString();
				
				System.out.println("The api response is"+UpdatedPayload);
				
				
				//Deserialization using Object Mapper
				ObjectMapper mapper=new ObjectMapper();
				POJOUserResponse response=mapper.readValue(resp.asString(), POJOUserResponse.class);
				int code=response.getCode();
				
				//TestNG assertions
				Assert.assertEquals(code, 200);
				Assert.assertEquals(resp.statusCode(), 200);	
		
										
		System.out.println("Endof Validating:TC003:Validate the  user is updated using PUT call");	
					
	}
	
	@Test(priority=4 ,description="TC004:Validate the  user is deleted using DELETE call")
	public void deleteUser() throws JsonMappingException, JsonProcessingException {
		System.out.println("Begin Validating:TC004:Validate the  user is deleted using DELETE call");
		
					//Serialization using POJO
							Response resp=given()
									.spec(req)
									.headers("Content-Type", "application/json")
									.when()
									.delete("/user/"+userpayloadupdated.getUsername())
									.then()
									.statusCode(200)
									.extract()
									.response();
				
		
						
						//Deserialization using Object Mapper
						ObjectMapper mapper=new ObjectMapper();
						POJOUserResponse response=mapper.readValue(resp.asString(), POJOUserResponse.class);
						int code=response.getCode();
						
						//TestNG assertions
						Assert.assertEquals(code, 200);
						Assert.assertEquals(resp.statusCode(), 200);	
										
		System.out.println("Endof Validating:TC004:Validate the  user is deleted using DELETE call");	
	}
}
