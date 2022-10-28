package com.web.aster.Tests;

import java.lang.reflect.Method;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.web.aster.Base.BaseClass;
import com.web.aster.Pages.HomePage;
import com.web.aster.Pages.LoginPage;
import com.web.aster.Pages.OTPPage;
import com.web.aster.Pages.RegistrationPage;
import com.web.aster.Utilities.TestUtils;

public class TC001_Register_As_New_User extends BaseClass{
	TestUtils utils;
	HomePage home;
	LoginPage login; 
	OTPPage otpp ;
	RegistrationPage register;
	
	String firstName;
	String lastName;
	
	
	@BeforeClass
	public void beforeClass() throws Exception {
		utils = new TestUtils();
		home = new HomePage();
		login = new LoginPage();
		otpp = new OTPPage();
		register = new RegistrationPage();
	}
	
	@BeforeMethod
	public void beforeMethod(Method m) {
		utils.log().info("\n" + "\n" + "****** starting test : " + m.getName() + " ******" + "\n");
	}

	@Test(priority = 1)
	public void Test_UI_Elements_On_Application_Home_Page() throws InterruptedException {
		SoftAssert s1 = new SoftAssert();
		Thread.sleep(10000);
		
		if(home.isAt()) {
			AssertJUnit.assertTrue(home.isLoginRegisterLinkDisplayed());
		}
		s1.assertAll();
	}
	
	@Test(priority = 2, dependsOnMethods = { "Test_UI_Elements_On_Application_Home_Page" })
	public void Test_Click_Login_Register_Link() throws InterruptedException {
		SoftAssert s2 = new SoftAssert();
		Thread.sleep(3000);
		
		if(home.isAt()) {
			AssertJUnit.assertTrue(home.clickLoginRegisterLink());
		}	
		s2.assertAll();
	}
	
	@Test(priority = 4, dependsOnMethods = { "Test_Click_Login_Register_Link" })
	public void Test_Login_As_New_User_On_Login_Page() throws InterruptedException {
		SoftAssert s4 = new SoftAssert();
		Thread.sleep(3000);

		if(login.isAt()) {
			String cc = super.getCountryCode();
			AssertJUnit.assertTrue(login.selectItemInList(cc));
			AssertJUnit.assertTrue(login.enterMobileNumber(super.getMobileNumber(cc)));
			AssertJUnit.assertTrue(login.clickContinue());
		}
		s4.assertAll();
	}
	
	@Test(priority = 6, dependsOnMethods = { "Test_Login_As_New_User_On_Login_Page" })
	public void Test_Enter_OTP_On_OTP_Page() throws InterruptedException {
		SoftAssert s6 = new SoftAssert();
		Thread.sleep(5000);
		
		if(otpp.isAt()) {
			AssertJUnit.assertTrue(otpp.enterOTP("123456"));
			AssertJUnit.assertTrue(otpp.clickContinue());
		}
		s6.assertAll();
	}
	
	@Test(priority = 8, dependsOnMethods = { "Test_Enter_OTP_On_OTP_Page" })
	public void Test_User_Registration_On_Registration_Page() throws InterruptedException {
		SoftAssert s8 = new SoftAssert();
		firstName = super.getFirstName();
		lastName = super.getLastName();
		
		if(register.isAt()) {
			AssertJUnit.assertTrue(register.selectTitleRadioButton("Mr."));
			AssertJUnit.assertTrue(register.enterFirstName(firstName));
			AssertJUnit.assertTrue(register.enterLasttName(lastName));
			AssertJUnit.assertTrue(register.enterEmailAddress(firstName+"."+lastName+"@gmail.com"));
			AssertJUnit.assertTrue(register.selectNationalityFromList(super.getCountryName()));
			AssertJUnit.assertTrue(register.enterPassword("asterOne"));
			AssertJUnit.assertTrue(register.clickShowPassword());
			AssertJUnit.assertTrue(register.clickShowPassword());
			AssertJUnit.assertTrue(register.enterConfirmPassword("asterOne"));
			AssertJUnit.assertTrue(register.clickShowConfirmPassword());
			AssertJUnit.assertTrue(register.clickShowConfirmPassword());
			super.scrollToBottom();
			AssertJUnit.assertTrue(register.clickTCCheckBox());
			AssertJUnit.assertTrue(register.clickRegister());
		}
		s8.assertAll();
	}
}






















//@Test(priority = 3, dependsOnMethods = { "Test_Click_Login_Register_Link" })
//public void Test_UI_Elements_On_Login_Page() throws InterruptedException {
//	SoftAssert s3 = new SoftAssert();
//	Thread.sleep(3000);
//	
//	if(login.isAt()) {
//		AssertJUnit.assertTrue(login.isTitleMessageDisplayed());
//		AssertJUnit.assertTrue(login.isHelpTextDisplayed());
//		AssertJUnit.assertTrue(login.isLabelDisplayed());
//		AssertJUnit.assertTrue(login.isCountryCodeListBoxDisplayed());
//		AssertJUnit.assertTrue(login.isMobileNumberTextBoxDisplayed());
//		AssertJUnit.assertTrue(login.isContinueButtonDisplayed());
//		AssertJUnit.assertTrue(login.isWillDoItLaterLinkDisplayed());
//		AssertJUnit.assertTrue(login.isLoginWithEmailButtonDisplayed());
//	}	
//	s3.assertAll();
//}
//
//@Test(priority = 4, dependsOnMethods = { "Test_UI_Elements_On_Login_Page" })
//public void Test_Login_As_New_User_On_Login_Page() throws InterruptedException {
//	SoftAssert s4 = new SoftAssert();
//	Thread.sleep(3000);
//
//	if(login.isAt()) {
//		AssertJUnit.assertTrue(login.selectItemInList(super.getCountryCode()));
//		AssertJUnit.assertTrue(login.enterMobileNumber(super.getMobileNumber(super.getCountryCode())));
//		AssertJUnit.assertTrue(login.clickContinue());
//	}
//	s4.assertAll();
//}
//
//@Test(priority = 5, dependsOnMethods = { "Test_Login_As_New_User_On_Login_Page" })
//public void Test_UI_Elements_On_OTP_Page() throws InterruptedException {
//	SoftAssert s5 = new SoftAssert();
//	Thread.sleep(2000);
//	
//	if(otpp.isAt()) {
//		AssertJUnit.assertTrue(otpp.isHelpTextDisplayed());
//		AssertJUnit.assertTrue(otpp.isLabelDisplayed());
//		AssertJUnit.assertTrue(otpp.isOTPTextBox1Displayed());
//		AssertJUnit.assertTrue(otpp.isOTPTextBox2Displayed());
//		AssertJUnit.assertTrue(otpp.isOTPTextBox3Displayed());
//		AssertJUnit.assertTrue(otpp.isOTPTextBox4Displayed());
//		AssertJUnit.assertTrue(otpp.isOTPTextBox5Displayed());
//		AssertJUnit.assertTrue(otpp.isOTPTextBox6Displayed());
//		AssertJUnit.assertTrue(otpp.isOTPTimerDisplayed());
//		AssertJUnit.assertTrue(otpp.isResendTextDisplayed());
//		AssertJUnit.assertTrue(otpp.isContinueButtonDisplayed());
//	}	
//	s5.assertAll();
//}
//
//@Test(priority = 6, dependsOnMethods = { "Test_UI_Elements_On_OTP_Page" })
//public void Test_Enter_OTP_On_OTP_Page() throws InterruptedException {
//	SoftAssert s6 = new SoftAssert();
//	Thread.sleep(5000);
//	
//	if(otpp.isAt()) {
//		AssertJUnit.assertTrue(otpp.enterOTP("123456"));
//		AssertJUnit.assertTrue(otpp.clickContinue());
//	}
//	s6.assertAll();
//}
//
//@Test(priority = 7, dependsOnMethods = { "Test_Enter_OTP_On_OTP_Page" })
//public void Test_UI_Elements_On_Registration_Page() throws InterruptedException {
//	SoftAssert s7 = new SoftAssert();
//	Thread.sleep(5000);
//
//	if(register.isAt()) {
//		AssertJUnit.assertTrue(register.isTitleDisplayed());
//		AssertJUnit.assertTrue(register.isHelpTextDisplayed());
//		AssertJUnit.assertTrue(register.isTitleLabelDisplayed());
//		AssertJUnit.assertTrue(register.isMrRadioButtonDisplayed());
//		AssertJUnit.assertTrue(register.isMrsRadioButtonDisplayed());
//		AssertJUnit.assertTrue(register.isMissRadioButtonDisplayed());
//		AssertJUnit.assertTrue(register.isFirstNameLabelDisplayed());
//		AssertJUnit.assertTrue(register.isFirstNameTextBoxDisplayed());
//		AssertJUnit.assertTrue(register.isLastNameLabelDisplayed());
//		AssertJUnit.assertTrue(register.isLastNameTextBoxDisplayed());
//		AssertJUnit.assertTrue(register.isEmailAddressLabelDisplayed());
//		AssertJUnit.assertTrue(register.isEmailAddressTextBoxDisplayed());
//		AssertJUnit.assertTrue(register.isNationalityLabelDisplayed());
//		AssertJUnit.assertTrue(register.isNationalityListBoxDisplayed());
//		AssertJUnit.assertTrue(register.isDOBLabelDisplayed());
//		AssertJUnit.assertTrue(register.isDOBTextBoxDisplayed());
//		AssertJUnit.assertTrue(register.isPasswordLabelDisplayed());
//		AssertJUnit.assertTrue(register.isPasswordTextBoxDisplayed());
//		AssertJUnit.assertTrue(register.isShowPasswordIconDisplayed());
//		AssertJUnit.assertTrue(register.isConfirmPasswordLabelDisplayed());
//		AssertJUnit.assertTrue(register.isConfirmPasswordTextBoxDisplayed());
//		AssertJUnit.assertTrue(register.isShowConfirmPasswordIconDisplayed());
//		super.scrollUp();
//		AssertJUnit.assertTrue(register.isRegisterButtonDisplayed());
//	}	
//	s7.assertAll();
//}
//
//@Test(priority = 8, dependsOnMethods = { "Test_UI_Elements_On_Registration_Page" })
//public void Test_User_Registration_On_Registration_Page() throws InterruptedException {
//	SoftAssert s8 = new SoftAssert();
//	firstName = super.getFirstName();
//	lastName = super.getLastName();
//	
//	if(register.isAt()) {
//		AssertJUnit.assertTrue(register.selectTitleRadioButton("Mr."));
//		AssertJUnit.assertTrue(register.enterFirstName(firstName));
//		AssertJUnit.assertTrue(register.enterLasttName(lastName));
//		AssertJUnit.assertTrue(register.enterEmailAddress(firstName+"."+lastName+"@gmail.com"));
//		AssertJUnit.assertTrue(register.selectNationalityFromList(super.getCountryName()));
//		AssertJUnit.assertTrue(register.enterPassword("asterOne"));
//		AssertJUnit.assertTrue(register.clickShowPassword());
//		AssertJUnit.assertTrue(register.clickShowPassword());
//		AssertJUnit.assertTrue(register.enterConfirmPassword("asterOne"));
//		AssertJUnit.assertTrue(register.clickShowConfirmPassword());
//		AssertJUnit.assertTrue(register.clickShowConfirmPassword());
//		AssertJUnit.assertTrue(register.clickTCCheckBox());
//		AssertJUnit.assertTrue(register.clickRegister());
//	}
//	s8.assertAll();
//}
