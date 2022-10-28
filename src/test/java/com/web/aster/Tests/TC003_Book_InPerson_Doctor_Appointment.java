package com.web.aster.Tests;

import java.lang.reflect.Method;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.web.aster.Base.BaseClass;
import com.web.aster.Pages.AppointmentBookingStatusPage;
import com.web.aster.Pages.BookAppointmentPage;
import com.web.aster.Pages.CheckoutPage;
import com.web.aster.Pages.ConsultADoctorPage;
import com.web.aster.Pages.DoctorSpecialityPage;
import com.web.aster.Pages.HomePage;
import com.web.aster.Pages.LoginPage;
import com.web.aster.Pages.OTPPage;
import com.web.aster.Pages.OneAsterHomePage;
import com.web.aster.Pages.RegistrationPage;
import com.web.aster.Utilities.TestUtils;

public class TC003_Book_InPerson_Doctor_Appointment extends BaseClass{
	TestUtils utils;
	HomePage home;
	LoginPage login; 
	OTPPage otpp ;
	RegistrationPage register;
	OneAsterHomePage asterhome;
	ConsultADoctorPage doctor;
	DoctorSpecialityPage specialities;
	BookAppointmentPage appoint;
	CheckoutPage checkout;
	AppointmentBookingStatusPage bookingstatus;
	
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
		doctor = new ConsultADoctorPage();
		specialities = new DoctorSpecialityPage();
		appoint = new BookAppointmentPage();
		checkout = new CheckoutPage();
		bookingstatus = new AppointmentBookingStatusPage();
		
	}
	
	@BeforeMethod
	public void beforeMethod(Method m) {
		utils.log().info("\n" + "\n" + "****** starting test : " + m.getName() + " on browser: " + super.getBrowserName() + "******" + "\n");
	}

	@Test(priority = 1)
	public void Test_Click_Consult_A_Doctor_Button() throws InterruptedException {
		SoftAssert s1 = new SoftAssert();
		Thread.sleep(10000);
		
		if(asterhome.isAt()) {
			AssertJUnit.assertTrue(asterhome.clickConsultDoctorButton());
		}
		s1.assertAll();
	}
	
	@Test(priority = 2, dependsOnMethods = { "Test_Click_Consult_A_Doctor_Button" })
	public void Test_Select_A_Speciality() throws InterruptedException {
		SoftAssert s2 = new SoftAssert();
		Thread.sleep(5000);
		
		if(doctor.isAt()) {
			AssertJUnit.assertTrue(doctor.selectASpeciality("Orthopedics"));
		}
		s2.assertAll();
	}
	
	@Test(priority = 3, dependsOnMethods = { "Test_Select_A_Speciality" })
	public void Test_Click_Book_An_Appointment_Button() throws InterruptedException {
		SoftAssert s3 = new SoftAssert();
		Thread.sleep(5000);
		
		if(specialities.isAt()) {
			AssertJUnit.assertTrue(specialities.clickBookAppointmentButton());
		}
		s3.assertAll();
	}
	
	@Test(priority = 4, dependsOnMethods = { "Test_Click_Book_An_Appointment_Button" })
	public void Test_Select_Consultation_Type() throws InterruptedException {
		SoftAssert s4 = new SoftAssert();
		Thread.sleep(10000);
		
		if(appoint.isAt()) {
			AssertJUnit.assertTrue(appoint.clickInPersonConsultation());
		}
		s4.assertAll();
	}
	
	@Test(priority = 5, dependsOnMethods = { "Test_Select_Consultation_Type" })
	public void Test_Select_TimeSlot() throws InterruptedException {
		SoftAssert s5 = new SoftAssert();
		Thread.sleep(5000);
		
		if(appoint.isAt()) {
			appoint.selectNextTimeSlot();
		}
		s5.assertAll();
	}
	
	@Test(priority = 6, dependsOnMethods = { "Test_Select_TimeSlot" })
	public void Test_Click_Self_Pay() throws InterruptedException {
		SoftAssert s6 = new SoftAssert();
		Thread.sleep(5000);
		
		if(appoint.isAt()) {
			AssertJUnit.assertTrue(appoint.selectPaymentMethod());
		}
		s6.assertAll();
	}
	
	@Test(priority = 7, dependsOnMethods = { "Test_Click_Self_Pay" })
	public void Test_Click_Confirm_In_Person_Appointment() throws InterruptedException {
		SoftAssert s7 = new SoftAssert();
		Thread.sleep(5000);
		
		if(appoint.isAt()) {
			AssertJUnit.assertTrue(appoint.clickConfirmAppointmentButton());
		}
		s7.assertAll();
	}
	
	@Test(priority = 8, dependsOnMethods = { "Test_Click_Confirm_In_Person_Appointment" })
	public void Test_Click_Pay_At_Clinic_On_Checkout_Page() throws InterruptedException {
		SoftAssert s8 = new SoftAssert();
		Thread.sleep(5000);
		
		if(checkout.isAt()) {
//			AssertJUnit.assertTrue(checkout.displayAppointmentSummary());
			AssertJUnit.assertTrue(checkout.clickPayAtClinicRadioButton());
			AssertJUnit.assertTrue(checkout.clickConfirmButton());
		}
		s8.assertAll();
	}
	
	@Test(priority = 9, dependsOnMethods = { "Test_Click_Confirm_In_Person_Appointment" })
	public void Test_Booking_Status_Click_MyAsterLogo_Page() throws InterruptedException {
		SoftAssert s9 = new SoftAssert();
		Thread.sleep(8000);
		
		if(bookingstatus.isAt()) {
			AssertJUnit.assertTrue(bookingstatus.displayBookingStatus());
			AssertJUnit.assertTrue(bookingstatus.clickMyAsterLogo());
			Thread.sleep(10000);
		}
		s9.assertAll();
	}
	
}















