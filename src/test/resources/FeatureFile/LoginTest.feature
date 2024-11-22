Feature: Login Functionality
@Smoke
  Scenario: Login with Valid Credentials
    Given User is able to launch the browser and navigate to Client Portal
    When User enters the "Username" and "Password"
    Then User should be redirected to the dashboard

  Scenario: Login with Invalid Credentials
    Given User is able to launch the browser and navigate to Client Portal
    When User enters the "Invalid Username" and "Password"
    Then User should see an error message
