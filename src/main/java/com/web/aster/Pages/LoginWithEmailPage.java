package com.web.aster.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.web.aster.Base.BaseClass;
import com.web.aster.Interfaces.Page;
import com.web.aster.Utilities.ScrollAction;
import com.web.aster.Utilities.TestUtils;


public class LoginWithEmailPage extends BaseClass implements Page{

	TestUtils utils = new TestUtils();
	ScrollAction sa = new ScrollAction();

	@FindBy(xpath = "//div[contains(text(),'Login with Email')]")
	private WebElement loginWithEmailTitleText;
	
	@FindBy(xpath = "//a[@class='help']")
	private WebElement helpText;
	
	@FindBy(xpath = "//label[@for='username']")
	private WebElement emailAddressLabel;
	
	@FindBy(xpath = "//input[@id='username']")
	private WebElement emailAddressTextBox;
	
	@FindBy(xpath = "//label[@for='password']")
	private WebElement passwordLabel;
	
	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwordTextBox;
	
	@FindBy(xpath = "//button[@id='kc-login']")
	private WebElement continueButton;
	
	@FindBy(xpath = "//span[@id='eye_icon_login_with_email']")
	private WebElement showPassword;
	
	@FindBy(xpath = "//a[@class='forgot-password-btn']")
	private WebElement forgotPassword;
	
	@FindBy(xpath = "//a[@class='achor-text']")
	private WebElement registerLink;
	

	public LoginWithEmailPage() {
		PageFactory.initElements(super.getDriver(), this);
	}
	
	public boolean isContinueButtonDisplayed() {
		if(isUIElementDisplayed(continueButton)) {
			utils.log().info(continueButton.getText() + " button is displayed on the Login With Email Page");
			return true;
		}else {
			utils.log().info("\"Continue\" button is not displayed on the Login With Email Page");
			return false;
		}
	}
	
	public boolean isEmailAddressLabelDisplayed() {
		if(isUIElementDisplayed(emailAddressLabel)) {
			utils.log().info(emailAddressLabel.getText() + " label is displayed on the Login With Email");
			return true;
		}else {
			utils.log().info("\"Email Address\" labl is not displayed on the Login With Email Page");
			return false;
		}
	}
	
	public boolean isEmailAddressTextBoxDisplayed() {
		if(isUIElementDisplayed(emailAddressTextBox)) {
			utils.log().info("Email Address Text box is displayed on the Login With Email Page");
			return true;
		}else {
			utils.log().info("Email Address text box is not displayed on the Login With Email Page");
			return false;
		}
	}
	
	public boolean isPasswordLabelDisplayed() {
		if(isUIElementDisplayed(passwordLabel)) {
			utils.log().info(passwordLabel.getText() + " label is displayed on the Login With Email");
			return true;
		}else {
			utils.log().info("\"Password\" labl is not displayed on the Login With Email Page");
			return false;
		}
	}
	
	public boolean isPasswordTextBoxDisplayed() {
		if(isUIElementDisplayed(passwordTextBox)) {
			utils.log().info("Password Text box is displayed on the Login With Email Page");
			return true;
		}else {
			utils.log().info("Password text box is not displayed on the Login With Email Page");
			return false;
		}
	}
	public boolean isForgotPasswordLinkDisplayed() {
		if(isUIElementDisplayed(forgotPassword)) {
			utils.log().info("Forgot Password link is displayed on the Login With Email Page");
			return true;
		}else {
			utils.log().info("Forgot Password link is not displayed on the Login With Email Page");
			return false;
		}
	}
	
	public boolean clickContinue() {
		if(isUIElementDisplayed(continueButton)) {
			super.click(continueButton);
			utils.log().info("Clicked on \"Continue\" button on the Login With Email Page");
			return true;
		}else {
			utils.log().info("\"Continue\" button is not displayed on the Login With Email Page");
			return false;
		}
	}
	
	public boolean enterEmailAddress(String emailAddress) {
		if(isUIElementDisplayed(emailAddressTextBox)) {
			super.typeData(emailAddressTextBox, emailAddress);
			utils.log().info("Entered email address " + emailAddress + " on the Login With Email Page");
			return true;
		}else {
			utils.log().info("Email Address text box is not displayed on the Login With Email Page");
			return false;
		}
	}
	
	public boolean enterPassword(String password) {
		if(isUIElementDisplayed(passwordTextBox)) {
			super.typeData(passwordTextBox, password);
			utils.log().info("Entered password on the Login With Email Page");
			return true;
		}else {
			utils.log().info("Password text box is not displayed on the Login With Email Page");
			return false;
		}
	}
	
	public boolean isHelpTextDisplayed() {
		if(isUIElementDisplayed(helpText)) {
			utils.log().info(helpText.getText() + " text is displayed on the Login With Email Page");
			return true;
		}else {
			utils.log().info("\"Help\" text is not displayed on the Login With Email Page");
			return false;
		}
	}
	

	public boolean isRegisterLinkDisplayed() {
		if(isUIElementDisplayed(registerLink)) {
			utils.log().info(registerLink.getText() + " link is displayed on the Login With Email Page");
			return true;
		}else {
			utils.log().info("\"Register\" link is not displayed on the Login With Email Page");
			return false;
		}
	}
	
	public boolean clickHelpText() {
		if(isUIElementDisplayed(helpText)) {
			super.click(helpText);
			utils.log().info("Clicked on \"Help\" text on the Login With Email Page");
			return true;
		}else {
			utils.log().info("\"Help\" text is not displayed on the Login With Email Page");
			return false;
		}
	}

	@Override
	public boolean isAt() {
		if(loginWithEmailTitleText.isDisplayed()) {
			utils.log().info("On One Aster Login With Email Page");
			return true;
		} else {
			utils.log().info("Not on One Aster Login With Email Page");
			return false;
		}
	}
}
