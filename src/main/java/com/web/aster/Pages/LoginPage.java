package com.web.aster.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.web.aster.Base.BaseClass;
import com.web.aster.Interfaces.Page;
import com.web.aster.Utilities.TestUtils;

public class LoginPage extends BaseClass implements Page{

	TestUtils utils = new TestUtils();
	

	@FindBy(xpath = "//span[normalize-space()='Welcome !']")
	private WebElement welcomeText;
	
	@FindBy(xpath = "//a[@class='help']")
	private WebElement helpText;
	
	@FindBy(xpath = "//strong[normalize-space()='Login']")
	private WebElement enterMobileNumberToLoginText;
	
	@FindBy(xpath = "//span[@class='dd-pointer dd-pointer-down']")
	private WebElement countryCodeDownArrow;
	
	@FindBy(xpath = "//*[@id=\"countryCode\"]/ul")
	private WebElement countryCodeListBox;
	
	@FindBy(xpath = "//input[@id='user.attributes.mobile_number']")
	private WebElement mobileNumberTextBox;
	
	@FindBy(xpath = "//div[@class='mdc-card__action-buttons']//button[@id='kc-login']")
	private WebElement continueButton;
	
	@FindBy(xpath = "//a[@id='kc-login']")
	private WebElement iWillDoItLaterLink;
	
	@FindBy(xpath = "//div[@class='list-group-item list-view-pf-stacked']//button[@id='kc-login']")
	private WebElement loginWithEmailButton;
	

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
	
	public boolean clickCountryCodeDownArrow() {
		if(isUIElementDisplayed(countryCodeDownArrow)) {
			super.click(countryCodeDownArrow);
			utils.log().info("Clicked on drop down box on the Login Page");
			return true;
		}else {
			utils.log().info("Drop down box is not displayed on the Login Page");
			return false;
		}
	}
	

	public boolean isWillDoItLaterLinkDisplayed() {
		if(isUIElementDisplayed(iWillDoItLaterLink)) {
			utils.log().info(iWillDoItLaterLink.getText() + " link is displayed on the Login Page");
			return true;
		}else {
			utils.log().info("\"I Will Do it Later\" link is not displayed on the Login Page");
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
	
	public boolean clickWillDoItLaterLinkDisplayed() {
		if(isUIElementDisplayed(iWillDoItLaterLink)) {
			super.click(iWillDoItLaterLink);
			utils.log().info("Clicked on \"I Will Do it Later\" link on the Login Page");
			return true;
		}else {
			utils.log().info("\"I Will Do it Later\" link is not displayed on the Login Page");
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
		click(countryCodeDownArrow);
		if(isUIElementDisplayed(countryCodeListBox)) {
			super.selectItemFromList(countryCodeListBox, countryCode);
			utils.log().info("Selected country code " + countryCode + " on the Login Page");
			return true;
		}else {
			utils.log().info("Country Code list box is not displayed on the Login Page");
			return false;
		}
	}
	
	public boolean enterMobileNumber(String mobileNumber) {
		if(isUIElementDisplayed(mobileNumberTextBox)) {
			super.typeData(mobileNumberTextBox, mobileNumber);
			utils.log().info("Entered mobile number " + mobileNumber + " on the Login Page");
			return true;
		}else {
			utils.log().info("Mobile Number text box is not displayed on the Login Page");
			return false;
		}
	}
	
	public boolean isTitleMessageDisplayed() {
		if(isUIElementDisplayed(welcomeText)) {
			utils.log().info(welcomeText.getText() + " title text is displayed");
			return true;
		}else {
			utils.log().info("Welcome title text is not displayed");
			return false;
		}
	}
	
	public boolean isLabelDisplayed() {
		if(isUIElementDisplayed(enterMobileNumberToLoginText)) {
			utils.log().info(enterMobileNumberToLoginText.getText() + " text is displayed");
			return true;
		}else {
			utils.log().info("To Login or Register please enter your mobile number text is not displayed");
			return false;
		}
	}
	
	@Override
	public boolean isAt() {
		if(isUIElementDisplayed(welcomeText)) {
			utils.log().info("On Login / Register Page");
			return true;
		} else {
			utils.log().info("Not on Login / Register Page");
			return false;
		}
	}
	
}
