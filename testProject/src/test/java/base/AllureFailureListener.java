package base;

import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureFailureListener implements ITestListener {

  @Override
  public void onTestFailure(ITestResult result) {
    attachScreenshot("Failure screenshot");
    attachVideo("Failure video");
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

  /**
   * Attaches a failure video. Video path must be provided via -Dvideo.path=/path/to/video.mp4 and
   * must exist; otherwise the run fails to enforce video capture is present.
   */
  private void attachVideo(String name) {
    String videoPath = System.getProperty("video.path");
    Assert.assertTrue(
        videoPath != null && !videoPath.isBlank(),
        "video.path system property is required to attach failure video");

    File videoFile = new File(videoPath);
    Assert.assertTrue(
        videoFile.exists() && videoFile.isFile(),
        "Failure video not found at video.path=" + videoPath);

    try (FileInputStream fis = new FileInputStream(videoFile)) {
      Allure.addAttachment(name, "video/mp4", fis, "mp4");
    } catch (IOException ignored) {
      // best-effort attachment
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
