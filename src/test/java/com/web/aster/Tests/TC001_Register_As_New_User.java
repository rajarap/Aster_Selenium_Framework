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
import com.web.aster.Utilities.TestUtils;

public class TC001_Register_As_New_User extends BaseClass{
	TestUtils utils;
	HomePage home;
	LoginPage login; 
	
	
	@BeforeClass
	public void beforeClass() throws Exception {
		utils = new TestUtils();
		home = new HomePage();
		login = new LoginPage();
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
	
	@Test(priority = 3, dependsOnMethods = { "Test_Click_Login_Register_Link" })
	public void Test_UI_Elements_On_Login_Page() throws InterruptedException {
		SoftAssert s3 = new SoftAssert();
		Thread.sleep(3000);
		
		if(login.isAt()) {
			AssertJUnit.assertTrue(login.isTitleMessageDisplayed());
			AssertJUnit.assertTrue(login.isHelpTextDisplayed());
			AssertJUnit.assertTrue(login.isLabelDisplayed());
			AssertJUnit.assertTrue(login.isCountryCodeListBoxDisplayed());
			AssertJUnit.assertTrue(login.isMobileNumberTextBoxDisplayed());
			AssertJUnit.assertTrue(login.isContinueButtonDisplayed());
			AssertJUnit.assertTrue(login.isWillDoItLaterLinkDisplayed());
//			AssertJUnit.assertTrue(login.isLoginWithEmailButtonDisplayed());
		}	
		s3.assertAll();
	}
	
	@Test(priority = 4, dependsOnMethods = { "Test_UI_Elements_On_Login_Page" })
	public void Test_Login_As_New_User_On_Login_Page() throws InterruptedException {
		SoftAssert s4 = new SoftAssert();
		Thread.sleep(3000);

		if(login.isAt()) {
			AssertJUnit.assertTrue(login.selectItemInList(super.getCountryCode()));
			AssertJUnit.assertTrue(login.enterMobileNumber(super.generateMobileNumber(super.getCountryCode())));
			AssertJUnit.assertTrue(login.clickContinue());
		}
		s4.assertAll();
	}

}
