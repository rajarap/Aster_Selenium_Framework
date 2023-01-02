package productionSmoke;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import dataprovider.User.AsterWebAutomation;
import pageObjects.Aster.CommonHomeDoctor;
import pageObjects.Aster.CommonHomePharmacy;
import pageObjects.Aster.HomePage;
import pageObjects.Aster.LoginPage;

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
import static constants.UserConstants.UATemailID;
import static constants.UserConstants.UATPassword;
import static utils.EncryptionDecryptionUtils.decrypt;

public class ProductionHomePageLoginUserSmokeSuite extends BaseTestUser {
	private HomePage homepage;
	private CommonHomePharmacy cmnpharmapage;
	private CommonHomeDoctor cmndoctorpage;
	private LoginPage loginpage;
	
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
		loginpage = new LoginPage(driver);
	}

	@Test(priority=1)
	public void VerifyLoginWithEmail() throws InterruptedException {
		test = rep.createTest("VerifyLoginWithEmail");
		test.log(Status.INFO, "Verfiy Login with Email ");
	   
	    homepage.verifyLoginRegister();
		test.log(Status.PASS, "Able to click Successfully on Login /Register");
		
	    String  loginwithEmailButton = "Login with Email";
		if(loginpage.ClickonLoginwithEmailButton())
		{
			test.log(Status.PASS, " In Login Page " +"<b>"+ loginwithEmailButton +"</b>"+ " is displayed");
			test.log(Status.PASS, " In Login Page, Clicked on " +"<b>"+ loginwithEmailButton +"</b>"+ " button Successfully");

		} else {
			test.log(Status.FAIL, " In Login Page " +"<b>"+ loginwithEmailButton +"/b"+ " is not available");
		}
		String  emailAddressLabel = "Email Address";
		if(loginpage.VerifyEmaiAddressLabel())
		{
			test.log(Status.PASS, " In Login with Email Page " +"<b>"+ emailAddressLabel +"</b>"+ " label is displayed");

		} else {
			test.log(Status.FAIL, " In Login with Email Page " +"<b>"+ emailAddressLabel +"/b"+ " label is not available");
		}
		String  emailAddressTextBox = "name@companyname.com";
		if(loginpage.ClickOnEmailAddressTextBox(UATemailID))
		{
			test.log(Status.PASS, " In Login with Email Page " +"<b>"+ emailAddressTextBox +"</b>"+ " text is displayed");
			test.log(Status.PASS, " In Login with Email Page, Entered Email ID Successfully");

		} else {
			test.log(Status.FAIL, " In Login with Email Page  " +"<b>"+ emailAddressTextBox +"/b"+ " text is not available");
		}
		String  continueButton_1 = "Continue";
		if(loginpage.ClickOnContinueButton_1())
		{
			test.log(Status.PASS, " In Login with Email Page " +"<b>"+ continueButton_1 +"</b>"+ " is displayed");
			test.log(Status.PASS, " In Login with Email Page, Clicked on " +"<b>"+ continueButton_1 +"</b>"+ " button Successfully");

		} else {
			test.log(Status.FAIL, " In Login with Email Page  " +"<b>"+ continueButton_1 +"/b"+ " is not available");
		
		}
		
		String  passwordLabel = "Password";
		if(loginpage.VerifyPasswordLabel())
		{
			test.log(Status.PASS, " In Login with Email Page " +"<b>"+ passwordLabel +"</b>"+ " label is displayed");

		} else {
			test.log(Status.FAIL, " In Login with Email Page  " +"<b>"+ passwordLabel +"/b"+ " label is not available");
		
		}
		
		String  passwordTextBox = "Enter Password";
		if(loginpage.ClickOnPasswordTextBox(UATPassword))
		{
			test.log(Status.PASS, " In Login with Email Page " +"<b>"+ passwordTextBox +"</b>"+ " text is displayed");
			test.log(Status.PASS, " In Login with Email Page, Entered Password Successfully");

		} else {
			test.log(Status.FAIL, " In Login with Email Page  " +"<b>"+ passwordTextBox +"/b"+ " text is not available");
		
		}
		
		String  continueButton_2 = "Continue";
		if(loginpage.ClickOnContinueButton_2())
		{
			test.log(Status.PASS, " In Login with Email Page " +"<b>"+ continueButton_2 +"</b>"+ " is displayed");
			test.log(Status.PASS, " In Login with Email Page, Clicked on " +"<b>"+ continueButton_2 +"</b>"+ " button Successfully");
			test.log(Status.PASS, " Logged In Successfully ");

		} else {
			test.log(Status.FAIL, " In Login with Email Page  " +"<b>"+ continueButton_2 +"/b"+ " is not available");
		
		}
	}
	
	@Test(dataProvider = "PharmacyPageCategory", dataProviderClass = AsterWebAutomation.class, priority = 2)

	public void VerifyOrderPlacedwithCashorCardOnDelivery(String category) throws InterruptedException {
		test = rep.createTest("VerifyOrderPlacedwithCashorCardOnDelivery ");
		test.log(Status.INFO, "Verify Order Placed with Cash or Card on Delivery");
		String Productname,cartProductname,Price,cartPrice;
		homepage.ClickonMainLogo();
		homepage.ClickonPharmacy();
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
					
				}
				else
				{
					test.log(Status.FAIL, "Cart Items are not matching ");
				}
			} else {
				test.log(Status.FAIL, "Cart page is not loaded");
			}
			cmnpharmapage.clickoncheckoutbutton();
			test.log(Status.PASS, "Able to click Successfully on Checkout Button");
			
			cmnpharmapage.clickOnCashorCardonDeliveryRadioButton();
			test.log(Status.PASS, "Able to click Successfully on Cash / Card on Delivery Radio Button");
			
			
			String TotalValue = cmnpharmapage.verifyTotalValueOfProduct();
			test.log(Status.PASS, "Total Value of Product= " + "<b>" + TotalValue);
			
			String SecurePoints = cmnpharmapage.verifySecurePoints();
			test.log(Status.PASS, "Secure Points= " + "<b>" +  SecurePoints);
			
			cmnpharmapage.clickOnPayorProceedButton();
			test.log(Status.PASS, "Able to click Successfully on Pay or Proceed Button");
		
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
