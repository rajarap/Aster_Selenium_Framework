package com.web.aster.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.web.aster.Base.BaseClass;
import com.web.aster.Interfaces.Page;
import com.web.aster.Utilities.TestUtils;


public class OneAsterHomePage extends BaseClass implements Page{

	TestUtils utils = new TestUtils();
	List<WebElement> specialities;
	List<WebElement> clinics;
	List<WebElement> doctors;

	@FindBy(xpath = "//button[normalize-space()='Consult a Doctor']") 
	private WebElement consultDoctorButton;
	
	@FindBy(xpath = "//button[normalize-space()='Pharmacy']") 
	private WebElement pharmacyButton;
		
	@FindBy(xpath = "//input[contains(@class,'react-autosuggest__input')]") 
	private WebElement commonSearchBox;
	
	@FindBy(xpath = "//div[@class='icGlobalSearch']//div//img[@alt='Search Icon']") 
	private WebElement commonSearchButton;
	
	@FindBy(xpath = "//div[@id='react-autowhatever-1']") 
	private WebElement commonSearchListBox;

	@FindBy(xpath = "//*[@id=\"react-autowhatever-1\"]//div[@class='box p-0 borderBottomRadius search-box-global']/div/ul") 
	private WebElement consultDoctorListBox;

	@FindBy(xpath = "//*[@id=\"react-autowhatever-1\"]//div[@class='box p-0 borderBottomRadius search-box-global epharmacy']/div/ul") 
	private WebElement pharmacyListBox;
	

	public OneAsterHomePage() {
		PageFactory.initElements(super.getDriver(), this);
	}
	
	public boolean clickConsultDoctorButton() {
		if(isUIElementDisplayed(consultDoctorButton)) {
			super.click(consultDoctorButton);
			utils.log().info("Clicked on \"Consult Doctor\" button on the 1Aster Home Page");
			return true;
		}else {
			utils.log().info("\"Consult Doctor\" button is not displayed on the 1Aster Home Page");
			return false;
		}
	}
	
	public boolean clickPharmacyButton() {
		if(isUIElementDisplayed(pharmacyButton)) {
			super.click(pharmacyButton);
			utils.log().info("Clicked on \"Pharmacy\" button on the Home Page");
			return true;
		}else {
			utils.log().info("\"Pharmacy\" button is not displayed on the Home Page");
			return false;
		}
	}
	
	public void getConsultDoctorOptions(String searchText) {
		specialities = new ArrayList<>();
		clinics = new ArrayList<>();
		doctors = new ArrayList<>();
		
		List<WebElement> options = consultDoctorListBox.findElements(By.tagName("li"));
		utils.log().info("Total Number of items availabe under Consult a Doctor is : " + options.size());
		
		for (WebElement item : options) {
			if (item.getText().contains("Specialities")) {
				specialities.add(item);
			}else if (item.getText().contains("Clinic")) {
				clinics.add(item);
			}else if (item.getText().contains("Doctors")) {
				doctors.add(item);
			}else {
				utils.log().info("No search results were displayed");
			}
		}
	}
	
	public void selectItemFromList(WebElement countryCodeListBox, String countryCode) {
		List<WebElement> options = countryCodeListBox.findElements(By.tagName("li"));
		utils.log().info("Total Number of items availabe in dropdown box is : " + options.size() + " options");

		for (WebElement item : options) {
			if (item.getText().equals(countryCode)) {
				click(item);
				break;
			}
		}

	}
	
	@Override
	public boolean isAt() {
		if(consultDoctorButton.isDisplayed()) {
			utils.log().info("On One Aster Home Page");
			return true;
		} else {
			utils.log().info("Not on One Aster Home Page");
			return false;
		}
	}
}
