package pageObjects.Aster;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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


}
