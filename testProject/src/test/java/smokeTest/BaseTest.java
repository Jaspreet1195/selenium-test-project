
package smokeTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import base.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	WebDriver driver;

	
	@Parameters("browser")
	@BeforeMethod
	public void setUp(@Optional("chrome") String browser) {
		if(browser.equalsIgnoreCase("Chrome")) {
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.get(PropertyReader.get("baseURL"));
	    driver.manage().window().maximize();
		}
	}

	@AfterMethod
	public void quitDriver() {
		if(driver!=null) {
		driver.quit();
		}
	}
	
	
	

}
