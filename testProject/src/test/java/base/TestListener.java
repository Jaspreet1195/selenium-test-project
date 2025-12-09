package base;

import io.qameta.allure.Allure;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener implements ITestListener, ISuiteListener {

  private static final Logger LOGGER = Logger.getLogger(TestListener.class.getName());

  @Override
  public void onTestStart(ITestResult result) {
    LOGGER.info(() -> "Test started: " + result.getName());
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    LOGGER.info(() -> "Test passed: " + result.getName());
  }

  @Override
  public void onTestFailure(ITestResult result) {
    LOGGER.warning(() -> "Test failed: " + result.getName());
    captureScreenshot(result);
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    LOGGER.info(() -> "Test skipped: " + result.getName());
  }

  @Override
  public void onStart(ISuite suite) {
    LOGGER.info(() -> "Suite started: " + suite.getName());
  }

  @Override
  public void onFinish(ISuite suite) {
    LOGGER.info(() -> "Suite finished: " + suite.getName());
  }

  private void captureScreenshot(ITestResult result) {
    WebDriver driver;
    try {
      driver = BaseTest.getDriver();
    } catch (IllegalStateException ex) {
      LOGGER.log(Level.WARNING, "WebDriver was not available for screenshot capture", ex);
      return;
    }

    if (!(driver instanceof TakesScreenshot)) {
      LOGGER.warning("Driver does not support screenshots.");
      return;
    }

    try {
      Path dest = buildScreenshotPath(result);
      Files.createDirectories(dest.getParent());
      byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      Files.write(dest, screenshot);

      Reporter.log("<a href='" + dest.toAbsolutePath() + "'>Screenshot</a>");
      try (InputStream is = Files.newInputStream(dest)) {
        Allure.addAttachment("Failure Screenshot", is);
      }
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Unable to capture screenshot for test " + result.getName(), e);
    }
  }

  private Path buildScreenshotPath(ITestResult result) {
    String fileName =
        result.getName()
            + "_"
            + Thread.currentThread().getId()
            + "_"
            + System.currentTimeMillis()
            + ".png";
    return Paths.get("target", "screenshots", fileName);
  }
}
