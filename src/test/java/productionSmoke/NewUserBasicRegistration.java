package productionSmoke;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import dataprovider.User.AsterWebAutomation;
import pageObjects.Aster.CommonHomeDoctor;
import pageObjects.Aster.CommonHomePharmacy;
import pageObjects.Aster.HomePage;
import pageObjects.Aster.LoginPage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import testUtils.BaseTestUser;
import testUtils.ReportsUtil;
import utils.DataProviderUtils;
import utils.LoadPropertiesFileUtils;
import utils.WaitUtils;

import java.net.MalformedURLException;
import java.util.Arrays;

import java.util.Random;



import static constants.GeneralConstants.DATA_PROPERTIES_FILE_NAME;

@Test
public class NewUserBasicRegistration extends BaseTestUser {
	private HomePage homepage;
	
	private LoginPage loginpage;

	public ExtentReports rep;
	public ExtentTest test;
	// public static Properties prop = new Properties();

	@BeforeClass(alwaysRun = true)
	public void setupTest() throws MalformedURLException {
		driver = initializeDriver();
		driver.get(getUrlFromPropertiesUser());
		// initialize reports
		rep = ReportsUtil.setReports();

		homepage = new HomePage(driver);
		
		loginpage = new LoginPage(driver);
	}
   @Test(priority=1)
	public void VerifyNewRegistrationUser() throws InterruptedException {
		test = rep.createTest("VerifyNewRegistrationUser");
		test.log(Status.INFO, "Verfiy New User Registered");
		LoadPropertiesFileUtils.loadPropertiesFile(DATA_PROPERTIES_FILE_NAME);
		//String envValue = LoadPropertiesFileUtils.getenvironment();

		if (homepage.verifyLoginRegister()) {
			homepage.clickLoginRegister();
			test.log(Status.PASS, "Able to click on Login/Register ");
			
			String defaultSelectedCountryCode = loginpage.VerifyDefaultSelectedCountryCode();
			if (defaultSelectedCountryCode.equals("")) {
				test.log(Status.FAIL, "Country Code is Empty");
			} else {
				test.log(Status.PASS, "Default Country Code : "+ "<b>" + defaultSelectedCountryCode);
			}
			loginpage.ClickOnCountryCodeListDropdown();
			loginpage.ClickOnCountryCode();
			Random rand = new Random();
			int num = rand. nextInt(900000000) + 100000000;
			int numnamerand = rand. nextInt(900) + 100;
			int numname=numnamerand;
			  String UATMobileNumber=Integer.toString(num);
			//String ins=InsuraceID;
			String  UATMobile=UATMobileNumber;
			if (loginpage.ClickOnMobileNumberTextBox(UATMobile)) {
				test.log(Status.PASS, "Able to enter the Mobile Number: "+ "<b>" + UATMobile);
			
			}
			loginpage.ClickOnContinueButton();
			String OTP = loginpage.enterOTP();
			if(OTP.equals(""))
			{
				test.log(Status.FAIL, "Not Entered OTP");
				}else
				{
							
				test.log(Status.PASS, "Entered OTP: " + "<b>" + OTP);
							
				}
			loginpage.ClickOnContinueButton();
			 String UATFirstName="AutomationF"+numname;
				if(loginpage.ClickOnFirstNameTextBox(UATFirstName))
				{
				test.log(Status.PASS, "First Name: " + "<b>" + UATFirstName);
							
				}
				String  UATLastName="AutomationL"+numname;
				if(loginpage.ClickOnLastNameTextBox(UATLastName))
				{				
				test.log(Status.PASS, "Last Name: " + "<b>" + UATLastName);
							
				}
				String UATemailID="fnameLname"+numname+"@gmail.com";
				if (loginpage.ClickOnEmailAddressTextBox(UATemailID)) {
					test.log(Status.PASS, "Email ID: " + "<b>"+ UATemailID);
				}
				
				loginpage.ClickOnFemaleRadioButton();
				loginpage.ClickOnNationalityDropdown();
				//loginpage.ClickOnNationalitySearBar();
				//loginpage.ClickOnCountryfromSearchBar();
				loginpage.ClickOnDOBDDDropdown();
				loginpage.ClickOnDOBMMDropdown();
				loginpage.ClickOnDOBYYYYDropdown();
				loginpage.clickOnProceedButton();
				String userName = loginpage.getUserName();
				if (userName.equals("")) {
					test.log(Status.FAIL, "Due to some issues,user is not able to register");
				} else {
					test.log(Status.PASS, "New user registered successfully, UserName is : "+ "<b>" + userName);
				}
			} else {
				test.log(Status.FAIL, "Login/Register is not available");
			}
		driver.close();
				
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
