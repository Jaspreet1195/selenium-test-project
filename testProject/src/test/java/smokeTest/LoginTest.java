package smokeTest;

import org.testng.annotations.Test;

import base.BaseTest;

import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;


import pageObjects.LoginPO;

public class LoginTest extends BaseTest {
	LoginPO loginPO;

	@Test(description = "Verify login with valid credentials")
	@Description("This test verifies that a user can log in successfully.")
	@Severity(SeverityLevel.BLOCKER)
	@Epic("Authentication")
	@Feature("Login")
	public void login() {
		// launch browser
		loginPO = new LoginPO(getDriver());
		loginPO.loginFailed();
	}

}
