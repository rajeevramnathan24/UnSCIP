package customException;

import coreUtility.BaseClass;
import coreUtility.ScreenShot;
import globalConstants.GlobalConstants;

public class CustomException extends Exception {
	
	/**
	 * Adding default serial ID since this is custom exception class
	 */
	private static final long serialVersionUID = 1L;
	

	public CustomException(String s) {
		
		
		super(s);
		
		try {
//			CommonUtility.captureScreenshot();
			//ScreenShot.createScreenshotDirectory();
			//BaseClass.embedScreenShot(GlobalConstants.scGlobal);
		} catch (Exception  e) {
			
			e.printStackTrace();
		}
		//Logs.error(s);
	}

}
