package test.PageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LaunchPageElements {

	
	public LaunchPageElements(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	//-----------------------------------#page elements--------------------------------------------
	
		@FindBy(xpath = "*//span[@class='home_text_image1__WDLiy']")
		public WebElement welcomeTextLabel;
}
