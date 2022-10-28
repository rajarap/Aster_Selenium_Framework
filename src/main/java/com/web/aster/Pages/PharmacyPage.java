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


public class PharmacyPage extends BaseClass implements Page{

	TestUtils utils = new TestUtils();
	ScrollAction sa = new ScrollAction();
	List<WebElement> medicines;
	
	@FindBy(xpath = "//button[@class='btn btn-outline-success btn-sm']") 
	private WebElement addToCartButton;
	
	@FindBy(xpath = "//div[@class='badge']") 
	private WebElement cartBadge;
	
	@FindBy(xpath = "//h2[normalize-space()='Shop By Brands']") 
	private WebElement shopByBrands;
	

	
	public PharmacyPage() {
		PageFactory.initElements(super.getDriver(), this);
	}
	
	public boolean clickAddToCartButton() throws InterruptedException {
		sa.scrollDownTwice();

		medicines = getDriver().findElements(By.xpath("//div[@class='slick-track']"));
		utils.log().info("No of items under Top Deals are : " + medicines.size());

		if (medicines.size() > 3) {
			for (int i = 0; i < 3; i++) {
				WebElement item = medicines.get(i);
				WebElement addToCartButton = item.findElement(By.xpath("//button[@class='btn btn-outline-success btn-sm']"));
				
				if(isUIElementDisplayed(addToCartButton)) {
					super.click(addToCartButton);
					Thread.sleep(5000);
				}
			}
		}
		return true;
	}
	
	public boolean clickCartBadge() {
		if(isUIElementDisplayed(cartBadge)) {
			super.click(cartBadge);
			utils.log().info("Clicked on cart badge");
			return true;
		}else {
			utils.log().info("Cart badge is not displayed");
			return false;
		}
	}

	@Override
	public boolean isAt() {
		if(getDriver().getTitle().contains("Aster online pharmacy is now myAster - order your medicines online  ")) {
			utils.log().info("On Aster online pharmacy is now myAster - order your medicines online Page");
			return true;
		} else {
			utils.log().info("Not on Aster online pharmacy is now myAster - order your medicines online Page");
			return false;
		}
	}
}
