package test.StepDefinitions;

import coreUtility.BaseClass;
import coreUtility.WebDriverSetup;
import customException.CustomException;
import globalConstants.GlobalConstants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import test.Pom.LaunchPagePOM;

public class LaunchPageStepDef {
	
	@Given("User has Launched SCIP application")
	public void User_has_Launched_SCIP_application() {

		//Link to navigate
		new BaseClass().launchBrowser_NavigateToURL(GlobalConstants.getTargetURL());

	}
	
	@Then("Launch page should be displayed with welcome text and login menu button at the top")
	public void Launch_page_should_be_displayed_with_welcome_text_and_login_menu_button_at_the_top() throws CustomException{
		
		new LaunchPagePOM().verifyLabel();
	}

}
