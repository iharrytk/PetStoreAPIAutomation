package PetStorePetDetailsAPIAutomation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import PetStorePetDetailsAPIAutomation.LombokPOJOPostPet.Category;
import PetStorePetDetailsAPIAutomation.LombokPOJOPostPet.Tags;
import io.restassured.response.ValidatableResponse;

public class TestPetDetailsLombokBuilder extends RequestResponse {
	LombokPOJOPostPet payload=LombokPOJOPostPet
			.builder()
			.id(201)
			.category(Category.builder().id(101).name("Golden Retriever").build())
			.name("Dogs")
			.photoUrls(Collections.singletonList("https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg"))
			.tags(Collections.singletonList(Tags.builder().id(101).name("Dogs").build()))
			.status("available")
			.build();
	
	@Test(priority = 1,description = "TC001:Validate creating a pet using POST call Lombok with builder pattern")
	public void createPetTest() {
		
		System.out.println("Begin Validating:TC001:Validate creating a pet using POST call Lombok with builder pattern");
		
		ValidatableResponse resp=given().spec(req)
					.contentType("application/json")
						.when()
							.body(payload)
								.post("/pet")
									.then()
									.assertThat()
									.spec(response_200)
											.body("id", equalTo(201))
							                .body("category.id", equalTo(101))
							                .body("category.name", equalTo("Golden Retriever"))
							                .body("name", equalTo("Dogs"))
							                .body("photoUrls[0]", equalTo("https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg"))
							                .body("tags[0].id", equalTo(101))
							                .body("tags[0].name", equalTo("Dogs"))
							                .body("status", equalTo("available"));

		
		String jsonResponse=resp.extract().asPrettyString();
		System.out.println("The JSON response is"+jsonResponse);
											
		
		System.out.println("Endof Validating:TC001:Validate creating a pet using POST call Lombok with builder pattern");
		
	}

}
