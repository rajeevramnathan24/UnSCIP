package coreUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import customException.CustomException;
import globalConstants.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverSetup {
	
	/**
	 * Method to set browser type based on the  config file, Pass the browser category as string. Currently supporting Chrome, FF, IE browsers.
	 * @param browserCategory
	 * @throws CustomException
	 */
	public static WebDriver setBrowser(String browserCategory) throws Exception{
		
		//Initialize webDriver
		WebDriver wd=null;		
		
		//System.out.println(GlobalConstants.osType.toString());
				
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
										
//					chromeOptions.setBinary("/path/to/Chrome");
//					chromeOptions.setBinary("/path/to/other/chrome/binary");
					wd = new ChromeDriver(chromeOptions);
				} else {
					
					//When background flag is false
					wd = new ChromeDriver(chromeOptions);
				}
	            //chromeOptions.addArguments("--no-sandbox");
	           // chromeOptions.addArguments("--headless","--window-size=1920,1200");
	            //chromeOptions.addArguments("disable-gpu");
//	          chromeOptions.addArguments("window-size=1400,2100"); // Linux should be activate
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
										
//					chromeOptions.setBinary("/path/to/Chrome");
//					chromeOptions.setBinary("/path/to/other/chrome/binary");
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
		
		return wd;
		
	}
	
	
	/* NOT Used
	 * 
	 */
	public static WebDriver intializeBrowser(String browserType) throws CustomException{
		//Initialize webDriver
				WebDriver wd1=null;	
				
				//Verify the OS type
				if (GlobalConstants.osType.contains("Windows")) {
					
					switch (browserType) {
					case "Chrome":
						
						WebDriverManager.chromedriver().setup();
									
						//ChromeOptions chOption = new ChromeOptions();
						//DesiredCapabilities capabilities = new DesiredCapabilities();
								
						
						//capabilities.setCapability(ChromeOptions.CAPABILITY, "");
											
						wd1 = new ChromeDriver();				
												
//						capabilities.setBrowserName(browserType);					
//						capabilities.setBrowserName(browserType);					
//						String bname = capabilities.getBrowserName();				
//						System. out. println(bname);					
//						System. out. println(System.getProperty("os.name").toLowerCase());					
//						System. out. println(capabilities.getPlatform());					
//						System. out. println(capabilities.getVersion());					
						
						
						wd1.quit();
						
						break;

					default:
						
						System.out.println("Incorrect browser type and execution could not initiate");
						
						break;
					}										
				}
					
				return null;
		
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
		
		GlobalConstants.getDriver().manage().window().maximize();
		GlobalConstants.getDriver().navigate().to(url);
		
		}

}
