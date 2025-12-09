package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Optional;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {

  private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
  private static final Duration DEFAULT_IMPLICIT_WAIT = Duration.ofSeconds(5);

  public static WebDriver getDriver() {
    WebDriver driver = DRIVER.get();
    if (driver == null) {
      throw new IllegalStateException("WebDriver is not initialized on the current thread.");
    }
    return driver;
  }

  private static void setDriver(WebDriver driver) {
    DRIVER.set(driver);
  }

  @BeforeMethod(alwaysRun = true)
  @Parameters({"browser", "headless"})
  public void setUp(
      @org.testng.annotations.Optional("") String browserParam,
      @org.testng.annotations.Optional("") String headlessParam) {
    String browser =
        firstNonBlank(
                System.getProperty("browser"),
                browserParam,
                PropertyReader.get("browser"),
                "chrome")
            .toLowerCase();
    boolean headless =
        Boolean.parseBoolean(
            firstNonBlank(
                System.getProperty("headless"),
                headlessParam,
                PropertyReader.get("headless"),
                "true"));

    WebDriver webDriver = createWebDriver(browser, headless);
    setDriver(webDriver);

    webDriver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT);
    webDriver.manage().window().maximize();
    webDriver.get(resolveBaseUrl());
  }

  @AfterMethod(alwaysRun = true)
  public void quitDriver() {
    WebDriver driver = DRIVER.get();
    if (driver != null) {
      try {
        driver.quit();
      } finally {
        DRIVER.remove();
      }
    }
  }

  protected String resolveBaseUrl() {
    return PropertyReader.getOrDefault("baseURL", "https://webdriveruniversity.com/index.html");
  }

  private WebDriver createWebDriver(String browser, boolean headless) {
    switch (browser) {
      case "chrome":
        return createChromeDriver(headless);
      case "firefox":
        return createFirefoxDriver(headless);
      default:
        throw new IllegalArgumentException("Unsupported browser requested: " + browser);
    }
  }

  private WebDriver createChromeDriver(boolean headless) {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-gpu");
    options.addArguments("--no-sandbox");
    if (headless) {
      options.addArguments("--headless=new");
      options.addArguments("--window-size=1920,1080");
    }

    return resolveRemoteUrl()
        .map(url -> (WebDriver) new RemoteWebDriver(url, options))
        .orElseGet(
            () -> {
              WebDriverManager.chromedriver().setup();
              return new ChromeDriver(options);
            });
  }

  private WebDriver createFirefoxDriver(boolean headless) {
    FirefoxOptions options = new FirefoxOptions();
    if (headless) {
      options.addArguments("--headless");
    }

    return resolveRemoteUrl()
        .map(url -> (WebDriver) new RemoteWebDriver(url, options))
        .orElseGet(
            () -> {
              WebDriverManager.firefoxdriver().setup();
              return new FirefoxDriver(options);
            });
  }

  private Optional<URL> resolveRemoteUrl() {
    String rawUrl =
        firstNonBlank(
            System.getProperty("seleniumGridUrl"), PropertyReader.get("selenium.grid.url"), "");
    if (rawUrl == null || rawUrl.isBlank()) {
      return Optional.empty();
    }
    try {
      return Optional.of(new URL(rawUrl));
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException("Invalid selenium grid url: " + rawUrl, e);
    }
  }

  private String firstNonBlank(String... values) {
    for (String value : values) {
      if (value != null && !value.isBlank()) {
        return value.trim();
      }
    }
    return "";
  }
}
