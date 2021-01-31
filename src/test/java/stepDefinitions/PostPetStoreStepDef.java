package stepDefinitions;

/**
 * Created by sahil singla on 30/01/2021.
 */

import org.junit.Assert;

import controllers.PetStoreController;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PostPetStoreStepDef {

	PetStoreController petAPIController = new PetStoreController();
	
	
	@When("User send API request to add new “available” pet")
	public void user_send_api_request_to_add_new_available_pet() {
		petAPIController.postRequestPetController("available");
	}
	
	@Then("User validate the new added pet")
	public void user_validate_the_new_added_pet() {
		int statusCode = petAPIController.verifyAddedPet();
		Assert.assertEquals(200, statusCode);
	}

	@When("User send API request to update the new added pet to “sold”")
	public void user_send_api_request_to_update_the_new_added_pet_to_sold() {
		petAPIController.putRequestPetController("sold");
	}

	@Then("User validate the updated status of new added pet")
	public void user_validate_the_updated_status_of_new_added_pet() {
		String updatedStatus = petAPIController.verifyUpdatedPet();
		Assert.assertEquals("sold", updatedStatus);
	}

	@When("User send API request to delete the new added pet")
	public void user_send_api_request_to_delete_the_new_added_pet() {
		petAPIController.deleteRequestPetController();
	}

	@Then("User validate the delete status of new added pet")
	public void user_validate_the_delete_status_of_new_added_pet() {
		boolean result = petAPIController.verifyPetDeleted();
		Assert.assertTrue(result);
	}

}
