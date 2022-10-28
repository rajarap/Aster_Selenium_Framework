package com.web.aster.Tests;

import java.lang.reflect.Method;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.web.aster.Base.BaseClass;
import com.web.aster.Pages.BookAppointmentPage;
import com.web.aster.Pages.ConsultADoctorPage;
import com.web.aster.Pages.DoctorSpecialityPage;
import com.web.aster.Pages.HomePage;
import com.web.aster.Pages.LoginPage;
import com.web.aster.Pages.OTPPage;
import com.web.aster.Pages.OneAsterHomePage;
import com.web.aster.Pages.PharmacyCheckoutPage;
import com.web.aster.Pages.PharmacyPage;
import com.web.aster.Pages.RegistrationPage;
import com.web.aster.Utilities.TestUtils;

public class TC004_Place_Order_And_Checkout_EPharmacy extends BaseClass{
	TestUtils utils;
	HomePage home;
	LoginPage login; 
	OTPPage otpp ;
	RegistrationPage register;
	OneAsterHomePage asterhome;
	PharmacyPage pharmacy;
	PharmacyCheckoutPage pcheckout;

	
	String firstName;
	String lastName;
	
	
	@BeforeClass
	public void beforeClass() throws Exception {
		utils = new TestUtils();
		home = new HomePage();
		login = new LoginPage();
		otpp = new OTPPage();
		register = new RegistrationPage();
		asterhome = new OneAsterHomePage();
		pharmacy = new PharmacyPage();
		pcheckout = new PharmacyCheckoutPage();
		
	}
	
	@BeforeMethod
	public void beforeMethod(Method m) {
		utils.log().info("\n" + "\n" + "****** starting test : " + m.getName() + " ******" + "\n");
	}

	@Test(priority = 1)
	public void Test_Click_Pharmacy_Button() throws InterruptedException {
		SoftAssert s1 = new SoftAssert();
		Thread.sleep(10000);
		
		if(asterhome.isAt()) {
			AssertJUnit.assertTrue(asterhome.clickPharmacyButton());
		}
		s1.assertAll();
	}
	
	@Test(priority = 2)
	public void Test_Select_Items_For_My_Cart() throws InterruptedException {
		SoftAssert s2 = new SoftAssert();
		Thread.sleep(10000);
		
		if(pharmacy.isAt()) {
			AssertJUnit.assertTrue(pharmacy.clickAddToCartButton());
		}
		s2.assertAll();
	}
	
	@Test(priority = 3)
	public void Test_Click_Cart_Badge() throws InterruptedException {
		SoftAssert s3 = new SoftAssert();
		Thread.sleep(10000);
		
		if(pharmacy.isAt()) {
			AssertJUnit.assertTrue(pharmacy.clickCartBadge());
		}
		s3.assertAll();
	}
	
	@Test(priority = 4)
	public void Test_Select_Payment_Type() throws InterruptedException {
		SoftAssert s4 = new SoftAssert();
		Thread.sleep(10000);
		
		if(pcheckout.isAt()) {
			AssertJUnit.assertTrue(pcheckout.clickCashCardOnDeliveryRadioButton());
		}
		s4.assertAll();
	}
	
	@Test(priority = 5)
	public void Test_Click_Proceed_Button() throws InterruptedException {
		SoftAssert s4 = new SoftAssert();
		Thread.sleep(10000);
		
		if(pcheckout.isAt()) {
			AssertJUnit.assertTrue(pcheckout.clickProceedButton());
			Thread.sleep(10000);
		}
		s4.assertAll();
	}	
}















