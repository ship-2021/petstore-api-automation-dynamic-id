Feature: Validate Swagger PetStore API

  Scenario: Create, Retrieve, Update and Delete Pet
    Given I create a new pet from test data index 0
    When I retrieve the pet by ID
    And I update the pet status to "sold"
    And I retrieve the pet by ID
    And I delete the pet

  Scenario: Find pets by status
    When I find pets by status "available"

  Scenario: Validate Pet response schema
    Given I create a new pet from test data index 0
    When I retrieve the pet by ID
    Then the response matches the Pet schema


