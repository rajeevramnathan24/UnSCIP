package coreUtility;

import java.io.File;

import customException.CustomException;

public class ScreenShot {
	
	/**
	 * createScreenshotDirectory
	 * @throws CustomException
	 */
	public static void createScreenshotDirectory() throws CustomException {
		File screenshot = new File(System.getProperty("user.dir") + 
				"/target/screenshots/");
		if(!(screenshot.exists() && screenshot.isDirectory())) {
			screenshot.mkdirs();
		}
	}

}
