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
import static constants.UserConstants.DoctorName;
import static constants.UserConstants.CardNumber;
import static constants.UserConstants.ExpiryMonth;
import static constants.UserConstants.ExpiryYear;

import static constants.UserConstants.CVV;
import static constants.UserConstants.NameonThecard;
import static utils.EncryptionDecryptionUtils.decrypt;
import static constants.GeneralConstants.DATA_PROPERTIES_FILE_NAME;

public class BookAppointment extends BaseTestUser {

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
	public void BookAppointment() throws InterruptedException {
		test = rep.createTest("BookAppointment");
		test.log(Status.INFO, "Book an Appointment ");
		Verifyloginregister();
		
		String doctorName = DoctorName;
		if(homepage.GlobalSearchDoctor(DoctorName))
		{
			
			test.log(Status.PASS, "Value entered in Search box : "+doctorName+" is displayed");
		}

		homepage.clickOnGlobalSearchedResult();
		String date = cmndoctorpage.getSlotDate();
		String timeperiod = cmndoctorpage.getSlottimeperiod();
		String timeslot = cmndoctorpage.getSlottimeslot();
		cmndoctorpage.clickontimeslot();
		test.log(Status.PASS, "Slot Information" + "<b>" + date + "</b>" + "," + "<b>" + timeperiod + "</b>"
				+ "," + "<b>" + timeslot + "</b>");
		if (homepage.verifyLoginRegister()) {
			homepage.clickLoginRegister();
			// loginpage.ClickOnLoginPharmacy();
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
			if (homepage.verifyMergecartPopup()) {
				homepage.clicknewMergecart();
				test.log(Status.PASS, "Clicked on Cancel button");
			}

		}
		if (cmndoctorpage.VerifyCompleteProfile()) {
			test.log(Status.INFO, "Unable to book appointment due to Patient profile is not completed");
		}
		cmndoctorpage.clickonBookAppointment();
		cmndoctorpage.selectPayAtClinic();
		String fee = cmndoctorpage.getConsultationfee();
		if (!(fee.equals(""))) {
			test.log(Status.PASS, "Consultation fee of doctor is :" + "<b>" + fee);
		} else {
			test.log(Status.FAIL, "Due to some issues,consultation fee Is not showing");
		}
		cmndoctorpage.clickonconfirm();
		String bookingID = cmndoctorpage.getBookingID();
		if (!(bookingID.equals(""))) {
			test.log(Status.PASS, "Appointment booked Successfully , Booking ID :" + "<b>" + bookingID);
		} else {
			test.log(Status.FAIL, "Due to some issues,Appointment Is not booked");
		}


		
	}
	
	@Test(priority = 2)
	public void RescheduleAppointment() throws InterruptedException {
		test = rep.createTest("RescheduleAppointment");
		test.log(Status.INFO, "Reschedule Appointment");
		
		
		//cmndoctorpage.clickOnCheckAppointmentDetailsButton();
		homepage.ClickonMainLogo();
		menupage.ClickonMenu();
		
		menupage.clickOnMenuAppointments();
		String upcomingAppointment_DoctorInfo = menupage.clickOnUpcomingAppointmentDoctorInfo();
		test.log(Status.PASS, "Doctor Info: " + "<b>" +upcomingAppointment_DoctorInfo);
		
		String confirmedstatus = menupage.getConfirmedStatus();
		test.log(Status.PASS, "Appointment Status: " +  "<b>"+ confirmedstatus);
		
		String reschedule = menupage.clickOnRescheduleButton();
		test.log(Status.PASS, "Clicked on : " +  "<b>"+ reschedule + "</b>"+" button");
		
		menupage.clickOnYesProceedButton();
		
		
		String date = cmndoctorpage.getSlotDate();
		String timeperiod = cmndoctorpage.getSlottimeperiod();
		String timeslot = cmndoctorpage.getSlottimeslot_2();
		cmndoctorpage.clickontimeslot_2();
		test.log(Status.PASS, "Slot Information" + "<b>" + date + "</b>" + "," + "<b>" + timeperiod + "</b>"
				+ "," + "<b>" + timeslot + "</b>");
		
		String confirmappointment = cmndoctorpage.clickOnConfirmAppointmentButton();
		test.log(Status.PASS, "Clicked on : " +  "<b>"+ confirmappointment + "</b>"+ " button");
		
		String bookingID = cmndoctorpage.getBookingID();
		if (!(bookingID.equals(""))) {
			test.log(Status.PASS, "Appointment Rescheduled Successfully , Booking ID :" + "<b>" + bookingID);
		} else {
			test.log(Status.FAIL, "Due to some issues,Appointment Is not booked");
		}
		
		
		
	} 
	
	@Test(priority = 3)
	public void CancelAppointment() throws InterruptedException {
		test = rep.createTest("CancelAppointment");
		test.log(Status.INFO, "Cancel Appointment");
		
		homepage.ClickonMainLogo();
		menupage.ClickonMenu();
		
		menupage.clickOnMenuAppointments();
		
		String upcomingAppointment_DoctorInfo = menupage.clickOnUpcomingAppointmentDoctorInfo();
		test.log(Status.PASS, "Doctor Info: " + "<b>" +upcomingAppointment_DoctorInfo);
		
		
		String confirmedstatus = menupage.getConfirmedStatus();
		test.log(Status.PASS, "Appointment Status: " +  "<b>"+ confirmedstatus);
		
		menupage.clickOnCancelButton();
		
		menupage.clickOnReasonforCancellationRadioButton();
		String cancelappointment_button = menupage.clickOnCancelAppointmentButton();
		test.log(Status.PASS, "Clicked on : " +  "<b>"+ cancelappointment_button + "</b>"+" button");
		
		
		String cancelledstatus = menupage.getCancelledStatus();
		test.log(Status.PASS, "Appointment Status: " +  "<b>"+ cancelledstatus);
		
		
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
