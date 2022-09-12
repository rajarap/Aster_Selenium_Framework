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

public class HomePageVerification extends BaseClass{
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
		
		AssertJUnit.assertTrue(home.isButtonDisplayed("Consult a Doctor"));
		AssertJUnit.assertTrue(home.isButtonDisplayed("Pharmacy"));
		AssertJUnit.assertTrue(home.islabelDisplayed("Popular Pharmacy Categories"));
		AssertJUnit.assertTrue(home.islabelDisplayed("Top Specialties"));
		AssertJUnit.assertTrue(home.islabelDisplayed("Top Deals for You"));
		AssertJUnit.assertTrue(home.islabelDisplayed("Best Seller"));
		AssertJUnit.assertTrue(home.islabelDisplayed("Clinics Nearby You"));
		
		s1.assertAll();
	}
	
	
}
