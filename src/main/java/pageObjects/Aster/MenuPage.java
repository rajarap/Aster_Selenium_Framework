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
	
	@FindBy(xpath = "//li[@id='mouseTarget6']//div[text()='Manage Address']")
	private WebElement Menu_ManageAddress;
	
	@FindBy(xpath = "//button[@class='addNewButton  btn btn-primary btn-md']")
	private WebElement addAddressButton;
	
	@FindBy(xpath = "//button[@class='lnkAddNew  btn btn-link']")
	private WebElement addNewButton;
	
	@FindBy(xpath="//input[contains(@name, 'searchLocation')]")
	private WebElement searchLocationTextBox;
	
	@FindBy(xpath="//button[@class='btn-close']")
	private WebElement closeOption;
	
	@FindBy(xpath="//div[@class='gm-style']")
	private WebElement map;
	
	@FindBy(xpath = "//button[text()='Confirm Location']")
	private WebElement confirmLocationButton;
	
	@FindBy(xpath = "//input[contains(@name, 'buildingName')]")
	private WebElement enterBuildingNameTextBox;
	
	@FindBy(xpath = "//input[contains(@name, 'flatNumber')]")
	private WebElement enterFlatorVillaNameTextBox;
	
	@FindBy(xpath = "//input[contains(@name, 'addressType')][@value='Home']")
	private WebElement radioButton_AddressTypeHome;
	
	@FindBy(xpath = "//input[contains(@name, 'primary')]")
	private WebElement checkBox_PrimaryAddress;
	
	@FindBy(xpath = "//button[@class='w-100 mx-auto py-3 btn btn-primary btn-sm']")
	private WebElement saveUpdateAddressDetailsButton;
	
	@FindBy(xpath = "//div[@class='address']")
	private WebElement address;
	
	@FindBy(xpath = "//button[@class='button  btn btn-primary 2345 btn-sm']")
	private WebElement editAddressIcon;
	
	@FindBy(xpath = "//div[@class='ButtonBaseStyled-sc-geagcp-0 gShOFA ms-3']")
	private WebElement removeAddressIcon;
	
	@FindBy(xpath = "//button[@class='false false order-2 btn btn-danger']")
	private WebElement removeButton;
	
	@FindBy(xpath = "//div[contains(text(), 'Appointments')]")
	private WebElement Menu_Appointments;
	
	@FindBy(xpath = "//div[@class='d-flex']")
	private WebElement upcomingAppointmentDoctorInfo;
	
	@FindBy(xpath = "//button[contains(text(), 'Reschedule')]")
	private WebElement rescheduleButton;
	
	@FindBy(xpath = "//button[contains(text(), 'Yes, Proceed')]")
	private WebElement yesProceedButton;
	
	@FindBy(xpath = "//button[contains(text(), 'Cancel')]")
	private WebElement cancelButton;
	
	@FindBy(xpath = "//input[@class='form-check-input'][@value='Booked appointment by mistake']")
	private WebElement reasonforCancellation_RadioButton;
	
	@FindBy(xpath = "//button[contains(text(), 'Cancel Appointment')]")
	private WebElement cancelAppointmentButton;
	
	@FindBy(xpath = "//div[@class='status cancelled ']")
	private WebElement cancelledStatus;
	
	@FindBy(xpath = "//div[@class='status confirmed ']")
	private WebElement confirmedStatus;
	
	@FindBy(xpath = "//li[@id='mouseTarget3']//div[text()='Lists']")
	private WebElement menuLists;
	
	@FindBy(xpath="//button[contains(text(), 'My Doctors')] ")
	private WebElement myDoctorsTab;
	
	@FindBy(xpath="//button[text()='Remove']")
	private WebElement removeButtonMyDoctors;
	
	@FindBy(xpath="//button[contains(text(), 'Shopping List')] ")
	private WebElement shoppingListTab;
	
	@FindBy(xpath="//button[@class='btnRemove  btn btn-link btn-sm']")
	private WebElement removeButtonFromShoppingList;
	
	@FindBy(xpath="//p[text()='Hey, there is nothing here']")
	private WebElement thereIsNothingHereText;
	
	@FindBy(xpath="//p[text()='No Doctors Found']")
	private WebElement noDoctorsFoundText;

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
	public String ClickonMenu() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			String val = Menu.getText();
			if (Menu.isDisplayed()) {
				WebElementUtils.mouseClick(Menu, driver);
			}

			return val;

		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
		return null;
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
	
	public String clickOnMenuManageAddresss() throws InterruptedException {
		String val = Menu_ManageAddress.getText();
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			if (Menu_ManageAddress.isDisplayed()) {
				WebElementUtils.mouseClick(Menu_ManageAddress, driver);
			}
			return val;
		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
		return null;
	}
	
	public boolean clickOnAddAddressButton() throws InterruptedException {
		
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			if (addAddressButton.isDisplayed()) {
				WebElementUtils.mouseClick(addAddressButton, driver);
			}
			
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
		return false;
	}
	
	public boolean clickOnAddNewButton() throws InterruptedException {
		
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			if (addNewButton.isDisplayed()) {
				WebElementUtils.mouseClick(addNewButton, driver);
			}
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
		return false;
	}
	
    public boolean clickOnSearchLocationTextBox() throws InterruptedException {
	    	
	    	Thread.sleep(6000);
	    	WaitUtils.waitForDocumentReadyState(driver);
	    	 WaitUtils.isElementDisplayed(searchLocationTextBox, driver);
	    	 searchLocationTextBox.sendKeys("Al Mankhool");
			return true;
	}
	 
		
      public boolean clickOnCloseOption() throws InterruptedException {
			
			try {
				Thread.sleep(6000);
				WaitUtils.waitForDocumentReadyState(driver);
				if (closeOption.isDisplayed()) {
					WebElementUtils.mouseClick(closeOption, driver);
				}
				return true;
			} catch (org.openqa.selenium.NoSuchElementException e) {

			}
			return false;
		}
      
      public boolean clickOnMap() throws InterruptedException {
			
			try {
				Thread.sleep(6000);
				WaitUtils.waitForDocumentReadyState(driver);
				if (map.isDisplayed()) {
					WebElementUtils.mouseClick(map, driver);
				}
				return true;
			} catch (org.openqa.selenium.NoSuchElementException e) {

			}
			return false;
		}
	
	public String clickOnConfirmLocationButton() throws InterruptedException {
		String val = confirmLocationButton.getText();
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			if (confirmLocationButton.isDisplayed()) {
				WebElementUtils.mouseClick(confirmLocationButton, driver);
			}
			return val;
		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
		return null;
	}
	
	  public boolean clickOnBuildingNameTextBox() throws InterruptedException {
	    	
	    	Thread.sleep(6000);
	    	WaitUtils.waitForDocumentReadyState(driver);
	    	 WaitUtils.isElementDisplayed(enterBuildingNameTextBox, driver);
	    	 enterBuildingNameTextBox.sendKeys("Moonlight");
			return true;
	}
	  
	  public boolean clickOnFlatOrVillaTextBox() throws InterruptedException {
	    	
	    	Thread.sleep(6000);
	    	WaitUtils.waitForDocumentReadyState(driver);
	    	 WaitUtils.isElementDisplayed(enterFlatorVillaNameTextBox, driver);
	    	 enterFlatorVillaNameTextBox.sendKeys("15B/9");
			return true;
	}
	  
	   public boolean clickOnHomeAddressTypeRadioButton() throws InterruptedException {
			
			try {
				Thread.sleep(6000);
				WaitUtils.waitForDocumentReadyState(driver);
				if (radioButton_AddressTypeHome.isDisplayed()) {
					WebElementUtils.mouseClick(radioButton_AddressTypeHome, driver);
				}
				return true;
			} catch (org.openqa.selenium.NoSuchElementException e) {

			}
			return false;
		}
	   
	   public boolean clickOnPrimaryAddressCheckBox() throws InterruptedException {
			
			try {
				Thread.sleep(6000);
				WaitUtils.waitForDocumentReadyState(driver);
				if (checkBox_PrimaryAddress.isDisplayed()) {
					WebElementUtils.mouseClick(checkBox_PrimaryAddress, driver);
				}
				return true;
			} catch (org.openqa.selenium.NoSuchElementException e) {

			}
			return false;
		}
	   
	   public String clickOnSaveUpdateAddressDetailsButton() throws InterruptedException {
			String val = saveUpdateAddressDetailsButton.getText();
			try {
				Thread.sleep(6000);
				WaitUtils.waitForDocumentReadyState(driver);
				if (saveUpdateAddressDetailsButton.isDisplayed()) {
					WebElementUtils.mouseClick(saveUpdateAddressDetailsButton, driver);
				}
				return val;
			} catch (org.openqa.selenium.NoSuchElementException e) {

			}
			return null;
		}
	   
	   public String verifyAddress() throws InterruptedException {
			String val = address.getText();
			try {
				Thread.sleep(6000);
				WaitUtils.waitForDocumentReadyState(driver);
				if (address.isDisplayed()) {
					WebElementUtils.mouseClick(address, driver);
				}
				return val;
			} catch (org.openqa.selenium.NoSuchElementException e) {

			}
			return null;
		}
	   
	   public boolean clickOnEditAddressIcon() throws InterruptedException {
			
			try {
				Thread.sleep(6000);
				WaitUtils.waitForDocumentReadyState(driver);
				if (editAddressIcon.isDisplayed()) {
					WebElementUtils.mouseClick(editAddressIcon, driver);
				}
				return true;
			} catch (org.openqa.selenium.NoSuchElementException e) {

			}
			return false;
		}
	   
	   public String clickOnRemoveAddressIcon() throws InterruptedException {
		
			try {
				Thread.sleep(6000);
				WaitUtils.waitForDocumentReadyState(driver);
				if (removeAddressIcon.isDisplayed()) {
					WebElementUtils.mouseClick(removeAddressIcon, driver);
				}
				
			} catch (org.openqa.selenium.NoSuchElementException e) {

			}
			return null;
		}
	   
	   public boolean clickOnRemoveButton() throws InterruptedException {
			
			try {
				Thread.sleep(6000);
				WaitUtils.waitForDocumentReadyState(driver);
				if (removeButton.isDisplayed()) {
					WebElementUtils.mouseClick(removeButton, driver);
				}
				return true;
			} catch (org.openqa.selenium.NoSuchElementException e) {

			}
			return false;
		}
	   
	   public String clickOnMenuAppointments() throws InterruptedException {
			String val = Menu_Appointments.getText();
			try {
				Thread.sleep(6000);
				WaitUtils.waitForDocumentReadyState(driver);
				if (Menu_Appointments.isDisplayed()) {
					WebElementUtils.mouseClick(Menu_Appointments, driver);
				}
				return val;
			} catch (org.openqa.selenium.NoSuchElementException e) {

			}
			return null;
		}
	   
	   public String clickOnUpcomingAppointmentDoctorInfo() throws InterruptedException {
			 String val = upcomingAppointmentDoctorInfo.getText();
			 try {
				Thread.sleep(6000);
				WaitUtils.waitForDocumentReadyState(driver);
				if (upcomingAppointmentDoctorInfo.isDisplayed()) {
					WebElementUtils.mouseClick(upcomingAppointmentDoctorInfo, driver);
				}
					return val;
				} catch (org.openqa.selenium.NoSuchElementException e) {

				}
				return null;
				}
		 

     public String clickOnRescheduleButton() throws InterruptedException {
	       String val = rescheduleButton.getText();
	       try {
		       Thread.sleep(6000);
		       WaitUtils.waitForDocumentReadyState(driver);
		       if (rescheduleButton.isDisplayed()) {
			       WebElementUtils.mouseClick(rescheduleButton, driver);
		       }
			       return val;
		       } catch (org.openqa.selenium.NoSuchElementException e) {

		       }
		       return null;
		       }
     
     public String clickOnYesProceedButton() throws InterruptedException {
	       String val = yesProceedButton.getText();
	       try {
		       Thread.sleep(6000);
		       WaitUtils.waitForDocumentReadyState(driver);
		       if (yesProceedButton.isDisplayed()) {
			       WebElementUtils.mouseClick(yesProceedButton, driver);
		       }
			       return val;
		       } catch (org.openqa.selenium.NoSuchElementException e) {

		       }
		       return null;
		       }
     

     public String clickOnCancelButton() throws InterruptedException {
	       String val = cancelButton.getText();
	       try {
		       Thread.sleep(6000);
		       WaitUtils.waitForDocumentReadyState(driver);
		       if (cancelButton.isDisplayed()) {
			       WebElementUtils.mouseClick(cancelButton, driver);
		       }
			       return val;
		       } catch (org.openqa.selenium.NoSuchElementException e) {

		       }
		       return null;
		       }
     
     public boolean clickOnReasonforCancellationRadioButton() throws InterruptedException {
			
			try {
				Thread.sleep(6000);
				WaitUtils.waitForDocumentReadyState(driver);
				if (reasonforCancellation_RadioButton.isDisplayed()) {
					WebElementUtils.mouseClick(reasonforCancellation_RadioButton, driver);
				}
				return true;
			} catch (org.openqa.selenium.NoSuchElementException e) {

			}
			return false;
		    }
     
     public String clickOnCancelAppointmentButton() throws InterruptedException {
	       String val = cancelAppointmentButton.getText();
	       try {
		       Thread.sleep(6000);
		       WaitUtils.waitForDocumentReadyState(driver);
		       if (cancelAppointmentButton.isDisplayed()) {
			       WebElementUtils.mouseClick(cancelAppointmentButton, driver);
		       }
			       return val;
		       } catch (org.openqa.selenium.NoSuchElementException e) {

		       }
		       return null;
		       }
     
 	   public String getCancelledStatus() throws InterruptedException {
		    String Val = "";
		    try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(cancelledStatus, driver);
			String cancelled_status = cancelledStatus.getText();
			Val = cancelled_status;
			return Val;

		    } catch (org.openqa.selenium.NoSuchElementException e) {
			  return Val;
		    }

	      }
 	  
 	   public String getConfirmedStatus() throws InterruptedException {
		    String Val = "";
		    try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(confirmedStatus, driver);
			String confirmed_Status = confirmedStatus.getText();
			Val = confirmed_Status;
			return Val;

		    } catch (org.openqa.selenium.NoSuchElementException e) {
			  return Val;
		    }

	      }
 	   
 	  public String clickOnMenuLists() throws InterruptedException {
 			try {
 				Thread.sleep(6000);
 				WaitUtils.waitForDocumentReadyState(driver);
 				if (menuLists.isDisplayed()) {
 					WebElementUtils.mouseClick(menuLists, driver);
 				}
 				String val = menuLists.getText();
 				return val;
 			} catch (org.openqa.selenium.NoSuchElementException e) {

 			}
 			return null;
 		}
 	  
 	 public String clickOnMyDoctorsTab() throws InterruptedException
    	{
    		try {
    			Thread.sleep(6000);
    			WaitUtils.waitForDocumentReadyState(driver);
    			if(myDoctorsTab.isDisplayed())
    			{
    				WebElementUtils.mouseClick(myDoctorsTab, driver);
    			}
    			String val = myDoctorsTab.getText();
 			return val;
    	    } catch (org.openqa.selenium.NoSuchElementException e) {
    	       
    	    }
 		return null;
    	}
 	 
 	 public boolean clickOnRemoveButtonMyDoctors() throws InterruptedException {
			
			try {
				Thread.sleep(6000);
				WaitUtils.waitForDocumentReadyState(driver);
				if (removeButtonMyDoctors.isDisplayed()) {
					WebElementUtils.mouseClick(removeButtonMyDoctors, driver);
				}
				return true;
			} catch (org.openqa.selenium.NoSuchElementException e) {

			}
			return false;
		}
 	 
 	public String clickOnShoppingListTab() throws InterruptedException
   	{
   		try {
   			Thread.sleep(6000);
   			WaitUtils.waitForDocumentReadyState(driver);
   			if(shoppingListTab.isDisplayed())
   			{
   				WebElementUtils.mouseClick(shoppingListTab, driver);
   			}
   			String val = shoppingListTab.getText();
			return val;
   	    } catch (org.openqa.selenium.NoSuchElementException e) {
   	       
   	    }
		return null;
   	}
 	
	public boolean clickOnRemoveButtonFromShoppingList() throws InterruptedException
   	{
   		try {
   			Thread.sleep(6000);
   			WaitUtils.waitForDocumentReadyState(driver);
   			if(removeButtonFromShoppingList.isDisplayed())
   			{
   				WebElementUtils.mouseClick(removeButtonFromShoppingList, driver);
   			}
   		
			return true;
   	    } catch (org.openqa.selenium.NoSuchElementException e) {
   	       
   	    }
		return false;
   	}
	
	public String VerifyThereIsNothingHereText() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
		
			WaitUtils.isElementDisplayed(thereIsNothingHereText, driver);
			String val = thereIsNothingHereText.getText();
			return val;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return null;

		}

	}
	
	public String VerifyNoDoctorsFoundText() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
		
			WaitUtils.isElementDisplayed(noDoctorsFoundText, driver);
			String val = noDoctorsFoundText.getText();
			return val;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return null;

		}

	}
     
     
   }


