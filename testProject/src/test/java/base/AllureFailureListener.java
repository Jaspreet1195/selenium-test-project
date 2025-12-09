package base;

import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureFailureListener implements ITestListener {

  @Override
  public void onTestFailure(ITestResult result) {
    attachScreenshot("Failure screenshot");
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    attachScreenshot("Failure screenshot (within success %)");
  }

  private void attachScreenshot(String name) {
    WebDriver driver;
    try {
      driver = BaseTest.getDriver();
    } catch (IllegalStateException ex) {
      return; // driver not available on this thread
    }

    if (driver instanceof TakesScreenshot) {
      byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
    }
  }

  @Override
  public void onTestStart(ITestResult result) {}

  @Override
  public void onTestSuccess(ITestResult result) {}

  @Override
  public void onTestSkipped(ITestResult result) {}

  @Override
  public void onStart(ITestContext context) {}

  @Override
  public void onFinish(ITestContext context) {}
}
