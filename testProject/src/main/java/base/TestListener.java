package base;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor.Base;

import io.qameta.allure.Allure; 

public class TestListener extends BaseTest  implements ITestListener, ISuiteListener {

//	implements ITestListener
	
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("✅ Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("🎉 Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test Failed: " + result.getName());
        // Example: take screenshot here
        
        try {
            File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            String fileName = result.getName()
                    + "_" + Thread.currentThread().getId()
                    + "_" + System.currentTimeMillis() + ".png";

            Path dest = Paths.get("target", "screenshots", fileName);
            Files.createDirectories(dest.getParent());
            Files.copy(src.toPath(), dest);

            // Attach to TestNG report
            Reporter.log("<a href='" + dest.toString() + "'>Screenshot</a>");
            
//            // ✅ Attach to Allure report properly
//            byte[] fileContent = Files.readAllBytes(dest);
//            Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(fileContent));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ Test Skipped: " + result.getName());
    }
    
    @Override
    public void onStart(ISuite suite) {
        System.out.println(">>> Suite started: " + suite.getName());
        // Example: initialize reporting system(testng or allure) or environment setup
        // ReportManager.initReport();
        // EnvironmentManager.setupEnvironment();
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println(">>> Suite finished: " + suite.getName());
        // Example: finalize or flush report
        // ReportManager.flushReport();
    }
}
