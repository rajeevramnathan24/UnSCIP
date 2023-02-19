package globalConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import coreUtility.ConfigReader;
import customException.CustomException;
import io.cucumber.core.api.Scenario;

public class GlobalConstants {
	
	private static WebDriver webDriver = null;
	private static String environmentName = "";
	private static List<String> lstTestEvidenceMessages = new ArrayList<String>();
	private static List<String> lstTestEvidenceScreenshots = new ArrayList<String>();
	private static List<String> lstTestSoftAssertionFailureMessages = new ArrayList<String>();
	private static String browserName = "";
	
	public static String studioUserName, studioPassword,lxpUsername,lxpPassword = null;


	public static String  Target_LXP_URL,Target_CC_URL,BrowserType,Environment, webDriverPath, osType,localDateTime, exeFileLocation, libFolderPath, jsonFolderPath= null;
	public static String importProcessPath,executableFileName = null;
	public static Boolean highlighterFlag, headlessMode = false;
	public static String currentDay, currentMonth, currentTime = null;
	
	public static String TargetURL;

	public static String getTargetURL() {
		return TargetURL;
	}
	public static void setTargetURL(String targetURL) {
		TargetURL = targetURL;
	}
	public static Scenario scGlobal = null;
	public static SoftAssert softAssertion = new SoftAssert();
	public static String windowsOS = "windows";
	
	public static String refreshIndexUrl = null;
//<<<<<<< HEAD
	public static String userLanguage = "English";
	
//=======

//>>>>>>> ManualTestSuite
	/*
	 * @Webdriver - Pass Webdriver to getter setter class
	 */
	public static void setDriver(WebDriver driver) {
		webDriver = driver;
	}
	public static WebDriver getDriver() {
		return webDriver;
	}

	/**
	 * This method initialises the Global constants variables before executing the test run. It is mandatory to run it once.
	 * @throws CustomException
	 */
	public static void loadConfigFileData() throws CustomException {

		//Config file path		
		final String configFilePath = System.getProperty("user.dir") 
				+ "/src/test/resources/config/config.properties";

		try {

			//Initialize properties
			Properties config = null;

			//Initialize hahmap to store all values to config file
			HashMap<String, String> propValue = new HashMap<String, String>();

			//Below code is to check for config file exist or not
			File file1 = new File(configFilePath);
			if (!file1.exists()) {
				throw new CustomException("Configuration properties file does not exists in the mentioned location : " 
						+ configFilePath);
			}

			//read config file
			InputStream input;
			try {
				input = new FileInputStream(configFilePath);
			} catch (FileNotFoundException e) {
				throw new CustomException("Unable to read Config Properties file :" + e.getMessage());
			}

			//load config file
			if (input != null) {
				try {
					config = new Properties();
					config.load(input);

					//Looping the config files values and storing them
					for (String key : config.stringPropertyNames()) {

						propValue.clear();
						propValue.put(key, String.valueOf(input));				    				    				    
					}
					
					//storing usernames/ pwd
					studioUserName = ConfigReader.loadPropertiesFile().get("CSUSERNAME").toString();
					studioPassword = ConfigReader.loadPropertiesFile().get("CSPWD").toString();
					
					lxpUsername = ConfigReader.loadPropertiesFile().get("LXPUSERNAME").toString();
					lxpPassword = ConfigReader.loadPropertiesFile().get("LXP_PWD").toString();

					//Storing these values in Global constants
					Environment = ConfigReader.loadPropertiesFile().get("ENVIRONMENT").toString();
									
					getUrls(Environment);
					
					refreshIndexUrl = ConfigReader.loadPropertiesFile().get("INDEX_URL").toString();

					BrowserType= ConfigReader.loadPropertiesFile().get("BROWSER_TYPE").toString();
					highlighterFlag= Boolean.parseBoolean(ConfigReader.loadPropertiesFile().get("HIGHLIGHTER").toString()) ;
					
					//webDriverPath = ConfigReader.loadPropertiesFile().get("DRIVER_EXE_PATH").toString();
					headlessMode =  Boolean.parseBoolean(ConfigReader.loadPropertiesFile().get("HEADLESS_MODE").toString()) ;
					osType = System.getProperty("os.name").toString();
					exeFileLocation = System.getProperty("user.dir") + ConfigReader.loadPropertiesFile().get("FILE_UPLOAD_EXE_PATH").toString()+"FileUpload1.exe";
					importProcessPath= System.getProperty("user.dir") + ConfigReader.loadPropertiesFile().get("FILE_UPLOAD_EXE_PATH").toString()+"importCourse.exe";
					
					
					//exeProcessFile = System.getProperty("user.dir") + ConfigReader.loadPropertiesFile().get("FILE_UPLOAD_EXE_PATH").toString()+"FileUploadProcess.exe";
					libFolderPath = System.getProperty("user.dir") + ConfigReader.loadPropertiesFile().get("LIB_FOLDER_PATH").toString();
					executableFileName = "FileUploadProcess.exe";
					jsonFolderPath = System.getProperty("user.dir") + ConfigReader.loadPropertiesFile().get("JSON_FOLDER_PATH").toString();
					
					SimpleDateFormat formatter = new SimpleDateFormat("ddMMMHHmmss");
					Date date = new Date();
					localDateTime = formatter.format(date);
					currentDay = new SimpleDateFormat("MMM").format(new Date());
					currentMonth = new SimpleDateFormat("dd").format(new Date());
					currentTime = new SimpleDateFormat("HHmm").format(new Date());
					
					//Setting Browser category
//					GlobalConstants.setDriver(WebDriverSetup.setBrowser(BrowserType));

				} catch (Exception e) {
					throw new CustomException("Unable to parse String/ Boolean/ Int values :" + e.getMessage());
				}
			}			

		} catch (CustomException e) {
			throw new CustomException("Unable to load Config Properties file :" + e.getMessage());
		}

	}	

	/**
	 * This method is used to return the urls based on the selected target environment
	 * @param envType
	 * @return
	 * @throws CustomException
	 */
	private static void getUrls(String envType) throws CustomException {

		switch (envType) {


		case "QA":

			setTargetURL(ConfigReader.loadPropertiesFile().get("QA_URL").toString());  			

			break;
			
		case "Dev":

			setTargetURL(ConfigReader.loadPropertiesFile().get("Dev_URL").toString()); 			

			break;
		
		default:
			break;
		}		

	}

	//BROWSER
	//
	public static void setBrowserName(String browser) {
		if(browser == null) {
			browserName = "";
		} else {
			browserName = browser;
		}
	}
	public static String getBrowserName() {
		return browserName;
	}

	//ENVIORNMENT
	//
	public static void setEnvironmentName(String env) {
		if(env == null) {
			environmentName = "";
		} else {
			environmentName = env;
		}
	}
	public static String getEnvironmentName() {
		return environmentName;
	}


	//TestEvidenceMessages
	//
	public static List<String> getTestEvidenceMessages() {
		return lstTestEvidenceMessages;
	}
	public static void addTestEvidenceMessage(String testEvidenceMessage) {
		lstTestEvidenceMessages.add(testEvidenceMessage);
		//Logs.info(testEvidenceMessage);
	}	
	public static void clearTestEvidenceMessages() {
		lstTestEvidenceMessages.clear();
	}


	//TestSoftAssertionFailureMessages
	//
	public static List<String> getTestSoftAssertionFailureMessages() {
		return lstTestSoftAssertionFailureMessages;
	}
	public static void addTestSoftAssertionFailureMessage(String testSoftAssertionFailureMessage) {
		lstTestSoftAssertionFailureMessages.add(testSoftAssertionFailureMessage);
		//Logs.error(testSoftAssertionFailureMessage);
	}	
	public static void clearTestSoftAssertionFailureMessages() {
		lstTestSoftAssertionFailureMessages.clear();
	}


	//TestEvidenceScreenshots
	//
	public static List<String> getTestEvidenceScreenshots() {
		return lstTestEvidenceScreenshots;
	}
	public static void addTestEvidenceScreenshot(String testEvidenceScreenshotFilePath) {
		lstTestEvidenceScreenshots.add(testEvidenceScreenshotFilePath);
	}
	public static void clearTestEvidenceScreenshots() {
		lstTestEvidenceScreenshots.clear();
	}	

}
