package productionSmoke;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import dataprovider.User.AsterWebAutomation;
import dataprovider.User.HttpsEndpointURL;
import pageObjects.Aster.CommonHomeDoctor;
import pageObjects.Aster.CommonHomePharmacy;
import pageObjects.Aster.HomePage;
import pageObjects.Aster.HttpsResponse;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import testUtils.BaseTestUser;

import testUtils.ReportsUtil;
import utils.DataProviderUtils;
import utils.WaitUtils;

import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static constants.GeneralConstants.specialities;
import static constants.GeneralConstants.Pharmacy;
import static constants.GeneralConstants.clinicsnearbyyou;
import static constants.GeneralConstants.cartpage;
import static constants.UserConstants.NumberofProducts;
import static utils.EncryptionDecryptionUtils.decrypt;

public class GetResponseValues extends BaseTestUser {

	public ExtentReports rep;
	public ExtentTest test;
	public HttpsResponse https;

	@BeforeClass(alwaysRun = true)
	public void setupTest() throws MalformedURLException {
		driver = initializeDriver();
		driver.get(getUrlFromPropertiesUser());
		// initialize reports
		rep = ReportsUtil.setReports();

		
		https = new HttpsResponse();
		
	}

//Verify Home Page List Values
	@Test(dataProvider = "EndPointURL", dataProviderClass = HttpsEndpointURL.class, priority = 1)
	// @Test(dataProvider = "getHomepageListValues")
	public void Verifyresponsecode(String endPointURL) throws InterruptedException, MalformedURLException, IOException {
		test = rep.createTest("Verifyresponsecode ");
		test.log(Status.INFO, "Verify response code");
		int value=https.getResponsecode(endPointURL);
	
		if(value==200)
		{
			
			test.log(Status.PASS, " Response code for EndPoint URL: " + "<b>" + endPointURL + "</b>" + " :"+value);

		} else {
			test.log(Status.FAIL, " Response code for EndPoint URL: " + "<b>" + endPointURL + "</b>" + " :"+value);
		}
		String msg=https.getResponsevalue(endPointURL);

		if(msg.equalsIgnoreCase("ok"))
		{
			test.log(Status.PASS, " Response message for EndPoint URL: " + "<b>" + endPointURL + "</b>" + " :"+msg);

		} else {
			test.log(Status.FAIL, " Response message for EndPoint URL: " + "<b>" + endPointURL + "</b>" + " :"+msg);
			
		
		}
		Writer responsevalue=https.getResponsebody(endPointURL);
		test.log(Status.PASS, " Response message for EndPoint URL: " + "<b>" + endPointURL + "</b>" + " :"+responsevalue);
	}



	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Case Failed is : " + result.getName() + " with parameters as "
					+ Arrays.asList(result.getParameters()));
			test.log(Status.FAIL, result.getThrowable().getMessage());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case Skipped is : " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case is Success : " + result.getName());
		}
		rep.flush();
	}
}
