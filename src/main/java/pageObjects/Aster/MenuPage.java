package pageObjects.Aster;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.WaitUtils;
import utils.WebElementUtils;

/**
 * Login Page Object class representing the log in page
 *
 * @author yavort
 */
public class MenuPage {
	WebDriver driver;

	@FindBy(xpath = "//div[@class='menuButton']")
	private WebElement Menu;

	@FindBy(xpath = "//div[@title='Insurance']")
	private WebElement Menu_Insurance;

	@FindBy(xpath = "//div[@class='insurance-card-container']")
	private WebElement Insurance_InsuranceCard;

	@FindBy(xpath = "//button[contains(@class,'btn btn-link btn-sm') and text()='Add New']")
	private WebElement Insurance_AddNew;

	@FindBy(xpath = "//button[contains(@class,'btn btn-primary') and text()='Add Insurance']")
	private WebElement Insurance_AddNewNocard;

	@FindBy(xpath = "//span[@title='Add New Insurance']")
	private WebElement Insurance_AddNewInsurance;

	@FindBy(xpath = "//select[contains(@class,'form-control')]")
	private WebElement Insurance_InsuranceProvider;

	@FindBy(xpath = "//input[@name='insuranceId']")
	private WebElement Insurance_InsuranceID;

	@FindBy(xpath = "//input[@name='expiryDate']")
	private WebElement Insurance_ExpiryDate;

	@FindBy(xpath = "//button[contains(@class,'next2-button')]")
	private WebElement Insurance_CalendarIcon;

	@FindBy(xpath = "//button[contains(@class,'month-view')]/abbr[text()='28']")
	private WebElement Insurance_CalendarDate;

	@FindBy(xpath = "//input[@type='checkbox' and @class='form-check-input']")
	private WebElement Insurance_Consentcheck;

	@FindBy(xpath = "//button[@type='submit' and text()='Add']")
	private WebElement Insurance_Add;

	@FindBy(xpath = "//button[@type='submit' and text()='Go to Insurance']")
	private WebElement Insurance_GotoInsurance;

	@FindBy(xpath = "//div[@class='insurance-card-container']//div[@class='insurance-name']")
	private WebElement Insurance_name;

	@FindBy(xpath = "//div[@class='insurance-card-container']//div[@class='insurance-number']")
	private WebElement Insurance_number;

	@FindBy(xpath = "//span[@class='expire-text']/following::span")
	private WebElement Insurance_expiringdate;
	
	@FindBy(xpath = "//span[@class='user-name']")
	private WebElement UserName;
	//Payments
	@FindBy(xpath = "//div[text()='Manage Payment']")
	private WebElement Menu_Payment;
	
	@FindBy(xpath = "//button[contains(@class,'btn btn-link btn-sm') and text()='Add New']")
	private WebElement Payment_AddNew;

	@FindBy(xpath = "//button[contains(@class,'btn btn-primary') and text()='Add New Card']")
	private WebElement Payment_AddNewNocard;
	
	@FindBy(xpath = "//span[@title='Manage Payment']")
	private WebElement Payment_PageTitle;
	
	@FindBy(xpath = "//input[@id='CARD_NUMBER']")
	private WebElement Payment_CardNumber;
	
	@FindBy(xpath = "//input[@id='EXPIRY_MONTH']")
	private WebElement Payment_ExpiryMonth;
	
	@FindBy(xpath = "//input[@id='EXPIRY_YEAR']")
	private WebElement Payment_ExpiryYear;
	
	@FindBy(xpath = "//input[@id='CVV']")
	private WebElement Payment_CVV;
	
	@FindBy(xpath = "//input[@id='CARD_HOLDER_NAME']")
	private WebElement Payment_CardHolderName;
	
	@FindBy(xpath = "//button[contains(@class,'btn-primary')]/span")
	private WebElement Payment_PayNow;
	
	@FindBy(xpath = "//div[@class='payment-info-section']")
	private WebElement Payment_InfoVerification;
		
	@FindBy(xpath = "//div[@class='card-remove']")
	private WebElement Payment_RemoveICON;
	
	@FindBy(xpath = "//button[text()='Remove']")
	private WebElement Payment_RemoveICONpopup;

	public MenuPage(WebDriver driver) {
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
	public void ClickonMenu() throws InterruptedException {
		try {
			Thread.sleep(6000);

			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Menu, driver);
			WebElementUtils.mouseClick(Menu, driver);

		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
	}

	public void ClickonInsurance() throws InterruptedException {
		try {
			Thread.sleep(6000);

			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Menu_Insurance, driver);
			WebElementUtils.mouseClick(Menu_Insurance, driver);

		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
	}

	public void ClickonInsuranceAddnew() throws InterruptedException {
		try {
			Thread.sleep(6000);

			if (Insurance_InsuranceCard.isDisplayed()) {
				WaitUtils.waitForDocumentReadyState(driver);
				WaitUtils.isElementDisplayed(Insurance_AddNew, driver);
				WebElementUtils.mouseClick(Insurance_AddNew, driver);
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Insurance_AddNewNocard, driver);
			WebElementUtils.mouseClick(Insurance_AddNewNocard, driver);
		}

	}

	public void VerifyInsurancePagetitle() throws InterruptedException {
		try {
			Thread.sleep(6000);

			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Insurance_AddNewInsurance, driver);
			// WebElementUtils.mouseClick(Insurance_AddNewInsurance, driver);

		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
	}

	public void SelectInsuranceProvider(String providername) throws InterruptedException {
		try {
			Thread.sleep(6000);

			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Insurance_InsuranceProvider, driver);
			WebElementUtils.mouseClick(Insurance_InsuranceProvider, driver);
			WebElementUtils.selectByValue(Insurance_InsuranceProvider, providername);

		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
	}

	public void EnterInsuranceID(String InsuranceID) throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Insurance_InsuranceID, driver);
			Insurance_InsuranceID.sendKeys(InsuranceID);

		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
	}

	public void EnterexpiryDate() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Insurance_ExpiryDate, driver);
			WebElementUtils.mouseClick(Insurance_ExpiryDate, driver);
			WaitUtils.isElementDisplayed(Insurance_CalendarIcon, driver);
			WebElementUtils.mouseClick(Insurance_CalendarIcon, driver);
			WebElementUtils.mouseClick(Insurance_CalendarIcon, driver);
			WebElementUtils.mouseClick(Insurance_CalendarDate, driver);
		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
	}

	public void ConsentCheck() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Insurance_Consentcheck, driver);
			WebElementUtils.mouseClick(Insurance_Consentcheck, driver);

		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
	}

	public void clickonInsuranceAdd() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Insurance_Add, driver);
			WebElementUtils.mouseClick(Insurance_Add, driver);

		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
	}

	public String verifyInsuranceSuccessMessage() throws InterruptedException {

		try {
			String successmsg = "";
			String successmsg2 = "";
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);

			WebElement cardwrapper1 = driver.findElement(By.xpath("(//div[@class='card_wrapper']/p)[1]"));
			WebElement cardwrapper2 = driver.findElement(By.xpath("(//div[@class='card_wrapper']/p)[2]"));
			if (cardwrapper1.isDisplayed()) {
				successmsg = cardwrapper1.getText();
				successmsg2 = cardwrapper2.getText();
			}
			String val = successmsg + successmsg2;
		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
		return null;
	}

	public void GoToInsurance() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Insurance_GotoInsurance, driver);
			WebElementUtils.mouseClick(Insurance_GotoInsurance, driver);

		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
	}

	public String ValidateInsuranceDetails(String InsuranceID) throws InterruptedException {
		String val ="";
		try {
			
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			
			String InsuranceName = Insurance_name.getText();
			String Insurancenumber = Insurance_number.getText();
			String Insuranceexpiringdate = Insurance_expiringdate.getText();
			String value="Insurance Name :"+InsuranceName+" "+"Insurance Number :"+" "+Insurancenumber+" "+"Insurance Expirydate :"+Insuranceexpiringdate;
			val=value;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			List<WebElement> InsuranceCard = driver.findElements(By
					.xpath("insurance-card-container")); 
			for(int i=1;i<=InsuranceCard.size();i++)
			{
				WebElement Insurancename=driver.findElement(By.xpath("(//div[@class='insurance-card-container']//div[@class='insurance-name'])['"+i+"']"));
				WebElement Insurancenumber=driver.findElement(By.xpath("(//div[@class='insurance-card-container']//div[@class='insurance-number'])['"+i+"']"));
				WebElement Insuranceexpiringdate=driver.findElement(By.xpath("(//div[@class='insurance-card-container']//div[@class='Insurance_expiringdate'])['"+i+"']"));
				
				String InsuranceName = Insurancename.getText();
				String Insurancenum = Insurancenumber.getText();
				String Insuranceexpiredate = Insuranceexpiringdate.getText();
				if(Insurancenum.equals(InsuranceID))
				{
					break;
				}
				String value="Insurance Name :"+InsuranceName+" "+"Insurance Number :"+" "+Insurancenum+" "+"Insurance Expirydate :"+Insuranceexpiredate;
				
				val=value;
			}

		}
		return val;
	}
	
	public void ClickonUserName() throws InterruptedException {
		try {
			Thread.sleep(6000);

			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(UserName, driver);
			WebElementUtils.mouseClick(UserName, driver);
			
			

		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
	}
	
	public void ClickonManagePayment() throws InterruptedException {
		try {
			Thread.sleep(6000);

			WaitUtils.waitForDocumentReadyState(driver);
			
			WaitUtils.isElementDisplayed(Menu_Payment, driver);
			WebElementUtils.mouseClick(Menu_Payment, driver);

		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
	}
	

	public void ClickonPaymentAddnewCard() throws InterruptedException {
		try {
			Thread.sleep(6000);

			if (Insurance_InsuranceCard.isDisplayed()) {
				WaitUtils.waitForDocumentReadyState(driver);
				WaitUtils.isElementDisplayed(Payment_AddNew, driver);
				WebElementUtils.mouseClick(Payment_AddNew, driver);
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Payment_AddNewNocard, driver);
			WebElementUtils.mouseClick(Payment_AddNewNocard, driver);
		}

	}
	
	public void VerifyPaymentPagetitle() throws InterruptedException {
		try {
			Thread.sleep(6000);

			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Payment_PageTitle, driver);
			// WebElementUtils.mouseClick(Insurance_AddNewInsurance, driver);

		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
	}
	
	public void PaymentPage(String CardNumber,String expiryMonth,String expiryYear,String cvv,String NameonCard) throws InterruptedException
	{
		try {
			Thread.sleep(6000);
			Thread.sleep(6000);
	
			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Payment_CardNumber, driver);
			Payment_CardNumber.sendKeys(CardNumber);
			Thread.sleep(500);
			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Payment_ExpiryMonth, driver);
			Payment_ExpiryMonth.sendKeys(expiryMonth);
			Thread.sleep(500);
			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Payment_ExpiryYear, driver);
			Payment_ExpiryYear.sendKeys(expiryYear);
			
			Thread.sleep(500);
			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Payment_CVV, driver);
			Payment_CVV.sendKeys(cvv);
			Thread.sleep(500);
			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Payment_CardHolderName, driver);
			Payment_CardHolderName.sendKeys(NameonCard);
			Thread.sleep(500);
			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(Payment_PayNow, driver);
			WebElementUtils.mouseClick(Payment_PayNow, driver);
			
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

		}
	}
	
	public boolean VerifyCardAdded() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
		
			WaitUtils.isElementDisplayed(Payment_InfoVerification, driver);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;

		}

	}
	
	public boolean removeCard() throws InterruptedException
	{
		try
		{
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
		
			WaitUtils.isElementDisplayed(Payment_RemoveICON, driver);
			WebElementUtils.mouseClick(Payment_RemoveICON, driver);
			WaitUtils.isElementDisplayed(Payment_RemoveICONpopup, driver);
			WebElementUtils.mouseClick(Payment_RemoveICONpopup, driver);
			Thread.sleep(6000);
			Payment_InfoVerification.isDisplayed();
			
			return false;
			
		
		}catch (org.openqa.selenium.NoSuchElementException e) {
	
			return true;
		}
	}

}
