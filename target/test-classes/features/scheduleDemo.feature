@scheduleDemo
Feature: Verify Schedule Demo features for Entrata

  @scheduleDemoForm
  Scenario Outline: Verify User is able to schedule Demo session
    Given I am on the Entrata homepage
    When User clicks on "Schedule Your Demo" button
    And  User enters firtsName as <firstName> lastName as<lastName> email as<Email>companyName as<comName>phonenumber as<PhoneNumber>
    And User clicks on "ScheduleDemo"
    Then User verifies that demo is scheduled is success
    Examples:
      | firstName | lastName | Email              | comName | PhoneNumber |
      | John      | Doe      | john1234@gmail.com | Google  |1234567890   |



