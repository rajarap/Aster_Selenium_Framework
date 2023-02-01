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
import static constants.GeneralConstants.specialities;

public class Lists extends BaseTestUser {
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

	
	@Test(dataProvider = "specialties", dataProviderClass = AsterWebAutomation.class, priority = 1)
	public void VerifyAddandRemoveDoctorsfromMyDoctorsLists(String Specialty) throws InterruptedException {
		test = rep.createTest("VerifyAddandRemoveDoctorsfromMyDoctorsLists");
		test.log(Status.INFO, "Verify Add and Remove Doctors from My Doctors Lists");
		Verifyloginregister();
		boolean flag=false;
		homepage.ClickonMainLogo();
		homepage.ClickonConsultaDoctor();
		if (homepage.VerifyHomepageFields(specialities)) {

			homepage.ClickonViewAll(specialities);
			List<WebElement> specialitylist = cmndoctorpage.getDoctorsBySpecialities();
			int count = specialitylist.size();
			//test.log(Status.PASS, "Total No.of Specialties displayed in Top Specialties are :" + "<b>" + count);
			for (int i = 1; i <= specialitylist.size(); i++) {
				String specialtytitle = cmndoctorpage.getDoctorSpecialitytitle(i);
				
				if (specialtytitle.equalsIgnoreCase(Specialty)) {

					test.log(Status.PASS, "Top Speciality :  " + "<b>" + specialtytitle + "</b>" + " is displayed");
					cmndoctorpage.ClickDoctorSpecialitylistpage(i);
					flag=true;
					break;

				} 
			}
			if(flag==false)
			{
				String specialtytitle = cmndoctorpage.getDoctorSpecialitytitle(1);

				test.log(Status.INFO, "Top Speciality :" + "<b>" + Specialty + "</b>"
						+ " is not matching with any of the Top Specialties list , so selected the Specialty as :"+specialtytitle);
				cmndoctorpage.ClickDoctorSpecialitylistpage(1);
			}
			//select the doctor
			String doctorName=cmndoctorpage.getDoctorName(1);
			if(doctorName.equals(""))
			{
			test.log(Status.FAIL, "No Doctors are displaying");
			}else
			{
						
			test.log(Status.PASS, "Doctor Name : " + "<b>" + doctorName);
						
			}
					
		}
			cmndoctorpage.ClickonAddTolistIcon(1);
			test.log(Status.PASS, "<b>"+"Clicked on Lists Icon Successfully");
				
			String MenuButton = menupage.ClickonMenu();
			test.log(Status.PASS, " Able to click Successfully on " +"<b>"+ MenuButton + "</b>");
				
			String MenuLists = menupage.clickOnMenuLists();
			test.log(Status.PASS, " Able to click Successfully on " +"<b>"+ MenuLists + "</b>");
				
			String MyDoctorsTab = menupage.clickOnMyDoctorsTab();
			test.log(Status.PASS, " Able to click Successfully on " +"<b>"+ MyDoctorsTab + "</b>");	
				
			String listDoctorName = cmndoctorpage.getListsDoctorsName(1);
			if(listDoctorName.equals(""))
			{
			test.log(Status.FAIL, "No Doctors are displaying");
			}else
			{
					
			test.log(Status.PASS, "Doctor Name : " + "<b>" + listDoctorName +"</b>" + " Added to "+ "<b>"+ MyDoctorsTab + "</b>" + " Successfully ");
			cmndoctorpage.ClickonAddTolistIcon(1);
			menupage.clickOnRemoveButtonMyDoctors();
			String nodoctorsfoundtext= menupage.VerifyNoDoctorsFoundText();
			if(nodoctorsfoundtext.equals(""))
			{
			test.log(Status.FAIL, "Doctors are available in My Doctors List");
			}else
			{
					
			test.log(Status.PASS, "Doctor removed from My Doctors List, "+"<b>" + nodoctorsfoundtext + "</b>" + " displayed ");
					
			}
					
			}
				
					
	} 
	
	@Test(dataProvider = "PharmacyPageCategory", dataProviderClass = AsterWebAutomation.class, priority = 2)

	public void VerifyAddandRemoveProductsToShoppingList(String category) throws InterruptedException {
		test = rep.createTest("VerifyAddandRemoveProductsToShoppingList");
		test.log(Status.INFO, "Verify Add and Remove Products from Shopping List");
		String Productname;
		homepage.ClickonMainLogo();
		homepage.ClickonPharmacy();
		
		if (homepage.VerifyHomepageFields(category)) {
			test.log(Status.PASS, " In Home Page " + "<b>" + category + "</b>" + " is displayed");
			homepage.ClickonViewAll(category);
		}
		
				Productname = cmnpharmapage.getProductName(1);
				test.log(Status.PASS, " Product Name : " + "<b>" + Productname);
				
				cmnpharmapage.ClickonAddToWishlistIcon(1);
				test.log(Status.PASS, "<b>"+"Clicked on Add to WishList Icon Successfully");
			
				String MenuButton = menupage.ClickonMenu();
				test.log(Status.PASS, " Able to click Successfully on " +"<b>"+ MenuButton + "</b>");
					
			    menupage.clickOnMenuLists();
				test.log(Status.PASS, " Able to click Successfully on " +"<b>"+ "Lists" + "</b>");
					
				String ShoppingListTab = menupage.clickOnShoppingListTab();
				test.log(Status.PASS, " Able to click Successfully on " +"<b>"+ ShoppingListTab + "</b>");
					
				String listProductsName = cmnpharmapage.getListsProductsName(1);
				if(listProductsName.equals(""))
				{
				test.log(Status.FAIL, "No Products are displaying");
				}else
				{
						
				test.log(Status.PASS, "Product Name : " + "<b>" + listProductsName +"</b>" + " Added to "+ "<b>"+ ShoppingListTab + "</b>" + " Successfully ");
						
				}
				menupage.clickOnRemoveButtonFromShoppingList();
				test.log(Status.PASS, "Clicked on Remove button from Shopping List ");
				String thereisnothingheretext= menupage.VerifyThereIsNothingHereText();
				if(thereisnothingheretext.equals(""))
				{
				test.log(Status.FAIL, "Products are available in Shopping List");
				}else
				{
						
				test.log(Status.PASS, "Product removed from Shopping List, "+"<b>" + thereisnothingheretext + "</b>" + " displayed ");
						
				}
				
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
