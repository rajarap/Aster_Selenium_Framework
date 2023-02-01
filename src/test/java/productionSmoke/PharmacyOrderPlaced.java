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
import static constants.UserConstants.NumberofProducts;
import static constants.UserConstants.UATemailID;
import static constants.UserConstants.UATPassword;
import static constants.GeneralConstants.Pharmacy;
import static constants.GeneralConstants.Categories_bestseller;
import static constants.GeneralConstants.Categories_TopDeals;
import static utils.EncryptionDecryptionUtils.decrypt;
import static constants.GeneralConstants.DATA_PROPERTIES_FILE_NAME;
import static constants.GeneralConstants.cartpage;

public class PharmacyOrderPlaced extends BaseTestUser {
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

	

	@Test( priority = 1)

	public void VerifyOrderPlacedwithBestseller() throws InterruptedException {
		test = rep.createTest("VerifyOrderPlacedwithCashorCardOnDelivery ");
		test.log(Status.INFO, "Verify Order Placed with Cash or Card on Delivery");
		String Productname, cartProductname, Price, cartPrice;
		Verifyloginregister();
		homepage.ClickonMainLogo();
		//homepage.ClickonPharmacy();
		HashMap<String, String> ProductDetails = new HashMap<String, String>();
		HashMap<String, String> CartProductDetails = new HashMap<String, String>();
		if (homepage.VerifyHomepageFields(Categories_bestseller)) {
			test.log(Status.PASS, " In Home Page " + "<b>" + Categories_bestseller + "</b>" + " is displayed");
			// if products are available to cart ,removing the existing products and adding
			// the new products from the cart
			// String Count=cmnpharmapage.cartitemscountvalidation();
			cmnpharmapage.ClickonCartIcon();
			if (cmnpharmapage.verifycartitemcount() == false) {
				cmnpharmapage.ClickonCartIcon();
				if (cmnpharmapage.RemoveCartItemsfromCart()) {
					test.log(Status.PASS, "Products removed from Cart Successfully");
				}
				else
				{
					test.log(Status.FAIL, "Due to some issues products are removed from Cart");
				}

			}
			homepage.ClickonMainLogo();
			//homepage.ClickonPharmacy();
			homepage.ClickonViewAll(Categories_bestseller);

			for (int i = 1; i <= NumberofProducts; i++) {
				Productname = cmnpharmapage.getProductName(i);
				Price = cmnpharmapage.getProductPrice(i).trim();
				cmnpharmapage.ClickonAddToCart(1);
				String count = cmnpharmapage.cartitemscountvalidation();
				int countvalue = Integer.parseInt(count);
				if (countvalue == i) {

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
				int cartitems = cmnpharmapage.getcartitemssize().size();
				for (int i = 1; i <= cartitems; i++) {
					cartProductname = cmnpharmapage.getProductNamefromcart(i);
					cartPrice = cmnpharmapage.getProductPricefromcart(i);
					test.log(Status.PASS, " Added product to cart successfully:" + "<b>" + cartProductname + "</b>"
							+ "Price for the product is :" + "<b>" + cartPrice);
					CartProductDetails.put(cartProductname, cartPrice);
				}
				if (cmnpharmapage.verifycartitems(ProductDetails, CartProductDetails)) {
					test.log(Status.PASS, "Cart Items are matching ");

				} else {
					test.log(Status.FAIL, "Cart Items are not matching ");
				}
			} else {
				test.log(Status.FAIL, "Cart page is not loaded");
			}
			cmnpharmapage.clickoncheckoutbutton();
			test.log(Status.PASS, "Able to click Successfully on Checkout Button");
			// if User is not login
			

			// test.log(Status.PASS, "Able to click Successfully on Checkout Button");
			// Login functionality ends here
			cmnpharmapage.clickOnCashorCardonDeliveryRadioButton();
			test.log(Status.PASS, "Able to click Successfully on Cash / Card on Delivery Radio Button");
			String TotalValue = cmnpharmapage.verifyTotalValueOfProduct();
			String SecurePoints = cmnpharmapage.verifySecurePoints();
			if (TotalValue.equals("")) {

				test.log(Status.FAIL, "Unable to fetch the Price of the products");
			} else {

				test.log(Status.PASS,
						"Total Price of the products is :" + TotalValue + " Secure Points : " + "<b>" + SecurePoints);
			}
			LoadPropertiesFileUtils.loadPropertiesFile(DATA_PROPERTIES_FILE_NAME);
			String envValue = LoadPropertiesFileUtils.getenvironment();

			if (!(envValue.equalsIgnoreCase("PROD"))) {
				cmnpharmapage.clickOnPayorProceedButton();
				test.log(Status.PASS, "Able to click Successfully on Pay or Proceed Button");
				String OrderId = cmnpharmapage.verifyOrderId();
				if (OrderId.equals("")) {
					test.log(Status.FAIL, "Unable to Place an Order ");
				} else {
					String DeliveryDate = cmnpharmapage.getExpectedDeliverydate();
					test.log(Status.PASS, "Order Placed Successfully Order Number is :" + "<b>+" + OrderId
							+ " Expected Delivery date is :" + "<b>+" + DeliveryDate);
				}
			}

			homepage.ClickonMainLogo();
		}
		
	}
	//@Test( priority = 2)

	public void VerifyOrderPlacedwithTopDealsForYou() throws InterruptedException {
		test = rep.createTest("VerifyOrderPlacedwithCashorCardOnDelivery ");
		test.log(Status.INFO, "Verify Order Placed with Cash or Card on Delivery");
		String Productname, cartProductname, Price, cartPrice;
		Verifyloginregister();
		homepage.ClickonMainLogo();
		//homepage.ClickonPharmacy();
		HashMap<String, String> ProductDetails = new HashMap<String, String>();
		HashMap<String, String> CartProductDetails = new HashMap<String, String>();
		if (homepage.VerifyHomepageFields(Categories_TopDeals)) {
			test.log(Status.PASS, " In Home Page " + "<b>" + Categories_TopDeals + "</b>" + " is displayed");
			// if products are available to cart ,removing the existing products and adding
			// the new products from the cart
			// String Count=cmnpharmapage.cartitemscountvalidation();
			cmnpharmapage.ClickonCartIcon();
			if (cmnpharmapage.verifycartitemcount() == false) {
				cmnpharmapage.ClickonCartIcon();
				if (cmnpharmapage.RemoveCartItemsfromCart()) {
					test.log(Status.PASS, "Products removed from Cart Successfully");
				}

			}
			homepage.ClickonMainLogo();
			//homepage.ClickonPharmacy();
			homepage.ClickonViewAll(Categories_TopDeals);

			for (int i = 1; i <= NumberofProducts; i++) {
				Productname = cmnpharmapage.getProductName(i);
				Price = cmnpharmapage.getProductPrice(i).trim();
				cmnpharmapage.ClickonAddToCart(1);
				String count = cmnpharmapage.cartitemscountvalidation();
				int countvalue = Integer.parseInt(count);
				if (countvalue == i) {

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
				int cartitems = cmnpharmapage.getcartitemssize().size();
				for (int i = 1; i <= cartitems; i++) {
					cartProductname = cmnpharmapage.getProductNamefromcart(i);
					cartPrice = cmnpharmapage.getProductPricefromcart(i);
					test.log(Status.PASS, " Added product to cart successfully:" + "<b>" + cartProductname + "</b>"
							+ "Price for the product is :" + "<b>" + cartPrice);
					CartProductDetails.put(cartProductname, cartPrice);
				}
				if (cmnpharmapage.verifycartitems(ProductDetails, CartProductDetails)) {
					test.log(Status.PASS, "Cart Items are matching ");

				} else {
					test.log(Status.FAIL, "Cart Items are not matching ");
				}
			} else {
				test.log(Status.FAIL, "Cart page is not loaded");
			}
			cmnpharmapage.clickoncheckoutbutton();
			test.log(Status.PASS, "Able to click Successfully on Checkout Button");
			// if User is not login
			

			// test.log(Status.PASS, "Able to click Successfully on Checkout Button");
			// Login functionality ends here
			cmnpharmapage.clickOnCashorCardonDeliveryRadioButton();
			test.log(Status.PASS, "Able to click Successfully on Cash / Card on Delivery Radio Button");
			String TotalValue = cmnpharmapage.verifyTotalValueOfProduct();
			String SecurePoints = cmnpharmapage.verifySecurePoints();
			if (TotalValue.equals("")) {

				test.log(Status.FAIL, "Unable to fetch the Price of the products");
			} else {

				test.log(Status.PASS,
						"Total Price of the products is :" + TotalValue + " Secure Points : " + "<b>" + SecurePoints);
			}
			LoadPropertiesFileUtils.loadPropertiesFile(DATA_PROPERTIES_FILE_NAME);
			String envValue = LoadPropertiesFileUtils.getenvironment();

			if (!(envValue.equalsIgnoreCase("PROD"))) {
				cmnpharmapage.clickOnPayorProceedButton();
				test.log(Status.PASS, "Able to click Successfully on Pay or Proceed Button");
				String OrderId = cmnpharmapage.verifyOrderId();
				if (OrderId.equals("")) {
					test.log(Status.FAIL, "Unable to Place an Order ");
				} else {
					String DeliveryDate = cmnpharmapage.getExpectedDeliverydate();
					test.log(Status.PASS, "Order Placed Successfully Order Number is :" + "<b>+" + OrderId
							+ " Expected Delivery date is :" + "<b>+" + DeliveryDate);
				}
			}

			homepage.ClickonMainLogo();
		}
		
	}
	@Test( priority = 3)

	public void VerifyOrderPlacedwithPopularCategoriesInPharmacy() throws InterruptedException {
		test = rep.createTest("VerifyOrderPlacedwithCashorCardOnDelivery ");
		test.log(Status.INFO, "Verify Order Placed with Cash or Card on Delivery");
		String Productname, cartProductname, Price, cartPrice;
		//Verifyloginregister();
		homepage.ClickonMainLogo();
		//homepage.ClickonPharmacy();
		HashMap<String, String> ProductDetails = new HashMap<String, String>();
		HashMap<String, String> CartProductDetails = new HashMap<String, String>();
		if (homepage.VerifyHomepageFields(Pharmacy)) {
			test.log(Status.PASS, " In Home Page " + "<b>" + Pharmacy + "</b>" + " is displayed");
			// if products are available to cart ,removing the existing products and adding
			// the new products from the cart
			// String Count=cmnpharmapage.cartitemscountvalidation();
			cmnpharmapage.ClickonCartIcon();
			if (cmnpharmapage.verifycartitemcount() == false) {
				cmnpharmapage.ClickonCartIcon();
				if (cmnpharmapage.RemoveCartItemsfromCart()) {
					test.log(Status.PASS, "Products removed from Cart Successfully");
				}

			}
			homepage.ClickonMainLogo();
			//homepage.ClickonPharmacy();
			homepage.ClickonViewAll(Pharmacy);
			String categoryname = cmnpharmapage.clickOnPharmacyCategory();
			test.log(Status.PASS, "Clicked On category: "+"<b>"+categoryname+"</b>");
			
			for (int i = 1; i <= NumberofProducts; i++) {
				Productname = cmnpharmapage.getProductName(i);
				Price = cmnpharmapage.getProductPrice(i).trim();
				cmnpharmapage.ClickonAddToCart(1);
				String count = cmnpharmapage.cartitemscountvalidation();
				int countvalue = Integer.parseInt(count);
				if (countvalue == i) {

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
				int cartitems = cmnpharmapage.getcartitemssize().size();
				for (int i = 1; i <= cartitems; i++) {
					cartProductname = cmnpharmapage.getProductNamefromcart(i);
					cartPrice = cmnpharmapage.getProductPricefromcart(i);
					test.log(Status.PASS, " Added product to cart successfully:" + "<b>" + cartProductname + "</b>"
							+ "Price for the product is :" + "<b>" + cartPrice);
					CartProductDetails.put(cartProductname, cartPrice);
				}
				if (cmnpharmapage.verifycartitems(ProductDetails, CartProductDetails)) {
					test.log(Status.PASS, "Cart Items are matching ");

				} else {
					test.log(Status.FAIL, "Cart Items are not matching ");
				}
			} else {
				test.log(Status.FAIL, "Cart page is not loaded");
			}
			cmnpharmapage.clickoncheckoutbutton();
			test.log(Status.PASS, "Able to click Successfully on Checkout Button");
			// if User is not login
			

			// test.log(Status.PASS, "Able to click Successfully on Checkout Button");
			// Login functionality ends here
			cmnpharmapage.clickOnCashorCardonDeliveryRadioButton();
			test.log(Status.PASS, "Able to click Successfully on Cash / Card on Delivery Radio Button");
			String TotalValue = cmnpharmapage.verifyTotalValueOfProduct();
			String SecurePoints = cmnpharmapage.verifySecurePoints();
			if (TotalValue.equals("")) {

				test.log(Status.FAIL, "Unable to fetch the Price of the products");
			} else {

				test.log(Status.PASS,
						"Total Price of the products is :" + TotalValue + " Secure Points : " + "<b>" + SecurePoints);
			}
			LoadPropertiesFileUtils.loadPropertiesFile(DATA_PROPERTIES_FILE_NAME);
			String envValue = LoadPropertiesFileUtils.getenvironment();

			if (!(envValue.equalsIgnoreCase("PROD"))) {
				cmnpharmapage.clickOnPayorProceedButton();
				test.log(Status.PASS, "Able to click Successfully on Pay or Proceed Button");
				String OrderId = cmnpharmapage.verifyOrderId();
				if (OrderId.equals("")) {
					test.log(Status.FAIL, "Unable to Place an Order ");
				} else {
					String DeliveryDate = cmnpharmapage.getExpectedDeliverydate();
					test.log(Status.PASS, "Order Placed Successfully Order Number is :" + "<b>+" + OrderId
							+ " Expected Delivery date is :" + "<b>+" + DeliveryDate);
				}
			}

			homepage.ClickonMainLogo();
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
