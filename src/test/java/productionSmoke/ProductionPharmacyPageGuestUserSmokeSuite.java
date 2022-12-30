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
import static constants.GeneralConstants.Pharmacycategory;
import static constants.GeneralConstants.clinicsnearbyyou;
import static constants.GeneralConstants.cartpage;
import static constants.UserConstants.NumberofProducts;
import static utils.EncryptionDecryptionUtils.decrypt;

public class ProductionPharmacyPageGuestUserSmokeSuite extends BaseTestUser {
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
	@Test(dataProvider = "PharmacyPage", dataProviderClass = AsterWebAutomation.class, priority = 1)
	// @Test(dataProvider = "getHomepageListValues")
	public void VerifyHomepagelist(String homepagelist) throws InterruptedException {
		test = rep.createTest("Verify Pharmacy Page " +homepagelist);
		test.log(Status.INFO, "Verfiy Pharmacy Page ");
		homepage.ClickonMainLogo();
		homepage.ClickonPharmacy();
		if (homepage.VerifyHomepageFields(homepagelist)) {
			test.log(Status.PASS, " In Home Page " + "<b>" + homepagelist + "</b>" + " is displayed");

		} else {
			test.log(Status.FAIL, " In Home Page " + "<b>" + homepagelist + "</b>" + " is not available");
		}

	}

	// Verify Popular Pharmacy Categories
	@Test(priority = 2)
	public void VerifyPopularCategories() throws InterruptedException {
		try {

			test = rep.createTest("VerifyPopularCategories " + Pharmacycategory);
			test.log(Status.INFO, "VerifyPopularCategories " + Pharmacycategory);
			homepage.ClickonMainLogo();
			homepage.ClickonPharmacy();
			if (homepage.VerifyHomepageFields(Pharmacycategory)) {

				homepage.ClickonViewAll(Pharmacycategory);
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
					homepage.ClickonPharmacy();
					homepage.ClickonViewAll(Pharmacycategory);
					test.log(Status.PASS, "Able to click Successfully on View All");
				}
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(Status.FAIL, "Element is not available");
		}

	}

	
	// Verify Add product to cart
	@Test(dataProvider = "PharmacyPageCategory", dataProviderClass = AsterWebAutomation.class, priority = 3)

	public void VerifyAddandRemoveProducttoCart(String category) throws InterruptedException {
		test = rep.createTest("VerifyAddandRemoveProducttoCart ");
		test.log(Status.INFO, "Verify Add and Remove Products to Cart ");
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
			
			
			
			homepage.ClickonMainLogo();
			test.log(Status.PASS, "Able to click Successfully on Main Logo");
			homepage.ClickonPharmacy();
			homepage.ClickonViewAll(Pharmacycategory);
			test.log(Status.PASS, "Able to click Successfully on View All");

		} else {
			test.log(Status.FAIL, " In Home Page " + category + " is not available");
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
