package test.Runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;

import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
		glue = {"test.StepDefinitions","coreUtility"},
		features = {"src/test/resources/features"},
//				features = GlobalConstants.content,
		 
		
		
//				format= {"pretty","html:target/cucumber-reports",
//        "json:target/cucumber-reports/CucumberTestReport.json"},
//		
		plugin = {	"pretty",
				"json:target/cucumber-reports/cucumber.json",
				"junit:target/cucumber-reports/cucumber.xml",
				"html:target/cucumber-reports"},
		
		
				monochrome = true, 
		tags = "@LoginLogout"
//		tags = "@DemoCS or @RefreshIndex or  @Demopass or @Demofail"
//		tags = {"~@Ignore"} //"@Studiosanity, @LXPsanity, @LXPCompleteCourse, @sample"//, "@LXPsanity","@StudioLXPsanity"}
//		tags = "@LXPSanity or CCSanity or @sample or @createCourse or @LXPCompleteCourse or @Studiosanity"
//		tags = "@TOP or @RefreshIndex or @LXPTOP or @LXPTOPIncomplete"
		)
		
//		plugin = {	"com.cucumber.listener.ExtentCucumberFormatter:", 
//				"json:target/cucumber-reports/cucumber.json"}
		
//		plugin = {
//				"pretty", 
//				"json:target/cucumber-reports/cucumber.json",
//				"junit:target/cucumber-reports/cucumber.xml",
//				"html:target/cucumber-reports"
//				}
		//)



public class TestRunner {
	


}
