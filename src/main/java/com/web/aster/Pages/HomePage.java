package com.web.aster.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.web.aster.Base.BaseClass;
import com.web.aster.Interfaces.Page;
import com.web.aster.Utilities.TestUtils;


public class HomePage extends BaseClass implements Page{

	TestUtils utils = new TestUtils();

	@FindBy(xpath = "//div[@className='label cursor-pointer']") 
	public WebElement loginRegisterLink;


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
	
//	public boolean clickOnlyThisTime() {
//		if(isUIElementDisplayed(onlyThisTime)) {
//			super.click(onlyThisTime);
//			utils.log().info("Clicked on \"Only This time\" option on the Application Launch Page");
//			return true;
//		}else {
//			utils.log().info("\"Only This time\" option is not displayed on the Application Launch Page");
//			return false;
//		}
//	}
//	
//	public boolean clickDontAllow() {
//		if(isUIElementDisplayed(dontAllow)) {
//			super.click(dontAllow);
//			utils.log().info("Clicked on \"Don't Allow\" option on the Application Launch Page");
//			return true;
//		}else {
//			utils.log().info("\"Don't Allow\" option is not displayed on the Application Launch Page");
//			return false;
//		}
//	}
//	
//	public boolean isWhileUsingThisAppButtonDisplayed() {
//		if(super.isUIElementDisplayed(whileUsingThisApp)) {
//			utils.log().info(whileUsingThisApp.getText() + " button is displayed on the Application Launch Page");
//			return true;
//		}else {
//			utils.log().info("\"While Using This App\" button is not displayed on the Application Launch Page");
//			return false;
//		}
//	}
//	
//	public boolean isOnlyThisTimepButtonDisplayed() {
//		if(super.isUIElementDisplayed(onlyThisTime)) {
//			utils.log().info(onlyThisTime.getText() + " button is displayed on the Application Launch Page");
//			return true;
//		}else {
//			utils.log().info("\"Only This Time\" button is not displayed on the Application Launch Page");
//			return false;
//		}
//	}
//	
//	public boolean isDontAllowButtonDisplayed() {
//		if(super.isUIElementDisplayed(dontAllow)) {
//			utils.log().info(dontAllow.getText() + " button is displayed on the Application Launch Page");
//			return true;
//		}else {
//			utils.log().info("\"Don't Allow\" button is not displayed on the Application Launch Page");
//			return false;
//		}
//	}
//	
//	public boolean isLabelDisplayed() {
//		if(super.isUIElementDisplayed(permissionText)) {
//			utils.log().info(permissionText.getText() + "label is displayed on the Application Launch Page");
//			return true;
//		}else {
//			utils.log().info("Access to Device Location Message is not displayed on the Application Launch Page");
//			return false;
//		}
//	}
//	
//	public boolean isImageDisplayed() {
//		if(super.isUIElementDisplayed(permissionIcon)) {
//			utils.log().info("Location image is displayed on the Application Launch Page");
//			return true;
//		}else {
//			utils.log().info("Location image is not displayed on the Application Launch Page");
//			return false;
//		}
//	}

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
