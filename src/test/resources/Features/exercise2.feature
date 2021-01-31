Feature: PetStore

  Scenario: Get available Pets
    Given User set base URL "https://petstore.swagger.io/v2/pet"
    When User send API request to get all "available" pets in "/findByStatus"
    Then User validate the status code and all the available pets

Scenario: Post new Pet and delete the pet
    Given User set base URL "https://petstore.swagger.io/v2/pet"
    When User send API request to add new “available” pet
    Then User validate the new added pet
    When User send API request to update the new added pet to “sold”
    Then User validate the updated status of new added pet
    When User send API request to delete the new added pet
    Then User validate the delete status of new added pet
    