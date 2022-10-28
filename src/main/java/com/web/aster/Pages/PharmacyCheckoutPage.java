package com.web.aster.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.web.aster.Base.BaseClass;
import com.web.aster.Interfaces.Page;
import com.web.aster.Utilities.ScrollAction;
import com.web.aster.Utilities.TestUtils;


public class PharmacyCheckoutPage extends BaseClass implements Page{

	TestUtils utils = new TestUtils();
	ScrollAction sa = new ScrollAction();
	
	@FindBy(xpath = "//button[@class='remove  btn btn-success']") 
	private WebElement proceedButton;
	
	@FindBy(xpath = "//span[@title='Delivery Preference']") 
	private WebElement deliveryPreference;
	
	@FindBy(xpath = "//input[@name='CanOptions'][1]") 
	private WebElement payViaCreditDebitCardRadioButton;
	
	@FindBy(xpath = "//input[@name='CanOptions'][2]") 
	private WebElement cashCardOnDeliveryRadioButton;
	

	public PharmacyCheckoutPage() {
		PageFactory.initElements(super.getDriver(), this);
	}
	
	public boolean clickProceedButton() {
		if(isUIElementDisplayed(proceedButton)) {
			super.click(proceedButton);
			utils.log().info("Clicked on Proceed Button");
			return true;
		}else {
			utils.log().info("Proceed Button is not displayed");
			return false;
		}
	}
	
	public boolean clickPayViaCreditDebitRadioButton() {
		if(isUIElementDisplayed(payViaCreditDebitCardRadioButton)) {
			super.click(payViaCreditDebitCardRadioButton);
			utils.log().info("Clicked on Pay Via Credit Debit Card Radio Button");
			return true;
		}else {
			utils.log().info("Pay Via Credit Debit Card Radio Button is not displayed");
			return false;
		}
	}
	
	public boolean clickCashCardOnDeliveryRadioButton() {
		if(isUIElementDisplayed(cashCardOnDeliveryRadioButton)) {
			super.click(cashCardOnDeliveryRadioButton);
			utils.log().info("Clicked on Cash Card On Delivery Radio Button");
			return true;
		}else {
			utils.log().info("Cash Card On Delivery Radio Button is not displayed");
			return false;
		}
	}

	@Override
	public boolean isAt() {
		if(deliveryPreference.isDisplayed()) {
			utils.log().info("On One Aster Online Pharmacy Checkout Page");
			return true;
		} else {
			utils.log().info("Not on One Aster Online Pharmacy Checkout Page");
			return false;
		}
	}
}
