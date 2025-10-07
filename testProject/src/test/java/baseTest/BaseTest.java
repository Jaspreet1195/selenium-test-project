
package baseTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import base.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;

public class BaseTest {

    // ThreadLocal for thread-safe driver
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    private static void setDriver(WebDriver drv) {
        driver.set(drv);
    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        WebDriver webDriver = null;

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");  // enable headless mode
            webDriver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } 
        // Add edge, safari, etc. as needed

        setDriver(webDriver);
        getDriver().get(PropertyReader.get("baseURL"));  // replace with PropertyReader.get("baseURL")
        getDriver().manage().window().maximize();
    }

    @AfterMethod
    public void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove(); // ✅ prevent memory leaks
        }
    }
}

//
//public class BaseTest {
//	WebDriver driver;
//
//	@Parameters("browser")
//	@BeforeMethod
//	public void setUp(@Optional("chrome") String browser) {
//		if (browser.equalsIgnoreCase("Chrome")) {
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//			driver.get(PropertyReader.get("baseURL"));
//			driver.manage().window().maximize();
//		}
//	}
//
//	@AfterMethod
//	public void quitDriver() {
//		if (driver != null) {
//			driver.quit();
//		}
//	}
//
//}
