package stepDefinitions;
/**
 * Created by sahil singla on 30/01/2021.
 */
import org.junit.Assert;

import controllers.PetStoreController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetPetStoreStepDef {

	PetStoreController petAPIController  = new PetStoreController();;
	
	@Given("User set base URL {string}")
	public void user_set_base_url(String string) {
		petAPIController.petStoreInitialization(string);
	}

	@When("User send API request to get all {string} pets in {string}")
	public void user_send_api_request_to_get_all_pets_in(String string, String string2) {
		petAPIController.getRequestPetController(string, string2);
	}

	@Then("User validate the status code and all the available pets")
	public void user_validate_the_status_code_and_all_the_available_pets() {
	    int statusCode = petAPIController.verifyGetResponse();
		Assert.assertEquals(200, statusCode);
	}

}
