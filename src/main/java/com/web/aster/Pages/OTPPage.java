package com.web.aster.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.web.aster.Base.BaseClass;
import com.web.aster.Interfaces.Page;
import com.web.aster.Utilities.TestUtils;


public class OTPPage extends BaseClass implements Page{

	TestUtils utils = new TestUtils();

	@FindBy(xpath = "//div[@class='kc-social-icon']//a//*[name()='svg']")
	public WebElement backArrow;
		
	@FindBy(xpath = "//a[@class='help']")
	public WebElement helpText;
	
	@FindBy(xpath = "//label[@for='sms_otp_user']")
	public WebElement sixDigitCodeText;
	
	@FindBy(xpath = "//input[@id='sms_otp_user1']")
	public WebElement otp1;
	
	@FindBy(xpath = "//input[@id='sms_otp_user2']")
	public WebElement otp2;
	
	@FindBy(xpath = "//input[@id='sms_otp_user3']")
	public WebElement otp3;
	
	@FindBy(xpath = "//input[@id='sms_otp_user4']")
	public WebElement otp4;
	
	@FindBy(xpath = "//input[@id='sms_otp_user5']")
	public WebElement otp5;
	
	@FindBy(xpath = "//input[@id='sms_otp_user6']")
	public WebElement otp6;
	
	@FindBy(xpath = "//div[@id='otp-time']")
	public WebElement otpTimer;
	
	@FindBy(xpath = "//button[@id='kc-otp']")
	public WebElement resendText;
	
	@FindBy(xpath = "//button[@id='kc-login']")
	public WebElement continueButton;
	

	public OTPPage() {
		PageFactory.initElements(super.getDriver(), this);
	}
	
	public boolean isContinueButtonDisplayed() {
		if(isUIElementDisplayed(continueButton)) {
			utils.log().info(continueButton.getText() + " button is displayed on the OTP Page");
			return true;
		}else {
			utils.log().info("\"Continue\" button is not displayed on the OTP Page");
			return false;
		}
	}
	
	public boolean isHelpTextDisplayed() {
		if(isUIElementDisplayed(helpText)) {
			utils.log().info(helpText.getText() + " text is displayed on the OTP Page");
			return true;
		}else {
			utils.log().info("\"Help\" text is not displayed on the OTP Page");
			return false;
		}
	}
	
	public boolean isOTPTimerDisplayed() {
		if(isUIElementDisplayed(otpTimer)) {
			utils.log().info("OTP Timer is displayed on the OTP Page");
			return true;
		}else {
			utils.log().info("OTP Timer is not displayed on the OTP Page");
			return false;
		}
	}
	
	public boolean isOTPTextBox1Displayed() {
		if(isUIElementDisplayed(otp1)) {
			utils.log().info("OTP text box1 is displayed on the OTP Page");
			return true;
		}else {
			utils.log().info("OTP text box1 is not displayed on the OTP Page");
			return false;
		}
	}
	
	public boolean isOTPTextBox2Displayed() {
		if(isUIElementDisplayed(otp1)) {
			utils.log().info("OTP text box2 is displayed on the OTP Page");
			return true;
		}else {
			utils.log().info("OTP text box2 is not displayed on the OTP Page");
			return false;
		}
	}
	
	public boolean isOTPTextBox3Displayed() {
		if(isUIElementDisplayed(otp3)) {
			utils.log().info("OTP text box3 is displayed on the OTP Page");
			return true;
		}else {
			utils.log().info("OTP text box3 is not displayed on the OTP Page");
			return false;
		}
	}
	
	public boolean isOTPTextBox4Displayed() {
		if(isUIElementDisplayed(otp4)) {
			utils.log().info("OTP text box4 is displayed on the OTP Page");
			return true;
		}else {
			utils.log().info("OTP text box4 is not displayed on the OTP Page");
			return false;
		}
	}
	
	public boolean isOTPTextBox5Displayed() {
		if(isUIElementDisplayed(otp5)) {
			utils.log().info("OTP text box5 is displayed on the OTP Page");
			return true;
		}else {
			utils.log().info("OTP text box5 is not displayed on the OTP Page");
			return false;
		}
	}
	
	public boolean isOTPTextBox6Displayed() {
		if(isUIElementDisplayed(otp6)) {
			utils.log().info("OTP text box6 is displayed on the OTP Page");
			return true;
		}else {
			utils.log().info("OTP text box6 is not displayed on the OTP Page");
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
	
	public boolean enterOTP(String value) { //123456
			String[] otp = value.split("");
			List<WebElement> otpObjects = new ArrayList<WebElement>();
			otpObjects.add(otp1);
			otpObjects.add(otp2);
			otpObjects.add(otp3);
			otpObjects.add(otp4);
			otpObjects.add(otp5);
			otpObjects.add(otp6);
			
			for(int i = 0; i < otpObjects.size(); i++) {
				otpObjects.get(i).sendKeys(otp[i]);
				utils.log().info("Entered OTP number " + otp[i].toString());
			}
			return true;
	}
	
	public boolean isLabelDisplayed() {
		if(isUIElementDisplayed(sixDigitCodeText)) {
			utils.log().info(sixDigitCodeText.getText() + " text is displayed");
			return true;
		}else {
			utils.log().info("\"We have sent 6-Digit code on\" text is not displayed");
			return false;
		}
	}
	
	public boolean isResendTextDisplayed() {
		if(isUIElementDisplayed(resendText)) {
			utils.log().info("\"Didn’t receive?\" Resend text is displayed");
			return true;
		}else {
			utils.log().info("\"Didn’t receive? Resend\" text is not displayed");
			return false;
		}
	}
	
	@Override
	public boolean isAt() {
		if(isUIElementDisplayed(sixDigitCodeText)) {
			utils.log().info("On Enter OTP Page");
			return true;
		} else {
			utils.log().info("Not on Enter OTP Page");
			return false;
		}
	}

}
