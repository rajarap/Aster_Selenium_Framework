package pageObjects.Aster;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.WaitUtils;
import utils.WebElementUtils;

public class LoginPage {
	 WebDriver driver;

	    @FindBy(xpath = "//button[@class='kc-social-btn']")
	    private WebElement loginwithEmailButton;
	    
	    @FindBy(xpath = "//label[text()='Email Address']")
	    private WebElement emailAddressLabel;
	    
	    @FindBy(xpath = "//input[@id='username']")
	    private WebElement emailAddressTextBox;
	    
	    @FindBy(xpath = "//*[contains(@class,'continue')]")
	    private WebElement continueButton;
	    
	    @FindBy(xpath = "//label[text()='Password']")
	    private WebElement passwordLabel;
	    
	    @FindBy(xpath = "//input[@id='password']")
	    private WebElement passwordTextBox;
	    
	    @FindBy(xpath = " //span[@class='user-name']")
	    private WebElement userName;
	  
	    @FindBy(xpath="//button[text()='Login/Register']")
	    private WebElement loginPharmacy;
	    

	    
	    @FindBy(xpath="//input[@class='mdc-text-field__input form-control input_box']")
	    private WebElement mobileNumberTextBox;
	    
	    @FindBy(xpath="//label[@class='dd-selected-text']")
	    private WebElement defaultSelectedCountryCode;
	    
	    @FindBy(xpath="//a[@class='dd-selected']")
	    private WebElement countryCodeListDropdown;
	    
	    @FindBy(xpath="//label[@class='dd-option-text'][text()='United Arab Emirates ']")
	    private WebElement selectCountryCode;
	    
	    @FindBy(xpath = "//*[contains(@id,'sms_otp_user1')]")
		public WebElement otp1;
		
		@FindBy(xpath = "//*[contains(@id,'sms_otp_user2')]")
		public WebElement otp2;
		
		@FindBy(xpath = "//*[contains(@id,'sms_otp_user3')]")
		public WebElement otp3;
		
		@FindBy(xpath = "//*[contains(@id,'sms_otp_user4')]")
		public WebElement otp4;
		
		@FindBy(xpath = "//*[contains(@id,'sms_otp_user5')]")
		public WebElement otp5;
		
		@FindBy(xpath = "//*[contains(@id,'sms_otp_user6')]")
		public WebElement otp6;
		
	    @FindBy(xpath="//input[contains(@name,'firstName')]")
	    private WebElement firstNameTextBox;
	    
	    @FindBy(xpath="//input[contains(@name,'lastName')]")
	    private WebElement lastNameTextBox;
	    
	    @FindBy(xpath="//label[@class='radio-custom-label'][text()='Female']")
	    private WebElement femaleRadioButton;
	    
	    @FindBy(xpath="//label[@class='radio-custom-label'][text()='Male']")
	    private WebElement maleRadioButton;
	    
	    @FindBy(xpath="//span[@id='select2-nationality-container']")
	    private WebElement nationalityDropdown;
	    
	    @FindBy(xpath="//input[@placeholder='Search']")
	    private WebElement searchNationality;
	    
	    @FindBy(xpath="//li[@class='select2-results__option select2-results__option--selectable select2-results__option--highlighted'][text()='Indian']")
	    private WebElement selectCountry;
	    
	    @FindBy(xpath="//button[contains(text(),' Proceed ')]")
	    private WebElement proceedButton;
	    
	    
	    public LoginPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        WaitUtils.waitForDocumentReadyState(driver);
	    }
	    
	    
	    public boolean ClickonLoginwithEmailButton() throws InterruptedException {
	    	
	    	Thread.sleep(6000);
	    	WaitUtils.waitForDocumentReadyState(driver);
	    	 WaitUtils.isElementDisplayed(loginwithEmailButton, driver);
	    	 WebElementUtils.mouseClick(loginwithEmailButton, driver);
			return true;
			
	    	
	    	 
	    }
   public boolean VerifyEmaiAddressLabel() throws InterruptedException {
	    	
	    	Thread.sleep(6000);
	    	WaitUtils.waitForDocumentReadyState(driver);
	    	 WaitUtils.isElementDisplayed(emailAddressLabel, driver);
	    	 WebElementUtils.mouseClick(emailAddressLabel, driver);
			return true;
			
	    	
	    	 
	    }
	    
         public boolean ClickOnEmailAddressTextBox(String emailID) throws InterruptedException {
	    	
	    	Thread.sleep(6000);
	    	WaitUtils.waitForDocumentReadyState(driver);
	    	 WaitUtils.isElementDisplayed(emailAddressTextBox, driver);
	    	emailAddressTextBox.sendKeys(emailID);
			return true;
			
	    	
	    	 
	    }

         
 	    public boolean ClickOnContinueButton_email() throws InterruptedException {
 	    	
 	    	Thread.sleep(6000);
 	    	WaitUtils.waitForDocumentReadyState(driver);
 	    	 WaitUtils.isElementDisplayed(continueButton, driver);
 	    	 WebElementUtils.mouseClick(continueButton, driver);
 			return true;
 			
 	    	
 	    	 
 	    }
 	    
 	   public boolean VerifyPasswordLabel() throws InterruptedException {
	    	
	    	Thread.sleep(6000);
	    	WaitUtils.waitForDocumentReadyState(driver);
	    	 WaitUtils.isElementDisplayed(passwordLabel, driver);
	    	 WebElementUtils.mouseClick(passwordLabel, driver);
			return true;
			
	    	
	    	 
	    }
 	   
 	  public boolean ClickOnPasswordTextBox(String password) throws InterruptedException {
	    	
	    	Thread.sleep(6000);
	    	WaitUtils.waitForDocumentReadyState(driver);
	    	 WaitUtils.isElementDisplayed(passwordTextBox, driver);
	    	 passwordTextBox.sendKeys(password);
			return true;
			
	    	
	    	 
	    }
 	  
 	 public boolean ClickOnContinueButton_password() throws InterruptedException {
	    	
	    	Thread.sleep(6000);
	    	WaitUtils.waitForDocumentReadyState(driver);
	    	 WaitUtils.isElementDisplayed(continueButton, driver);
	    	 WebElementUtils.mouseClick(continueButton, driver);
			return true;
			
	    	
	    	 
	    }
 	 
		public String getUserName() throws InterruptedException {
			String Val = "";
			try {
				Thread.sleep(6000);
				WaitUtils.waitForDocumentReadyState(driver);
				WaitUtils.isElementDisplayed(userName, driver);
				String user_name = userName.getText();
				Val = user_name;
				return Val;

			} catch (org.openqa.selenium.NoSuchElementException e) {
				return Val;
			}

		}
		
		public boolean VerifyLoginRegisteratPharmacy() throws InterruptedException {
			String Val = "";
			try {
				Thread.sleep(6000);
				WaitUtils.waitForDocumentReadyState(driver);
				loginPharmacy.isDisplayed();
				//WaitUtils.isElementDisplayed(loginPharmacy, driver);
				return true;

			} catch (org.openqa.selenium.NoSuchElementException e) {
				return false;
			}

		}
		 public void ClickOnLoginPharmacy() throws InterruptedException {
		    	
		    	Thread.sleep(6000);
		    	WaitUtils.waitForDocumentReadyState(driver);
		    	 WaitUtils.isElementDisplayed(loginPharmacy, driver);
		    	 WebElementUtils.mouseClick(loginPharmacy, driver);
			 
		    }

		 public String VerifyDefaultSelectedCountryCode() throws InterruptedException {
				String Val = "";
				try {
					Thread.sleep(6000);
					WaitUtils.waitForDocumentReadyState(driver);
					defaultSelectedCountryCode.isDisplayed();
				String default_SelectedCountryCode = defaultSelectedCountryCode.getText();
				   Val = default_SelectedCountryCode;
					return Val;

				} catch (org.openqa.selenium.NoSuchElementException e) {
					return Val;
				}
		 }

	
		  public void ClickOnCountryCodeListDropdown() throws InterruptedException {
		    	
		    	Thread.sleep(6000);
		    	WaitUtils.waitForDocumentReadyState(driver);
		        WaitUtils.isElementDisplayed(countryCodeListDropdown, driver);
		        WebElementUtils.mouseClick(countryCodeListDropdown, driver);
			 
		    }
		 
		 public void ClickOnCountryCode() throws InterruptedException {
		    	
		    	Thread.sleep(6000);
		    	WaitUtils.waitForDocumentReadyState(driver);
		    	WaitUtils.isElementDisplayed(selectCountryCode, driver);
		    	WebElementUtils.mouseClick(selectCountryCode, driver);
			 
		    }
		    
		 public boolean ClickOnMobileNumberTextBox(String mobileNumber) throws InterruptedException {
		    	
		    	Thread.sleep(6000);
		    	WaitUtils.waitForDocumentReadyState(driver);
		    	 WaitUtils.isElementDisplayed(mobileNumberTextBox, driver);
		    	 mobileNumberTextBox.sendKeys(mobileNumber);
				return true;
				
		    }
		 
		
		public String enterOTP() { //123456
			    String otpval = "123456";
					String[] otp = otpval.split("");
					List<WebElement> otpDigits = new ArrayList<WebElement>();
					otpDigits.add(otp1);
					otpDigits.add(otp2);
					otpDigits.add(otp3);
					otpDigits.add(otp4);
					otpDigits.add(otp5);
					otpDigits.add(otp6);
					
					for(int i = 0; i < otpDigits.size(); i++) {
					otpDigits.get(i).sendKeys(otp[i]);
						
					}
					return otpval;
					
			}
	
		 
		 public boolean ClickOnFirstNameTextBox(String firstName) throws InterruptedException {
		    	
		    	Thread.sleep(6000);
		    	WaitUtils.waitForDocumentReadyState(driver);
		    	 WaitUtils.isElementDisplayed(firstNameTextBox, driver);
		    	 firstNameTextBox.sendKeys(firstName);
				return true;
			}
		 
		 public boolean ClickOnLastNameTextBox(String lastName) throws InterruptedException {
		    	
		    	Thread.sleep(6000);
		    	WaitUtils.waitForDocumentReadyState(driver);
		    	 WaitUtils.isElementDisplayed(lastNameTextBox, driver);
		    	 lastNameTextBox.sendKeys(lastName);
				return true;
			}
		 
		 public boolean ClickOnFemaleRadioButton() throws InterruptedException {
		    	
		    	Thread.sleep(6000);
		    	WaitUtils.waitForDocumentReadyState(driver);
		    	 WaitUtils.isElementDisplayed(femaleRadioButton, driver);
		    	 WebElementUtils.mouseClick(femaleRadioButton, driver);
				return true;
			}
		 
		 public boolean ClickOnMaleRadioButton() throws InterruptedException {
		    	
		    	Thread.sleep(6000);
		    	WaitUtils.waitForDocumentReadyState(driver);
		    	 WaitUtils.isElementDisplayed(maleRadioButton, driver);
		    	 WebElementUtils.mouseClick(maleRadioButton, driver);
				return true;
			}
		 
		 public boolean ClickOnNationalityDropdown() throws InterruptedException {
		    	
		    	Thread.sleep(6000);
		    	WaitUtils.waitForDocumentReadyState(driver);
		    	Select oSelect = new Select(driver.findElement(By.xpath("//select[@class='select-box-nationality js-example-basic-single select2-hidden-accessible']")));
		         oSelect.selectByVisibleText("Indian");
				return true;
			}
		/* public boolean ClickOnNationalityDropdown() throws InterruptedException {
		    	
		    	Thread.sleep(6000);
		    	WaitUtils.waitForDocumentReadyState(driver);
		    	 WaitUtils.isElementDisplayed(nationalityDropdown, driver);
		    	 WebElementUtils.mouseClick(nationalityDropdown, driver);
				return true;
			}
		 
		 public boolean ClickOnNationalitySearBar() throws InterruptedException {
		    	
		    	Thread.sleep(6000);
		    	WaitUtils.waitForDocumentReadyState(driver);
		    	 WaitUtils.isElementDisplayed(searchNationality, driver);
		    	 searchNationality.sendKeys("Indian");
				return true;
				
		    }
		 
		 public boolean ClickOnCountryfromSearchBar() throws InterruptedException {
		    	
		    	Thread.sleep(6000);
		    	WaitUtils.waitForDocumentReadyState(driver);
		    	 WaitUtils.isElementDisplayed(selectCountry, driver);
		    	 WebElementUtils.mouseClick(selectCountry, driver);
				return true;
			}
			*/
		 
		 public boolean ClickOnDOBDDDropdown() throws InterruptedException {
		    	
		    	Thread.sleep(6000);
		    	WaitUtils.waitForDocumentReadyState(driver);
		    	WebElement ele = driver.findElement(By.xpath("//select[@class='day select2-hidden-accessible']"));
		    	 Select oSelect = new Select(ele);
		         oSelect.selectByIndex(15);
		         return true;
				
			}
		 
		 public boolean ClickOnDOBMMDropdown() throws InterruptedException {
		    	
		    	Thread.sleep(6000);
		    	WaitUtils.waitForDocumentReadyState(driver);
		    	 Select oSelect = new Select(driver.findElement(By.xpath("//select[@class='month select2-hidden-accessible']")));
		         oSelect.selectByVisibleText("February");
		         return true;
			
			}
		 
		 public boolean ClickOnDOBYYYYDropdown() throws InterruptedException {
		    	
		    	Thread.sleep(6000);
		    	WaitUtils.waitForDocumentReadyState(driver);
		    	 Select oSelect = new Select(driver.findElement(By.xpath("//select[@class='year select2-hidden-accessible']")));
		         oSelect.selectByValue("1996");
		         return true;
			
			}
		 
		 public boolean clickOnProceedButton() throws InterruptedException {
				try {
					Thread.sleep(6000);
					WaitUtils.waitForDocumentReadyState(driver);
					if (proceedButton.isDisplayed()) {
						WebElementUtils.mouseClick(proceedButton, driver);
					}
					return true;
				} catch (org.openqa.selenium.NoSuchElementException e) {
					return false;
				}
			}
		 
		 public boolean ClickOnContinueButton() throws InterruptedException {
	 	    	
	 	    	Thread.sleep(6000);
	 	    	WaitUtils.waitForDocumentReadyState(driver);
	 	    	 WaitUtils.isElementDisplayed(continueButton, driver);
	 	    	 WebElementUtils.mouseClick(continueButton, driver);
	 			return true;
	 			
	 	    	
	 	    	 
	 	    }
		
}
