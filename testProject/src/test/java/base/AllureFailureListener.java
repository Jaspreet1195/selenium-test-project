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

  /** Best-effort failure video attachment so builds without recording still succeed. */
  private void attachVideo(String name) {
    String videoPath = System.getProperty("video.path");
    if (videoPath == null || videoPath.isBlank()) {
      videoPath = System.getenv("VIDEO_PATH"); // pipeline-friendly env override
    }
    if (videoPath == null || videoPath.isBlank()) {
      return; // no video was recorded for this run
    }

    File videoFile = new File(videoPath);
    if (!videoFile.exists() || !videoFile.isFile()) {
      return; // keep the test result; we just cannot attach the missing file
    }

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
