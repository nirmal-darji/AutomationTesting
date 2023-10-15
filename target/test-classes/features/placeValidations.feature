Feature: Validating Place APIs

@AddPlace
  Scenario Outline: Verify if place is successfully added using AddPlace API
    Given I have AddPlace API payload with "<name>" and "<language>"
    When I hit the "addPlace" API with "POST" method
    Then I verify status code is 200
    And I verify "status" in the response body is "OK"
    And I verify "scope" in the response body is "APP"
    And I verify "place_id" created maps to "<name>" using "getPlace" API

    Examples: 
      | name        | language |
      | NirmalHouse | French   |
  		#| DarjiHouse  | Spanish  |
 
@DeletePlace
  Scenario: Verify place is deleted successfully using DeletePlace API
    Given I have DeletePlace API payload
    When I hit the "deletePlace" API with "POST" method
    Then I verify status code is 200
    And I verify "status" in the response body is "OK"
