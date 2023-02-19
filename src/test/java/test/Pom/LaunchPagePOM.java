package test.Pom;

import coreUtility.BaseClass;
import coreUtility.CommonUtility;
import customException.CustomException;
import test.PageElements.LaunchPageElements;

public class LaunchPagePOM {

	public LaunchPageElements  LaunchPageElement= null;
	
	public LaunchPagePOM() {
		
		LaunchPageElement = new LaunchPageElements(BaseClass.getDriver());
	}

	public void verifyLabel() throws CustomException {
		
		
		
		try {
			//new CommonUtility().jsWait();
			
			new CommonUtility().threadWait(10000);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//new CommonUtility().isElementVisibleNew(LaunchPageElement);
		
		new CommonUtility().verifyAndReport(LaunchPageElement.welcomeTextLabel.getText().toString(), "Welcome to the Supply chain Continuous" +"\n"+"Improvement Plans Implementation Tool (SCIP)");
		
		new CommonUtility().verifyAndReport(LaunchPageElement.welcomeTextLabel.getText().toString(), "Fail");
		
	}
	
}
