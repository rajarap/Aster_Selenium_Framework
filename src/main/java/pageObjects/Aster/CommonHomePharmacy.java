package pageObjects.Aster;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.WaitUtils;
import utils.WebElementUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Login Page Object class representing the log in page
 *
 * @author yavort
 */
public class CommonHomePharmacy {
	public ExtentReports rep;
	public ExtentTest test;
	WebDriver driver;
	private HomePage homepage;
	@FindBy(xpath = "//h2[@class='title']")
	private WebElement title;
	@FindBy(xpath = "//div[@class='subTitle']")
	private WebElement productscount;

	@FindBy(xpath = "//h2[@class='title ']")
	private WebElement popularCategories;

	@FindBy(xpath = "//div[@class='ic'][@href='/online-pharmacy/mycart']")
	private WebElement mycart;

	@FindBy(xpath = "//li[@class='breadcrumb-item active']")
	private WebElement breadcrumb;

	@FindBy(xpath = "//div[@class='cartListItemBox']//span")
	private WebElement cartproductscount;
	
	@FindBy(xpath="//button[text()='Select Item']")
	private WebElement selectitem;
	
	@FindBy(xpath="//input[@name='selectAllCartItems']/following::label[text()='Select All']")
	private WebElement SelectAll;
	
	@FindBy(xpath="//button[contains(@class,'btnRemove')]")
	private WebElement RemoveItem;
	
	@FindBy(xpath="//div[@class='modal-content']//button[text()='Remove']")
	private WebElement Removebutton;
	
	@FindBy(xpath="//div[@class='card_wrapper']/p[@class='title']")
	private WebElement CartEmptymessage;
	
	@FindBy(xpath="//div[contains(@class,'Toastify')]/div[text()='Item added in cart']")
	private WebElement successmessage;
	
	@FindBy(xpath="//div[@class='cartBox']//div[@class='badge']")
	private WebElement cartitemscount;
	

	@FindBy(xpath="//button[text()='Checkout']")
	private WebElement checkout;
	
	@FindBy(xpath="//div[contains(@class,'toBePaid')]//div[contains(@id,'to-be-paid')]")
	private WebElement totalpriceincart;
	
	//div[contains(@class,'final-price')]

	@FindBy(xpath="//div[@class='final-price']")
	private WebElement totalpriceincheckout;
	
	@FindBy(xpath="//div[@class='backArrow']")
	private WebElement backarrow;
	
	@FindBy(xpath="//button[text()='Select Item']")
	private WebElement SelectItem;
	
	@FindBy(xpath="//input[@name='selectAllCartItems']")
	private WebElement SelectallcartItems;
	
	@FindBy(xpath="//button[text()='Remove']")
	private WebElement Removecartitems;
	
	@FindBy(xpath="//div[@class='modal-content']//button[text()='Remove']")
	private WebElement RemoveAll;
	
	@FindBy(xpath="//p[text()='Your Cart is Empty']")
	private WebElement cartempty;
	
	
	
	
	public CommonHomePharmacy(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WaitUtils.waitForDocumentReadyState(driver);
	}

	/**
	 * This method is used to facilitate the login process (it ends before Stay
	 * Signed In confirmation)
	 *
	 * @param email    The username email
	 * @param password The password
	 * @return
	 * @throws InterruptedException
	 */
	public boolean VerifyPharmacyProductslistpagetitle(String fieldValue) throws InterruptedException {
		boolean val = false;
		Thread.sleep(6000);
		WebElement ele = driver.findElement(By.xpath("//li[@class='breadcrumb-item active']"));

		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(ele, driver);
		if (ele.getText().equalsIgnoreCase(fieldValue)) {
			val = true;
		}
		return val;
	}

	public String ClickPharmacyCategorylistpage(int i) throws InterruptedException {
		Thread.sleep(6000);
		WebElement ele = driver.findElement(By.xpath("(//div[@class='brandtitle'])[" + i + "]"));
		String Category = ele.getText();
		WaitUtils.isElementDisplayed(ele, driver);
		// test.log(Status.PASS, "Category " + Category + " is displayed");
		WebElement Categorylistvalue = driver
				.findElement(By.xpath("//div[@class='brandtitle'][text()='" + Category + "']"));
		Categorylistvalue.click();
		return Category;

	}

	public String returnproductcount() {
		// String value = "";
		String productcounttext = productscount.getText();
		return productcounttext;

	}

	public boolean VerifyProductscount() {
		String productcounttext = productscount.getText();
		String[] arrOfStr = productcounttext.split(" ");
		if (arrOfStr[1].equals("0")) {
			return false;
		} else {
			return true;
		}
	}

	public List<WebElement> getPopularPharmacyCategories() throws InterruptedException {
		Thread.sleep(3000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(popularCategories, driver);
		List<WebElement> categorieslist = driver.findElements(By.xpath("//div[@class='brandbox']"));
		return categorieslist;

	}
	
	public  List<WebElement> getcartitemssize() throws InterruptedException {
		Thread.sleep(3000);
		WaitUtils.waitForDocumentReadyState(driver);
		//WaitUtils.isElementDisplayed(popularCategories, driver);
		List<WebElement> cartitemslist = driver.findElements(By.xpath("//div[@class='cartContainer']/div[@class='cartItem']"));
		//int cartlist=cartitemslist.size();
		return cartitemslist;

	}

	public boolean ClickonAddToCart(int i) throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			WebElement ele = driver.findElement(By.xpath("(//button[text()='Add to Cart'])[" + i + "]"));
			WaitUtils.isElementDisplayed(ele, driver);
			if (ele.isEnabled())
				ele.click();
				//WebElementUtils.mouseClick(ele, driver);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;

		}

	}

	public String getProductName(int i) throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WebElement ele = driver.findElement(By.xpath("(//div[@class='item']//div[@class='name'])[" + i + "]"));
		WaitUtils.isElementDisplayed(ele, driver);
		String productname = ele.getAttribute("title");
		return productname;

	}

	public String getProductPrice(int i) throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WebElement ele = driver.findElement(By.xpath("(//div[@class='item']//div[@class='price'])[" + i + "]"));
		WaitUtils.isElementDisplayed(ele, driver);
		String productprice = ele.getText();
		String[] arrOfStr = productprice.split(" ");
		String Price = arrOfStr[1];
		if(Price.contains("AED"))
		{
			String[] price = Price.split("AED");
			return price[0];
		}
		else
		{
		
			return Price;
		}
		

	}
	public String getProductNamefromcart(int i) throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WebElement ele = driver.findElement(By.xpath("(//div[@class='cartContainer']/div[@class='cartItem']//div[contains(@class,'prodName')])[" + i + "]"));
		WaitUtils.isElementDisplayed(ele, driver);
		String productname = ele.getText();
		return productname;

	}

	public String getProductPricefromcart(int i) throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WebElement ele = driver.findElement(By.xpath("(//div[@class='cartContainer']/div[@class='cartItem']//div[@class='price'])[" + i + "]"));
		WaitUtils.isElementDisplayed(ele, driver);
		String productprice = ele.getText();
		String[] arrOfStr = productprice.split(" ");
		String Price = arrOfStr[1];
		return Price;

	}


	public void ClickonCartIcon() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);

		WaitUtils.isElementDisplayed(mycart, driver);
		WebElementUtils.mouseClick(mycart, driver);

	}

	public List<WebElement> getCartlistItems() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(mycart, driver);
		List<WebElement> cartlist = driver.findElements(By.xpath("//div[@class='cartContainer']"));

		return cartlist;
	}

	public boolean verifyactivepage(String pagename) throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(breadcrumb, driver);
		String Value = breadcrumb.getText();
		if (Value.equalsIgnoreCase(pagename)) {
			return true;
		} else {
			return false;
		}

	}

	public String Cartproductscount() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(cartproductscount, driver);
		String Value = cartproductscount.getText();
		return Value;

	}
	
	public void selectItem() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(selectitem, driver);
		WebElementUtils.mouseClick(selectitem, driver);

	}
	
	public void selectAllCheckbox() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(SelectAll, driver);
		WebElementUtils.mouseClick(SelectAll, driver);

	}
	public void clickonRemoveItem() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(RemoveItem, driver);
		WebElementUtils.mouseClick(RemoveItem, driver);

	}
	
	public void clickonRemovebutton() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(Removebutton, driver);
		WebElementUtils.mouseClick(Removebutton, driver);

	}
	public boolean getTextCartMessage(String message) throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(CartEmptymessage, driver);
		String Value = CartEmptymessage.getText();
		if(Value.equalsIgnoreCase(message))
		{
			return true;
		}else
		{
			return false;
		}

	}
	
	public boolean verifycartitems(HashMap<String, String> ProductDetails,HashMap<String, String> CartProductDetails) throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		//String key=ProductDetails.keySet();
		 try{
		        for (String k : CartProductDetails.keySet())
		        {
		            if (!ProductDetails.get(k).equals(CartProductDetails.get(k))) {
		                return false;
		            }
		        } 
		        for (String y : ProductDetails.keySet())
		        {
		            if (!CartProductDetails.containsKey(y)) {
		                return false;
		            }
		        } 
		    } catch (NullPointerException np) {
		        return false;
		    }
		    return true;
		

	}
	public boolean successmessage() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		//WaitUtils.isElementDisplayed(successmessage, driver);
		//String Value = successmessage.getText();
		if(successmessage.isDisplayed())
		{
			return true;
		}else
		{
			return false;
		}

	}

	public String cartitemscountvalidation() throws InterruptedException {
		String count;
		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		//WaitUtils.isElementDisplayed(successmessage, driver);
		//String Value = successmessage.getText();
		if(cartitemscount.isDisplayed())
		{
			Thread.sleep(6000);
			count =cartitemscount.getText();
		}else
		{
			count=null;
		}
		return count;

	}

	public void clickoncheckoutbutton() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(checkout, driver);
		WebElementUtils.mouseClick(checkout, driver);

	} 
	public boolean comparecartandcheckoutprice()throws InterruptedException
	{
		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(totalpriceincart, driver);
		String cartprice =totalpriceincart.getText();
		clickoncheckoutbutton();
		Thread.sleep(6000);
		WaitUtils.isElementDisplayed(totalpriceincheckout, driver);
		String checkoutprice =totalpriceincheckout.getText();
		if(cartprice.equals(checkoutprice))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void clickonbackarrow() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(backarrow, driver);
		WebElementUtils.mouseClick(backarrow, driver);

	} 
	public void clickonselectItem() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(SelectItem, driver);
		WebElementUtils.mouseClick(SelectItem, driver);

	} 
	public void clickonselectallcheck() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(SelectallcartItems, driver);
		WebElementUtils.mouseClick(SelectallcartItems, driver);

	} 
	public void clickonRemove() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(Removecartitems, driver);
		WebElementUtils.mouseClick(Removecartitems, driver);

	} 
	
	public void clickonRemovepopup() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(RemoveAll, driver);
		WebElementUtils.mouseClick(RemoveAll, driver);

	} 
	public boolean RemoveCartItemsfromCart() throws InterruptedException
	{
		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		clickonselectItem();
		clickonselectallcheck();
		clickonRemove();
		clickonRemovepopup();
		if(WaitUtils.isElementDisplayed(cartempty, driver))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
