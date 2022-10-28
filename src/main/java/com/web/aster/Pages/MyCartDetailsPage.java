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


public class MyCartDetailsPage extends BaseClass implements Page{

	TestUtils utils = new TestUtils();
	ScrollAction sa = new ScrollAction();
	
	@FindBy(xpath = "//button[@class='mb-4  btn btn-success']") 
	private WebElement checkoutButton;
	

	public MyCartDetailsPage() {
		PageFactory.initElements(super.getDriver(), this);
	}
	
	public boolean clickCheckoutButton() {
		if(isUIElementDisplayed(checkoutButton)) {
			super.click(checkoutButton);
			utils.log().info("Clicked on Checkout Button");
			return true;
		}else {
			utils.log().info("Checkout Button is not displayed");
			return false;
		}
	}

	@Override
	public boolean isAt() {
		if(getDriver().getTitle().contains("1Aster-Post Online Pharmacy My Cart")) {
			utils.log().info("On One Aster Post Online Pharmacy My Cart Page");
			return true;
		} else {
			utils.log().info("Not on One Aster Post Online Pharmacy My Cart Page");
			return false;
		}
	}
}
