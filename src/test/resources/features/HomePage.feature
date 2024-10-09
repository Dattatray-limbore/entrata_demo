@HomePageFeatures
Feature: Verify Home Page features for Entrata

  @HomePageTitle
  Scenario: Verify Homepage Title for Entrata
    Given I am on the Entrata homepage
    When I check the title of the page
    Then The title should be "Property Management Software | Entrata"


  @NavigationToFeaturesPage
  Scenario: Validate Navigation to NavBar features displayed
    Given I am on the Entrata homepage
    When User Verifies that "Products" option is displayed
    Then User clicks on "Products"
    When User Verifies that "Solutions" option is displayed
    Then User clicks on "Solutions"
    When User Verifies that "Resources" option is displayed
    Then User clicks on "Resources"


  @SearchFunctionality
  Scenario: Search Functionality for Entrata
  Given I am on the Entrata homepage.
  When I use the search bar to search for "property management".
  Then I should be taken to a results page.
  And The results page should display relevant search results.



