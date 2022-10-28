package com.web.aster.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.web.aster.Base.BaseClass;
import com.web.aster.Interfaces.Page;
import com.web.aster.Utilities.TestUtils;


public class RegistrationPage extends BaseClass implements Page {

	TestUtils utils = new TestUtils();

	@FindBy(xpath = "//a[@onclick='showdiscardmodal()']//*[name()='svg']")
	public WebElement backArrow;

	@FindBy(xpath = "//div[@class='d-flex']//div[contains(text(),'Register')]")
	public WebElement titleText;

	@FindBy(xpath = "//a[@class='help']")
	public WebElement helpText;

	@FindBy(xpath = "//label[normalize-space()='Title']")
	public WebElement titleLabel;

	@FindBy(xpath = "//label[@for='mr']")
	public WebElement mrRadioButton;

	@FindBy(xpath = "//label[@for='mrs']")
	public WebElement mrsRadioButton;

	@FindBy(xpath = "//label[@for='miss']")
	public WebElement missRadioButton;

	@FindBy(xpath = "//label[normalize-space()='First Name']")
	public WebElement firstNameLabelText;

	@FindBy(xpath = "//input[@id='firstName']")
	public WebElement firstNameTextBox;

	@FindBy(xpath = "//label[normalize-space()='Last Name']")
	public WebElement lastNameLabelText;

	@FindBy(xpath = "//input[@id='last_name']")
	public WebElement lastNameTextBox;

	@FindBy(xpath = "//label[@for='email']")
	public WebElement emailAddressLabelText;

	@FindBy(xpath = "//input[@id='email']")
	public WebElement emailTextBox;

	@FindBy(xpath = "//label[normalize-space()='Nationality']")
	public WebElement nationalityLabelText;

	@FindBy(xpath = "//select[@id='nationality']")
	public WebElement nationalityListBox;

	@FindBy(xpath = "//label[normalize-space()='Date of Birth']")
	public WebElement dobLabelText;

	@FindBy(xpath = "//input[@id='dob']]")
	public WebElement dobTextBox;

	@FindBy(xpath = "//label[@for='password']")
	public WebElement passwordLabelText;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement passwordTextBox;

	@FindBy(xpath = "//span[@id='eye_icon']")
	public WebElement showPasswordIcon;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='Your Password must have:']")
	public WebElement passwordRulesText1;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='8 or more characters']")
	public WebElement passwordRulesText2;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='Upper & lowercase letters']")
	public WebElement passwordRulesText3;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='At least one digit']")
	public WebElement passwordRulesText4;
 
	@FindBy(xpath = "//android.widget.TextView[@resource-id='At least one special character']")
	public WebElement passwordRulesText5;

	@FindBy(xpath = "//label[@for='password-confirm']")
	public WebElement confirmPasswordLabelText;

	@FindBy(xpath = "//input[@id='password-confirm']")
	public WebElement confirmPasswordTextBox;

	@FindBy(xpath = "//span[@id='eye_icon_password_confirm']")
	public WebElement showConfirmPasswordIcon;

	@FindBy(xpath = "//input[@id='termsAndCondition']")
	public WebElement termsAndConditionCheckBox;

	@FindBy(xpath = "//button[@id='kc-login']")
	public WebElement registerButton;

	public RegistrationPage() {
		PageFactory.initElements(super.getDriver(), this);
	}

	public boolean clickRegister() {
		if (isUIElementDisplayed(registerButton)) {
			super.click(registerButton);
			utils.log().info("Clicked on \"Register\" button on the Registration Page");
			return true;
		} else {
			utils.log().info("\"Register\" button is not displayed on the Registration Page");
			return false;
		}
	}

	public boolean isRegisterButtonDisplayed() {
		if (isUIElementDisplayed(registerButton)) {
			utils.log().info(registerButton.getText() + " button is displayed");
			return true;
		} else {
			utils.log().info("Register button is not displayed");
			return false;
		}

	}

	public boolean isTitleDisplayed() {
		try {
			if (isUIElementDisplayed(titleText))
				utils.log().info(titleText.getText() + " text is displayed");
			return true;
		} catch (Exception e) {
			utils.log().info("Title text is not displayed");
			return true;
		}
	}

	public boolean isTitleLabelDisplayed() {
		try {
			if (isUIElementDisplayed(titleLabel))
				utils.log().info(titleLabel.getText() + " label is displayed");
			return true;
		} catch (Exception e) {
			utils.log().info("Register text is not displayed");
			return true;
		}
	}

	public boolean isHelpTextDisplayed() {
		try {
			if (isUIElementDisplayed(helpText))
				utils.log().info(helpText.getText() + " text is displayed");
			return true;
		} catch (Exception e) {
			utils.log().info("\"Help\" text is not displayed");
			return true;
		}
	}

	public boolean isMrRadioButtonDisplayed() {
		if (isUIElementDisplayed(mrRadioButton)) {
			utils.log().info(mrRadioButton.getText() + " radio button is displayed");
			return true;
		} else {
			utils.log().info("\"Mr.\" radio button is not displayed");
			return false;
		}
	}

	public boolean isMrsRadioButtonDisplayed() {
		if (isUIElementDisplayed(mrsRadioButton)) {
			utils.log().info(mrsRadioButton.getText() + " radio button is displayed");
			return true;
		} else {
			utils.log().info("\"Mrs.\" radio button is not displayed");
			return false;
		}
	}

	public boolean isMissRadioButtonDisplayed() {
		if (isUIElementDisplayed(missRadioButton)) {
			utils.log().info(missRadioButton.getText() + " radio button is displayed");
			return true;
		} else {
			utils.log().info("\"Miss.\" radio button is not displayed");
			return false;
		}
	}

	public boolean isFirstNameLabelDisplayed() {
		try {
			if (isUIElementDisplayed(firstNameLabelText))
				utils.log().info(firstNameLabelText.getText() + " label is displayed");
			return true;
		} catch (Exception e) {
			utils.log().info("First Name Label is not displayed");
			return true;
		}
	}

	public boolean isFirstNameTextBoxDisplayed() {
		if (isUIElementDisplayed(firstNameTextBox)) {
			utils.log().info("First Name text box is displayed");
			return true;
		} else {
			utils.log().info("First Name text box is not displayed");
			return false;
		}
	}

	public boolean isLastNameLabelDisplayed() {
		try {
			if (isUIElementDisplayed(lastNameLabelText))
				utils.log().info(lastNameLabelText.getText() + " label is displayed");
			return true;
		} catch (Exception e) {
			utils.log().info("Last Name Label is not displayed");
			return true;
		}
	}

	public boolean isLastNameTextBoxDisplayed() {
		if (isUIElementDisplayed(lastNameTextBox)) {
			utils.log().info("Last Name text box is displayed");
			return true;
		} else {
			utils.log().info("Last Name text box is not displayed");
			return false;
		}
	}


	public boolean isEmailAddressLabelDisplayed() {
		try {
			if (isUIElementDisplayed(emailAddressLabelText))
				utils.log().info(emailAddressLabelText.getText() + " label is displayed");
			return true;
		} catch (Exception e) {
			utils.log().info("Email Address Label is not displayed");
			return true;
		}
	}

	public boolean isEmailAddressTextBoxDisplayed() {
		if (isUIElementDisplayed(emailTextBox)) {
			utils.log().info("Email Address text box is displayed");
			return true;
		} else {
			utils.log().info("Email Address text box is not displayed");
			return false;
		}
	}

	public boolean isNationalityLabelDisplayed() {
		try {
			if (isUIElementDisplayed(nationalityLabelText))
				utils.log().info(nationalityLabelText.getText() + " label is displayed");
			return true;
		} catch (Exception e) {
			utils.log().info("Nationality Label is not displayed");
			return true;
		}
	}

	public boolean isNationalityListBoxDisplayed() {
		if (isUIElementDisplayed(nationalityListBox)) {
			utils.log().info("Nationality List box is displayed");
			return true;
		} else {
			utils.log().info("Nationality List box is not displayed");
			return false;
		}
	}

	public boolean isDOBLabelDisplayed() {
		try {
			if (isUIElementDisplayed(dobLabelText))
				utils.log().info(dobLabelText.getText() + " label is displayed");
			return true;
		} catch (Exception e) {
			utils.log().info("Date of Birth Label is not displayed");
			return true;
		}
	}

	public boolean isDOBTextBoxDisplayed() {
		if (isUIElementDisplayed(dobTextBox)) {
			utils.log().info("Date of Birth text box is displayed");
			return true;
		} else {
			utils.log().info("Date of Birth text box is not displayed");
			return false;
		}
	}

	public boolean isPasswordLabelDisplayed() {
		try {
			if (isUIElementDisplayed(passwordLabelText))
				utils.log().info("Password label is displayed");
			return true;
		} catch (Exception e) {
			utils.log().info("Password Label is not displayed");
			return true;
		}
	}

	public boolean isPasswordTextBoxDisplayed() {
		if (isUIElementDisplayed(passwordTextBox)) {
			utils.log().info("Password text box is displayed");
			return true;
		} else {
			utils.log().info("Password text box is not displayed");
			return false;
		}
	}

	public boolean isShowPasswordIconDisplayed() {
		try {
			if (isUIElementDisplayed(showPasswordIcon))
				utils.log().info("Show Password Icon is displayed");
			return true;
		} catch (Exception e) {
			utils.log().info("Show Password Icon is not displayed");
			return true;
		}
	}

	public boolean isConfirmPasswordLabelDisplayed() {
		try {
			if (isUIElementDisplayed(confirmPasswordLabelText))
				utils.log().info("Confirm Password label is displayed");
			return true;
		} catch (Exception e) {
			utils.log().info("Confirm Password Label is not displayed");
			return true;
		}
	}

	public boolean isConfirmPasswordTextBoxDisplayed() {
		if (isUIElementDisplayed(confirmPasswordTextBox)) {
			utils.log().info("Confirm Password text box is displayed");
			return true;
		} else {
			utils.log().info("Confirm Password text box is not displayed");
			return false;
		}
	}

	public boolean isShowConfirmPasswordIconDisplayed() {
		try {
			if (isUIElementDisplayed(showConfirmPasswordIcon))
				utils.log().info("Show Confrim Password Icon is displayed");
			return true;
		} catch (Exception e) {
			utils.log().info("Show Confrim Password Icon is not displayed");
			return true;
		}
	}

	public boolean selectNationalityFromList(String countryName) {
		if (isUIElementDisplayed(nationalityListBox)) {
			super.selectOptionFromList(nationalityListBox, countryName);
			utils.log().info("Selected nationality " + countryName + " on the Registration Page");
			return true;
		} else {
			utils.log().info("Nationality list box is not displayed on the Registration Page");
			return false;
		}
	}

	public boolean selectTitleRadioButton(String titleRadiobutton) {

		if (titleRadiobutton.equalsIgnoreCase("mr.")) {
			super.click(mrRadioButton);
			utils.log().info("Clicked on : " + titleRadiobutton + " radio button");
			return true;
		} else if (titleRadiobutton.equalsIgnoreCase("mrs.")) {
			super.click(mrsRadioButton);
			utils.log().info("Clicked on : " + titleRadiobutton + " radio button");
			return true;
		} else if (titleRadiobutton.equalsIgnoreCase("mrs.")) {
			super.click(missRadioButton);
			utils.log().info("Clicked on : " + titleRadiobutton + " radio button");
			return true;
		} else {
			utils.log().info("Invalied option provided for title Radio button");
			return false;
		}
	}

	public boolean enterFirstName(String fname) {
		if (isUIElementDisplayed(firstNameTextBox)) {
			super.typeData(firstNameTextBox, fname);
			utils.log().info("Entered first name : " + fname);
			return true;
		} else {
			utils.log().info("First Name text box is not displayed");
			return false;
		}
	}

	public boolean enterLasttName(String lname) {
		if (isUIElementDisplayed(lastNameTextBox)) {
			super.typeData(lastNameTextBox, lname);
			utils.log().info("Entered last name : " + lname);
			return true;
		} else {
			utils.log().info("Last Name text box is not displayed");
			return false;
		}
	}

	public boolean enterEmailAddress(String email) {
		if (isUIElementDisplayed(emailTextBox)) {
			super.typeData(emailTextBox, email);
			utils.log().info("Entered email address : " + email);
			return true;
		} else {
			utils.log().info("Email Address text box is not displayed");
			return false;
		}
	}

	public boolean enterPassword(String password) {
		if (isUIElementDisplayed(passwordTextBox)) {
			super.typeData(passwordTextBox, password);
			utils.log().info("Entered password : " + password);
			return true;
		} else {
			utils.log().info("Password text box is not displayed");
			return false;
		}
	}

	public boolean clickShowPassword() {
		try {
			if (isUIElementDisplayed(showPasswordIcon))
				super.click(showPasswordIcon);
			utils.log().info("Clicked on \"Show Password\" icon is displayed");
			return true;
		} catch (Exception e) {
			utils.log().info("\"Show Password\" icon is not displayed");
			return true;
		}
	}

	public boolean enterConfirmPassword(String confirmPassword) {
		if (isUIElementDisplayed(confirmPasswordTextBox)) {
			super.typeData(confirmPasswordTextBox, confirmPassword);
			utils.log().info("Entered confirm password : " + confirmPassword);
			return true;
		} else {
			utils.log().info("Confirm password text box is not displayed");
			return false;
		}
	}

	public boolean clickShowConfirmPassword() {
		try {
			if (isUIElementDisplayed(showConfirmPasswordIcon))
				super.click(showConfirmPasswordIcon);
			utils.log().info("Clicked on \"Show Confirm Password\" icon");
			return true;
		} catch (Exception e) {
			utils.log().info("\"Show Confirm Password\" icon is not displayed");
			return false;
		}

	}

	public boolean clickTCCheckBox() {
		if (isUIElementDisplayed(termsAndConditionCheckBox)) {
			super.click(termsAndConditionCheckBox);
			utils.log().info("Clicked on \"Terms And Conditions\" checkbox");
			return true;
		} else {
			utils.log().info("\"Terms And Conditions\" checkbox is not displayed");
			return false;
		}
	}

	@Override
	public boolean isAt() {
		if (isUIElementDisplayed(titleLabel)) {
			utils.log().info("On Registration Page");
			return true;
		} else {
			utils.log().info("Not on Registration Page");
			return false;
		}
	}

}
