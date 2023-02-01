package productionSmoke;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import constants.UserConstants;
import dataprovider.User.AsterWebAutomation;
import pageObjects.Aster.CommonHomeDoctor;
import pageObjects.Aster.CommonHomePharmacy;
import pageObjects.Aster.HomePage;
import pageObjects.Aster.LoginPage;
import pageObjects.Aster.MenuPage;

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
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;

//import static constants.UserConstants.InsuraceID;
import static constants.UserConstants.Insuranceprovider;
import static constants.UserConstants.UATemailID;
import static constants.UserConstants.UATPassword;
import static utils.EncryptionDecryptionUtils.decrypt;
import static constants.GeneralConstants.DATA_PROPERTIES_FILE_NAME;

public class ManageAddress extends BaseTestUser {
	private HomePage homepage;
	private CommonHomePharmacy cmnpharmapage;
	private CommonHomeDoctor cmndoctorpage;
	private LoginPage loginpage;
	private MenuPage menupage;
	private UserConstants constant;
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
		cmnpharmapage = new CommonHomePharmacy(driver);
		cmndoctorpage = new CommonHomeDoctor(driver);
		loginpage = new LoginPage(driver);
		menupage= new MenuPage(driver);
		//constant =new UserConstants(driver);
	}

	
	@Test(priority = 1)
	public void VerifyAddNewAddress() throws InterruptedException {
		test = rep.createTest("VerifyAddNewAddress");
		test.log(Status.INFO, "Add New Address ");
		Verifyloginregister();
		homepage.ClickonMainLogo();
		 menupage.ClickonMenu();
		test.log(Status.PASS, " Able to click Successfully on " +"<b>"+ "Menu" + "</b>");
		String manageAddress = menupage.clickOnMenuManageAddresss();
		test.log(Status.PASS, " Able to click Successfully on " +"<b>"+ manageAddress + "</b>");
		  if(menupage.clickOnAddAddressButton())
				  {
			  test.log(Status.PASS, " Able to click Successfully on Add Address button");
				  }
		  else if(menupage.clickOnAddNewButton())
		          {
			   test.log(Status.PASS, " Able to click Successfully on Add New button ");
		          }
	   // menupage.clickOnSearchLocationTextBox();
	   // menupage.clickOnCloseOption();
	    menupage.clickOnMap();
		String confirmLocationButton = menupage.clickOnConfirmLocationButton();
	    test.log(Status.PASS, " Able to click Successfully on " +"<b>"+ confirmLocationButton+ "</b>");
	    menupage.clickOnBuildingNameTextBox();
	    menupage.clickOnFlatOrVillaTextBox();
	    menupage.clickOnHomeAddressTypeRadioButton();
	    menupage.clickOnPrimaryAddressCheckBox();
	    String saveUpdateAddressDetailsButton = menupage.clickOnSaveUpdateAddressDetailsButton();
	    test.log(Status.PASS, " Able to click Successfully on " +"<b>"+ saveUpdateAddressDetailsButton+ "</b>");
	    String AddressDetails=menupage.verifyAddress();
		if(AddressDetails.equals(""))
		{
			test.log(Status.FAIL, "Due to some technical issues Address not added");
		}
		else
		{
			test.log(Status.PASS, "Address added Successfully ,Address details are:"+"<b>" +AddressDetails );
		}
	
		//driver.close();
		
	}

	@Test(priority = 2)
	public void VerifyEditAddress() throws InterruptedException {
		test = rep.createTest("VerifyEditAddress");
		test.log(Status.INFO, "Edit Address ");
	
		menupage.clickOnEditAddressIcon();
	   // menupage.clickOnSearchLocationTextBox();
	   // menupage.clickOnCloseOption();
	    menupage.clickOnMap();
		String confirmLocationButton = menupage.clickOnConfirmLocationButton();
	    test.log(Status.PASS, " Able to click Successfully on " +"<b>"+ confirmLocationButton+ "</b>");
	    menupage.clickOnBuildingNameTextBox();
	    menupage.clickOnFlatOrVillaTextBox();
	    menupage.clickOnHomeAddressTypeRadioButton();
	    String saveUpdateAddressDetailsButton = menupage.clickOnSaveUpdateAddressDetailsButton();
	    test.log(Status.PASS, " Able to click Successfully on " +"<b>"+ saveUpdateAddressDetailsButton+ "</b>");
	    String AddressDetails=menupage.verifyAddress();
		if(AddressDetails.equals(""))
		{
			test.log(Status.FAIL, "Due to some technical issues Address not added");
		}
		else
		{
			test.log(Status.PASS, "Address edited Successfully ,Address details are:"+"<b>" +AddressDetails );
		}
	}
	

	@Test(priority = 3)
	public void VerifyRemoveAddress() throws InterruptedException {
		test = rep.createTest("VerifyRemoveAddress");
		test.log(Status.INFO, "Remove Address ");
		 if(menupage.clickOnAddAddressButton())
		  {
	  test.log(Status.PASS, " Able to click Successfully on Add Address button");
		  }
         else if(menupage.clickOnAddNewButton())
         {
	   test.log(Status.PASS, " Able to click Successfully on Add New button ");
         }
        // menupage.clickOnSearchLocationTextBox();
        // menupage.clickOnCloseOption();
      menupage.clickOnMap();
      String confirmLocationButton = menupage.clickOnConfirmLocationButton();
      test.log(Status.PASS, " Able to click Successfully on " +"<b>"+ confirmLocationButton+ "</b>");
      menupage.clickOnBuildingNameTextBox();
      menupage.clickOnFlatOrVillaTextBox();
      menupage.clickOnHomeAddressTypeRadioButton();
    //  menupage.clickOnPrimaryAddressCheckBox();
      String saveUpdateAddressDetailsButton = menupage.clickOnSaveUpdateAddressDetailsButton();
      test.log(Status.PASS, " Able to click Successfully on " +"<b>"+ saveUpdateAddressDetailsButton+ "</b>");
      String AddressDetails=menupage.verifyAddress();
      if(AddressDetails.equals(""))
       {
	   test.log(Status.FAIL, "Due to some technical issues Address not added");
       }
      else
       {
	  test.log(Status.PASS, "Address added Successfully ,Address details are:"+"<b>" +AddressDetails );
       }

		menupage.clickOnRemoveAddressIcon();
		menupage.clickOnRemoveButton();
		test.log(Status.PASS, "Address deleted Successfully");
	
		driver.close();
	}
		
	
	public void Verifyloginregister() throws InterruptedException
	{
		if (homepage.verifyLoginRegister()) {
			homepage.clickLoginRegister();
			test.log(Status.PASS, "Able to click on Login/Register ");
			loginpage.ClickonLoginwithEmailButton();
			loginpage.VerifyEmaiAddressLabel();
			String emailid = UATemailID;
			if (loginpage.ClickOnEmailAddressTextBox(UATemailID)) {
				test.log(Status.PASS, "Able to enter the email ID:" + emailid);
			}
			loginpage.ClickOnContinueButton_email();
			loginpage.VerifyPasswordLabel();
			String password = UATPassword;
			if (loginpage.ClickOnPasswordTextBox(UATPassword)) {
				test.log(Status.PASS, "Able to enter the password:" + password);
			}

			loginpage.ClickOnContinueButton_password();
			String userName = loginpage.getUserName();
			if (userName.equals("")) {
				test.log(Status.FAIL, "Due to some issues,user is not able to login");
			} else {
				test.log(Status.PASS, "Able to login successfully UserName is : " + userName);
			}
		} else {
			test.log(Status.FAIL, "Login/Register is not available");
		}
		
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
