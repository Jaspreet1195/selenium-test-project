package smokeTest;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.LoginPO;

public class LoginTest extends BaseTest {
  private LoginPO loginPO;

  @BeforeMethod(alwaysRun = true)
  public void init() {
    loginPO = new LoginPO(getDriver());
  }

  @Test(description = "Verify login with invalid credentials shows validation", groups = "ui")
  @Description(
      "This test verifies that a user sees validation failed message when invalid credentials are used.")
  @Severity(SeverityLevel.BLOCKER)
  @Epic("Authentication")
  @Feature("Login")
  public void loginShowsValidationMessage() {
    String alertMessage = loginPO.loginFailed();
    Assert.assertTrue(
        alertMessage.toLowerCase().contains("validation"), "Expected validation alert.");
  }
}
