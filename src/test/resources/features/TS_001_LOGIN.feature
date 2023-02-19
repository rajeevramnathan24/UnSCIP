Feature: SCIP001: Verify appropriate user is able to login to Unicef SCIP system
     
  Given As an authorised user
  When logged in to SCIP system with valid credentials 
  Then user is successfully logged into SCIP system

  @LoginLogout
  Scenario Outline: To verify that Registered User is able to successfully into SCIP system
  
    Given User has Launched SCIP application
    Then Launch page should be displayed with welcome text and login menu button at the top

    #**************************************************************************************
    Examples: 
      | id | User Name                 | Studio Password | Role         |
      |  1 | vishaltest123@yopmail.com | Test@1234       | Global Admin |
     