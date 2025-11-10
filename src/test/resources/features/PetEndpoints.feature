Feature: Validate Swagger PetStore API

  Scenario: Create, Retrieve, Update and Delete Pet
    Given I create a new pet from test data index 0
    When I retrieve the pet by ID
    Then the retrieved pet should match the created pet
    When I update the pet status to "sold"
    Then the pet status should be "sold"
    When I retrieve the pet by ID
    Then the retrieved pet should match the updated pet
    When I delete the pet
    Then retrieving the pet should return 404


  Scenario: Find pets by status
    When I find pets by status "available"
    Then all returned pets should have status "available"


  Scenario: Validate Pet response schema
    Given I create a new pet from test data index 0
    When I retrieve the pet by ID
    Then the response matches the Pet schema


