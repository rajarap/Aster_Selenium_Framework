package com.web.aster.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.web.aster.Base.BaseClass;
import com.web.aster.Interfaces.Page;
import com.web.aster.Utilities.TestUtils;


public class HomePage extends BaseClass implements Page{

	TestUtils utils = new TestUtils();

	@FindBy(xpath = "//div[@class='label cursor-pointer']") 
	private WebElement loginRegisterLink;

	public HomePage() {
		PageFactory.initElements(super.getDriver(), this);
	}
	
	public boolean clickLoginRegisterLink() {
		if(isUIElementDisplayed(loginRegisterLink)) {
			super.click(loginRegisterLink);
			utils.log().info("Clicked on \"Login / Register\" link on the Home Page");
			return true;
		}else {
			utils.log().info("\"ogin / Register\" link is not displayed on the Home Page");
			return false;
		}
	}
	
	public boolean isLoginRegisterLinkDisplayed() {
	if(super.isUIElementDisplayed(loginRegisterLink)) {
		utils.log().info(loginRegisterLink.getText() + " link is displayed on the Home Page");
		return true;
	}else {
		utils.log().info("\"Login / Register\" link is not displayed on the Home Page");
		return false;
	}
}

	@Override
	public boolean isAt() {
		if(loginRegisterLink.isDisplayed()) {
			utils.log().info("On Home Page");
			return true;
		} else {
			utils.log().info("Not on Home Page");
			return false;
		}
	}
}
