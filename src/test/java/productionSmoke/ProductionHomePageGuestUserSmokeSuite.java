package productionSmoke;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import dataprovider.User.AsterWebAutomation;
import pageObjects.Aster.CommonHomeDoctor;
import pageObjects.Aster.CommonHomePharmacy;
import pageObjects.Aster.HomePage;

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

public class ProductionHomePageGuestUserSmokeSuite extends BaseTestUser {
	private HomePage homepage;
	private CommonHomePharmacy cmnpharmapage;
	private CommonHomeDoctor cmndoctorpage;
	public ExtentReports rep;
	public ExtentTest test;

	@BeforeClass(alwaysRun = true)
	public void setupTest() throws MalformedURLException {
		driver = initializeDriver();
		driver.get(getUrlFromPropertiesUser());
		// initialize reports
		rep = ReportsUtil.setReports();

		homepage = new HomePage(driver);
		cmnpharmapage = new CommonHomePharmacy(driver);
		cmndoctorpage = new CommonHomeDoctor(driver);
	}

//Verify Home Page List Values
	@Test(dataProvider = "HomePage", dataProviderClass = AsterWebAutomation.class, priority = 1)
	// @Test(dataProvider = "getHomepageListValues")
	public void VerifyHomepagelist(String homepagelist) throws InterruptedException {
		test = rep.createTest("VerifyHomepagelist "+homepagelist);
		test.log(Status.INFO, "Verfiy Home Page ");
		if (homepage.VerifyHomepageFields(homepagelist)) {
			test.log(Status.PASS, " In Home Page " + "<b>" + homepagelist + "</b>" + " is displayed");

		} else {
			test.log(Status.FAIL, " In Home Page " + "<b>" + homepagelist + "</b>" + " is not available");
		}

	}

	// Verify Popular Pharmacy Categories
	@Test(priority = 2)
	public void VerifyPopularPharmacyCategories() throws InterruptedException {
		try {

			test = rep.createTest("VerifyPopularPharmacyCategories " + Pharmacy);
			test.log(Status.INFO, "VerifyPopularPharmacyCategories " + Pharmacy);

			if (homepage.VerifyHomepageFields(Pharmacy)) {

				homepage.ClickonViewAll(Pharmacy);
				List<WebElement> categorieslist = cmnpharmapage.getPopularPharmacyCategories();
				int count = categorieslist.size();
				test.log(Status.PASS, "Total No.of categories displayed on Popular Pharmacy categories are :" + "<b>"
						+ count + "</b>");
				for (int i = 1; i <= categorieslist.size(); i++) {
					String Category = cmnpharmapage.ClickPharmacyCategorylistpage(i);
					test.log(Status.PASS, "Able to click Successfully on " + "<b>" + Category);
					if (cmnpharmapage.VerifyPharmacyProductslistpagetitle(Category)) {

						test.log(Status.PASS,
								"Popular Pharmacy categories " + "<b>" + Category + "</b>" + " is displayed");

					} else {
						test.log(Status.FAIL, "Popular Pharmacy categories" + "<b>" + Category + "</b>"
								+ " is not matching with Popular Pharmacy Details Page");
					}
					String ProductCount = cmnpharmapage.returnproductcount();
					if (cmnpharmapage.VerifyProductscount()) {
						test.log(Status.PASS, "Popular Pharmacy categories " + "<b>" + Category + "</b>"
								+ " is displayed " + "<b>" + ProductCount + "</b>");

					} else {
						test.log(Status.INFO, "No Results Found");
					}
					homepage.ClickonMainLogo();
					test.log(Status.PASS, "Able to click Successfully on Main Logo");
					homepage.ClickonViewAll(Pharmacy);
					test.log(Status.PASS, "Able to click Successfully on View All");
				}
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(Status.FAIL, "Element is not available");
		}

	}

	// Verify Top specialties
	@Test(priority = 3)
	public void VerifyTopSpecialties() throws InterruptedException {

		try {
			test = rep.createTest("VerifyTopSpecialties " + specialities);
			test.log(Status.INFO, "Verify Top Specialties" + specialities);
			homepage.ClickonMainLogo();
			if (homepage.VerifyHomepageFields(specialities)) {

				homepage.ClickonViewAll(specialities);
				List<WebElement> specialitylist = cmndoctorpage.getTopSpecialties();
				int count = specialitylist.size();
				test.log(Status.PASS, "Total No.of Specialties displayed in Top Specialties are :" + "<b>" + count);
				for (int i = 1; i <= specialitylist.size(); i++) {
					String specialty = cmndoctorpage.ClickDoctorSpecialitylistpage(i);
					test.log(Status.PASS, "Able to click Successfully on " + "<b>" + specialty);
					if (cmndoctorpage.VerifyDoctorSpecialitylistpagetitle(specialty)) {

						test.log(Status.PASS, "Top Speciality :  " + "<b>" + specialty + "</b>" + " is displayed");

					} else {
						test.log(Status.FAIL, "Top Speciality :" + "<b>" + specialty + "</b>"
								+ " is not matching with Top Specialties list");
					}
					String ProductCount = cmndoctorpage.returndoctorcount();
					if (cmndoctorpage.VerifyProductscount()) {
						test.log(Status.PASS, "Top Speciality : " + "<b>" + specialty + "</b>" + " is displayed "
								+ "<b>" + ProductCount);

					} else {
						test.log(Status.FAIL, "No Results Found");
					}
					homepage.ClickonMainLogo();
					test.log(Status.PASS, "Able to click Successfully on Main Logo");

					homepage.ClickonViewAll(specialities);
					test.log(Status.PASS, "Able to click Successfully on View All");

				}
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(Status.FAIL, "Element is not available");
		}
	}

	// Verify Clinics Nearby You
	@Test(priority = 4)
	public void VerifyClinicsNearbyYou() throws InterruptedException {

		test = rep.createTest("VerifyClinicsNearbyYou " + clinicsnearbyyou);
		test.log(Status.INFO, "Verify ClinicsNearbyYou" + clinicsnearbyyou);
		homepage.ClickonMainLogo();
		if (homepage.VerifyHomepageFields(clinicsnearbyyou)) {

			homepage.ClickonViewAll(clinicsnearbyyou);

			String count = cmndoctorpage.clinicsavailable();
			test.log(Status.PASS, "Total No.of Clinics available are :" + "<b>" + count);

			List<WebElement> ClinicsList = cmndoctorpage.getClinicsList();
			List<WebElement> page = cmndoctorpage.getPagenumbers();

			//for (int j = 2; j <= page.size() - 1; j++) {
				for (int j = 2; j <= 2; j++) {
				cmndoctorpage.clickonPage(j);
				for (int i = 1; i <= ClinicsList.size(); i++) {
					try {
						String ClinicName = cmndoctorpage.getClinicname(i);
						test.log(Status.PASS, "Clinic " + "<b>" + ClinicName + "</b>" + " is Displayed");
						cmndoctorpage.clickonClinics(i);
						/*
						 * if(cmndoctorpage.VerifyDoctorSpecialitylistpagetitle(ClinicName)) {
						 * 
						 * test.log(Status.PASS, "Clinic Name :  " + ClinicName + " is displayed");
						 * 
						 * } else { test.log(Status.FAIL, "Clinic Name :" + ClinicName +
						 * " is not matching with Clinics list"); }
						 */
						if (cmndoctorpage.clickonViewAll()) {
							if (cmndoctorpage.verifydoctorcount()) {
								String ProductCount = cmndoctorpage.returndoctorcount();

								if (cmndoctorpage.VerifyProductscount()) {
									test.log(Status.PASS, "No.of Doctors displayed in " + "<b>" + ClinicName + "</b>"
											+ " are: " + "<b>" + ProductCount);

								}
							} else {
								test.log(Status.INFO, "No Results Found");
							}

						} else {
							test.log(Status.FAIL,
									"Clinic Name :" + "<b>" + ClinicName + "</b>" + " No Doctors are available");
						} // cmndoctorpage.clickonViewAll()
						cmndoctorpage.clickonAsterclinicinHirerachy();
						if (j > 2) {
							cmndoctorpage.clickonPage(j);
						}
					} catch (org.openqa.selenium.NoSuchElementException e) {
						test.log(Status.FAIL, "Element is not available");
						cmndoctorpage.clickonAsterclinicinHirerachy();
						if (j > 2) {
							cmndoctorpage.clickonPage(j);
						}
					} 

				} // ClinicsList.size()
			} // page.size()
		}// VerifyHomepageFields(
	}

	// Verify Add product to cart
	@Test(dataProvider = "Pharmacy", dataProviderClass = AsterWebAutomation.class, priority = 5)

	public void VerifyAddandRemoveProducttoCart(String category) throws InterruptedException {
		test = rep.createTest("VerifyAddandRemoveProducttoCart ");
		test.log(Status.INFO, "Verify Add and Remove Products to Cart ");
		String Productname,cartProductname,Price,cartPrice;
		homepage.ClickonMainLogo();
		HashMap<String, String> ProductDetails = new HashMap<String, String>();
		HashMap<String, String> CartProductDetails = new HashMap<String, String>();
		if (homepage.VerifyHomepageFields(category)) {
			test.log(Status.PASS, " In Home Page " + "<b>" + category + "</b>" + " is displayed");
			homepage.ClickonViewAll(category);
			for (int i = 1; i <= NumberofProducts; i++) {
				Productname = cmnpharmapage.getProductName(i);
				Price = cmnpharmapage.getProductPrice(i).trim();
				cmnpharmapage.ClickonAddToCart(1);
				String count=cmnpharmapage.cartitemscountvalidation();
				int countvalue=Integer.parseInt(count);  
				if (countvalue==i) {

					test.log(Status.PASS, " Product selected is" + "<b>" + Productname + "</b>"
							+ "Price for the product is :" + "<b>" + Price);
					ProductDetails.put(Productname, Price);
					
				} else {
					test.log(Status.INFO, "" + "<b>" + Productname + "</b>" + "Product is out of stock");

				}

			}
			cmnpharmapage.ClickonCartIcon();
			if (cmnpharmapage.verifyactivepage(cartpage)) {
				String count = cmnpharmapage.Cartproductscount();
				test.log(Status.PASS, " Able to see the cart page:No.of Products available are :" + "<b>" + count);
				int cartitems= cmnpharmapage.getcartitemssize().size();
				for(int i=1;i<=cartitems;i++)
				{
					cartProductname = cmnpharmapage.getProductNamefromcart(i);
					cartPrice = cmnpharmapage.getProductPricefromcart(i);
					test.log(Status.PASS, " Added product to cart successfully:" + "<b>" + cartProductname + "</b>"
							+ "Price for the product is :" + "<b>" + cartPrice);
					CartProductDetails.put(cartProductname, cartPrice);
				}
				if(cmnpharmapage.verifycartitems(ProductDetails, CartProductDetails))
				{
					test.log(Status.PASS, "Cart Items are matching ");
					
					if(cmnpharmapage.RemoveCartItemsfromCart())
					{
						test.log(Status.PASS, "Products removed from Cart Successfully");
					}
					
				}
				else
				{
					test.log(Status.FAIL, "Cart Items are not matching ");
				}
			} else {
				test.log(Status.FAIL, "Cart page is not loaded");
			}
			
		

		} else {
			test.log(Status.FAIL, " In Home Page " + category + " is not available");
		}
	}
		
		//Verify Global Search
		@Test(dataProvider = "searchValues", dataProviderClass = AsterWebAutomation.class, priority = 7)
		public void VerifySearchValues(String searchvalue) throws InterruptedException {
			test = rep.createTest("VerifySearchValues ");
			test.log(Status.INFO, "Verfiy Search Page ");
			homepage.ClickonMainLogo();
			String Value=homepage.Globalsearch(searchvalue);
			if(Value.equals(""))
			{
				test.log(Status.FAIL, "Value entered in Search box : "+searchvalue+" is not displayed");
				
				
			}else
			{
				test.log(Status.PASS, "Value entered in Search box : "+Value+" is displayed");
			}

		}

//Book appointment Via Specialty
		@Test(dataProvider = "specialties", dataProviderClass = AsterWebAutomation.class, priority = 6)
		public void BookAppointment(String Specialty) throws InterruptedException {
			test = rep.createTest("VerifyBookAppointment ");
			test.log(Status.INFO, "Verfiy Book Appointment via Specialty ");
			boolean flag=false;
			homepage.ClickonMainLogo();
			if (homepage.VerifyHomepageFields(specialities)) {

				homepage.ClickonViewAll(specialities);
				List<WebElement> specialitylist = cmndoctorpage.getTopSpecialties();
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
					cmndoctorpage.ClickDoctorName(1);
					test.log(Status.PASS, "Doctor Name" + "<b>" + doctorName);
					String date=cmndoctorpage.getSlotDate();
					String timeperiod=cmndoctorpage.getSlottimeperiod();
					String timeslot=cmndoctorpage.getSlottimeslot();
					cmndoctorpage.clickontimeslot();
					test.log(Status.PASS, "Slot Information" + "<b>" + date +"</b>"+","+"<b>" + timeperiod +"</b>"+","+"<b>" + timeslot +"</b>");
					if(homepage.verifyLoginRegister())
					{
						test.log(Status.PASS, "As a Guest User, able to see the Login/Register CTA in the Bookappointment page");
					}
					else
					{
						test.log(Status.FAIL, "As a Guest User, not able to see the Login/Register CTA in the Bookappointment page");
					}
				}
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
