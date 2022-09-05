package com.web.aster.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.web.aster.Base.BaseClass;
import com.web.aster.Interfaces.Page;
import com.web.aster.Utilities.TestUtils;



public class LoginPage extends BaseClass implements Page{

	TestUtils utils = new TestUtils();

	@FindBy(xpath = "//*[contains(@text,'Login / Register')]")
	public WebElement titleText;
	
	@FindBy(xpath = "//*[@id=\"kc-page-title\"]/div[2]/div/div/a/text()")
	public WebElement helpText;
	
	@FindBy(xpath = "//*[contains(@text,'Please enter your mobile number')]")
	public WebElement enterMobileNumberText;
	
	@FindBy(xpath = "//*[@id=\"countryCode\"]/div/span")
	public WebElement countryCodeDownArrow;
	
	@FindBy(xpath = "//*[@id=\"countryCode\"]/ul")
	public WebElement countryCodeListBox;
	
	@FindBy(id = "user.attributes.mobile_number")
	public WebElement mobileNumberTextBox;
	
	@FindBy(xpath = "//*[contains(@text,'Continue')]")
	public WebElement continueButton;
	
	@FindBy(xpath = "//*[contains(@text,'Login with Email')]")
	public WebElement loginWithEmailButton;
	

	public LoginPage() {
		PageFactory.initElements(super.getDriver(), this);
	}
	
	public boolean isContinueButtonDisplayed() {
		if(isUIElementDisplayed(continueButton)) {
			utils.log().info(continueButton.getText() + " button is displayed on the Login Page");
			return true;
		}else {
			utils.log().info("\"Continue\" button is not displayed on the Login Page");
			return false;
		}
	}
	
	public boolean isLoginWithEmailButtonDisplayed() {
		if(isUIElementDisplayed(loginWithEmailButton)) {
			utils.log().info(loginWithEmailButton.getText() + " button is displayed on the Login Page");
			return true;
		}else {
			utils.log().info("\"Login With Email\" button is not displayed on the Login Page");
			return false;
		}
	}
	
	public boolean isHelpTextDisplayed() {
		if(isUIElementDisplayed(helpText)) {
			utils.log().info(helpText.getText() + " text is displayed on the Login Page");
			return true;
		}else {
			utils.log().info("\"Help\" text is not displayed on the Login Page");
			return false;
		}
	}
	
	public boolean isCountryCodeListBoxDisplayed() {
		if(isUIElementDisplayed(countryCodeDownArrow)) {
			utils.log().info("Country Code List box is displayed on the Login Page");
			return true;
		}else {
			utils.log().info("Country Code List box is not displayed on the Login Page");
			return false;
		}
	}
	
	public boolean isMobileNumberTextBoxDisplayed() {
		if(isUIElementDisplayed(mobileNumberTextBox)) {
			utils.log().info("Mobile Number text box is displayed on the Login Page");
			return true;
		}else {
			utils.log().info("Mobile Number text box is not displayed on the Login Page");
			return false;
		}
	}
	
	public boolean clickContinue() {
		if(isUIElementDisplayed(continueButton)) {
			super.click(continueButton);
			utils.log().info("Clicked on \"Continue\" button on the Login Page");
			return true;
		}else {
			utils.log().info("\"Continue\" button is not displayed on the Login Page");
			return false;
		}
	}
	
	public boolean clickLoginWithEmail() {
		if(isUIElementDisplayed(loginWithEmailButton)) {
			super.click(loginWithEmailButton);
			utils.log().info("Clicked on \"Login With Email\" button on the Login Page");
			return true;
		}else {
			utils.log().info("\"Login With Email\" button is not displayed on the Login Page");
			return false;
		}
	}
	
	public boolean clickHelpText() {
		if(isUIElementDisplayed(helpText)) {
			super.click(helpText);
			utils.log().info("Clicked on \"Help\" text on the Login Page");
			return true;
		}else {
			utils.log().info("\"Help\" text is not displayed on the Login Page");
			return false;
		}
	}
	
	public boolean selectItemInList(String countryCode) {
		if(isUIElementDisplayed(countryCodeListBox)) {
			super.selectItemFromList(countryCodeListBox, countryCode);
			utils.log().info("Selected country code " + countryCode + " on the Login Page");
			return true;
		}else {
			utils.log().info("Country Code list box is not displayed on the Login Page");
			return false;
		}
	}
	
	public boolean enterMobileNumber(Integer mobileNumber) {
		if(isUIElementDisplayed(mobileNumberTextBox)) {
			super.typeData(mobileNumberTextBox, mobileNumber.toString());
			utils.log().info("Entered mobile number " + mobileNumber.toString() + " on the Login Page");
			return true;
		}else {
			utils.log().info("Mobile Number text box is not displayed on the Login Page");
			return false;
		}
	}
	
	public boolean isTitleMessageDisplayed() {
		if(isUIElementDisplayed(titleText)) {
			utils.log().info(titleText.getText() + " title is displayed");
			return true;
		}else {
			utils.log().info("Login / Register title is not displayed");
			return false;
		}
	}
	
	public boolean isLabelDisplayed() {
		if(isUIElementDisplayed(enterMobileNumberText)) {
			utils.log().info(enterMobileNumberText.getText() + " text is displayed");
			return true;
		}else {
			utils.log().info("Please enter your mobile number text is not displayed");
			return false;
		}
	}
	
	@Override
	public boolean isAt() {
		if(isUIElementDisplayed(titleText)) {
			utils.log().info("On Login / Register Page");
			return true;
		} else {
			utils.log().info("Not on Login / Register Page");
			return false;
		}
	}
	
}
