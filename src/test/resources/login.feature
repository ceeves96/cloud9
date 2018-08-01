Feature: Test a successful Login

  Scenario: Successful Login
    Given A cloud login page
    When we login with email "crobinson52@qub.ac.uk" and password "password"
    Then the login should be successful
