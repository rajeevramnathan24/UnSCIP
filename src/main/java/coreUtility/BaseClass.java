package coreUtility;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import customException.CustomException;
import globalConstants.GlobalConstants;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	
	
	
	/**
	 * 
	 */
	public BaseClass() {
		//super();
		// TODO Auto-generated constructor stub
	}

	public static void test() {
		
		
		
	}

	private static WebDriver webDriver = null;
	
	//Setting config file path name
			private static final String configFilePath = System.getProperty("user.dir") 
					+ "/src/test/resources/config/config.properties";


			/**
			 * loadPropertiesFile
			 * @throws CustomException
			 */
			public static Properties loadPropertiesFile() throws CustomException{
				
				Properties config = null;
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
						
						for (String key : config.stringPropertyNames()) {
						   
						    propValue.clear();
						    propValue.put(key, String.valueOf(input));				    				    				    
						}				
						
					} catch (IOException e) {
						throw new CustomException("Unable to load Config Properties file :" + e.getMessage());
					}
				}
				return config;
			}
			
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
			
			/**
			 * Method to set browser type based on the  config file, Pass the browser category as string. Currently supporting Chrome, FF, IE browsers.
			 * @param browserCategory
			 * @throws CustomException
			 */
			public void launchBrowser(String browserCategory) throws CustomException{
				
				//Initialize webDriver
				WebDriver wd=null;		
				
				//System.out.println(GlobalConstants.osType.toString());
						
				try {
					switch (browserCategory) {
						case "Chrome":

							//Calling setup method
							WebDriverManager.chromedriver().setup();
							ChromeOptions chromeOptions = new ChromeOptions();
											
							//Verify if headless execution is required
							if (GlobalConstants.headlessMode) {
							
								//Passing parameters for Headless execution
								chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200",
										"--ignore-certificate-errors");
													
//							chromeOptions.setBinary("/path/to/Chrome");
//							chromeOptions.setBinary("/path/to/other/chrome/binary");
								wd = new ChromeDriver(chromeOptions);
							} else {
								
								//When background flag is false
								wd = new ChromeDriver(chromeOptions);
							}
					        //chromeOptions.addArguments("--no-sandbox");
					       // chromeOptions.addArguments("--headless","--window-size=1920,1200");
					        //chromeOptions.addArguments("disable-gpu");
//			          chromeOptions.addArguments("window-size=1400,2100"); // Linux should be activate
					       // driver = new ChromeDriver(chromeOptions);
											
							break;

						case "FIREFOX":

							WebDriverManager.firefoxdriver().setup();
							FirefoxOptions FFOptions = new FirefoxOptions();
							
							//Verify if headless execution is required
							if (GlobalConstants.headlessMode) {
							
								//Passing parameters for Headless execution
								FFOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200",
										"--ignore-certificate-errors");
													
//							chromeOptions.setBinary("/path/to/Chrome");
//							chromeOptions.setBinary("/path/to/other/chrome/binary");
								wd = new FirefoxDriver(FFOptions);
							} else {
								
								//When background flag is false
								wd = new FirefoxDriver(FFOptions);
							}
							
							//wd = new FirefoxDriver();
							break;

						case "ED":

							WebDriverManager.edgedriver().setup();
							wd = new EdgeDriver();
							break;
							
						case "IE":

							WebDriverManager.iedriver().setup();
							wd = new InternetExplorerDriver();
							break;

						default:
							break;
						}
					
					//Set the driver value to be used across
					setDriver(wd);
					
				} catch (Exception e) {
					
					throw new CustomException("Unable to set and initialise Browser type");
					
					
				}
				
				
				
			}
			
			/**
			 * @Function This method will close the currently active browser tab opened by the current thread's driver object.
			 */
			public void closeBrowser() {
				
				//Close browser is taken care in After annotation in cucumber - Test execution manager

			}

			/**
			 * @Function This method will fetch the driver of the current thread and will perform browser navigation on the provided URL.
			 * @exception Exception - any generic exception
			 * @throws CustomException 
			 */
			public void launchBrowser_NavigateToURL(String url)  {
				
				getDriver().manage().window().maximize();
				getDriver().navigate().to(url);
				
				}
			
			private static final int IMG_WIDTH = 900;
		    private static final int IMG_HEIGHT = 450;

		    /**
		     * resizeImage
		     * @param input
		     * @param target
		     * @throws IOException
		     */
		    public static void resizeImage(
		    		String sourceFilePath, String targetFilePath) throws IOException {

		        Path source = Paths.get(sourceFilePath);
		        Path target = Paths.get(targetFilePath);
		        
		        InputStream inputFile = new FileInputStream(source.toFile());
		        BufferedImage originalImage = ImageIO.read(inputFile);

		        /**
		         * SCALE_AREA_AVERAGING
		         * SCALE_DEFAULT
		         * SCALE_FAST
		         * SCALE_REPLICATE
		         * SCALE_SMOOTH
		         */
		        Image newResizedImage = originalImage
		              .getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_SMOOTH);

		        String s = target.getFileName().toString();
		        String fileExtension = s.substring(s.lastIndexOf(".") + 1);

		        // we want image in png format
		        ImageIO.write(convertToBufferedImage(newResizedImage),
		                fileExtension, target.toFile());
		    }

		    
		    /**
		     * convertToBufferedImage
		     * @param img
		     * @return
		     */
		    public static BufferedImage convertToBufferedImage(Image img) {

		        if (img instanceof BufferedImage) {
		            return (BufferedImage) img;
		        }

		        // Create a buffered image with transparency
		        BufferedImage bi = new BufferedImage(
		                img.getWidth(null), img.getHeight(null),
		                BufferedImage.TYPE_INT_ARGB);

		        Graphics2D graphics2D = bi.createGraphics();
		        graphics2D.drawImage(img, 0, 0, null);
		        graphics2D.dispose();

		        return bi;
		    }
		    
		    /**
		     * 
		     * @param scenario
		     * @throws CustomException
		     */
		    public static void setDriver(WebDriver driver) {
				webDriver = driver;
			}
			public static WebDriver getDriver() {
				return webDriver;
			}
		    
			/**
			 * Below method to be executed each time before starting of execution
			 * @param scenario
			 * @throws CustomException
			 */
		    @Before
		    public void testDataSetup(Scenario scenario) throws CustomException {

				//Load all global data
				GlobalConstants.loadConfigFileData();
				
				//Launch and set driver				
				launchBrowser(GlobalConstants.BrowserType);
				
				
				//Set cucumber scenario
				GlobalConstants.scGlobal = scenario;
						
				//SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy 'T' HH:mm:ss");
				//scenario.write("Test Scenario Execution Started at = " + formatter.format(Calendar.getInstance().getTime()));
				//Logs.info("Test Scenario Execution Started at = " + formatter.format(Calendar.getInstance().getTime()));

				try {
					//CommonUtils.createScreenshotDirectory();
//					ScreenShot.createScreenshotDirectory();
					BaseClass.createScreenshotDirectory();
//					CommonUtility.createScreenshotDirectory();

					//Environment_Name
					//
					String envNameFromCommandLineOrJenkinsJob = System.getenv("Environment_Name");
					if(envNameFromCommandLineOrJenkinsJob != null && envNameFromCommandLineOrJenkinsJob != "") {
						GlobalConstants.setEnvironmentName(envNameFromCommandLineOrJenkinsJob);
					} else {
						String envNameFromConfigProperties = GlobalConstants.Environment;
						GlobalConstants.setEnvironmentName(envNameFromConfigProperties);
					}
					System.out.println("ENVIORNMENT=" + GlobalConstants.getEnvironmentName());

					//Browser_Name
					//
					String browserNameFromCommandLineOrJenkinsJob = System.getenv("Browser_Name");
					if(browserNameFromCommandLineOrJenkinsJob != null && browserNameFromCommandLineOrJenkinsJob != "") {
						GlobalConstants.setBrowserName(browserNameFromCommandLineOrJenkinsJob);
					} else {
						String browserNameFromConfigProperties = GlobalConstants.BrowserType;
						GlobalConstants.setBrowserName(browserNameFromConfigProperties);
					}
					System.out.println("BROWSER=" + GlobalConstants.getBrowserName());

				} catch (CustomException e) {
					throw new CustomException("TestExecutionStatusManager.testCaseExecutionStarted() - error - " + e.getMessage());
				}
			}
		    
		    /**
			 * This method is used to embed the captured screen shot on the html report
			 * @param scenario
			 * @throws CustomException
			 */
			public static void embedScreenShot(Scenario scenario) throws CustomException {

				try {
					//verify screenshot list size
					if(GlobalConstants.getTestEvidenceScreenshots().size() > 0) {
						//scenario.write("File path of the screenshot(s) captured during this test step execution:");
						for(String filePath : GlobalConstants.getTestEvidenceScreenshots()) {
							//scenario.write("embeddings\\" + new File(filePath).getName());
							byte[] encoded = Files.readAllBytes(Paths.get(filePath));	
							scenario.embed(encoded, "image/png"); 
						}

						//clear screen shot list
						GlobalConstants.clearTestEvidenceScreenshots();
					} else {

						scenario.write("No screenshots captured during this test step execution.");

					}
				} catch(Exception e) {
					throw new CustomException("TestExecutionManager.showTestStepExecutionStatus() - error - " + e.getMessage());
				}
			}
		    
		    @After
			/**
			 * This method will be called after every scenario/ feature file execution. To be used to quit browser
			 * @param scenario
			 */
			public void quitBrowserInstance(Scenario scenario) {
				
				getDriver().quit();
			}
}
