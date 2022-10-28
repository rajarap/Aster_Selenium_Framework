package com.web.aster.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.web.aster.Base.BaseClass;
import com.web.aster.Interfaces.Page;
import com.web.aster.Utilities.ScrollAction;
import com.web.aster.Utilities.TestUtils;


public class ConsultADoctorPage extends BaseClass implements Page{

	TestUtils utils = new TestUtils();
	ScrollAction sa = new ScrollAction();
	WebElement speciality; 
	WebElement element; 
	
	@FindBy(xpath = "//div[@class='slick-slide slick-active slick-current']//div//img[@alt='Homepage - Aster TeleConsult']")
	private WebElement consultDoctorAds;
	
	@FindBy(xpath = "//input[@class='react-autosuggest__input react-autosuggest__input--focused']")
	private WebElement searchBox;

	@FindBy(xpath = "//h2[normalize-space()='Top Specialties']") 
	private WebElement topSpecialities;
	
	@FindBy(xpath = "//img[@title='General Medicine']") 
	private WebElement generalMedicine;
		
	@FindBy(xpath = "//img[@title='Pediatrics']") 
	private WebElement pediatrics;
	
	@FindBy(xpath = "//img[@title='Orthopedics']") 
	private WebElement orthopedics;
	
	@FindBy(xpath = "//img[@title='Internal medicine']") 
	private WebElement internalMedicine;
	
	@FindBy(xpath = "//img[@title='Obs & Gynae']") 
	private WebElement obsGynea;
	
	@FindBy(xpath = "//li[@class='breadcrumb-item active']") 
	private WebElement activeBreadcrumb;
	

	public ConsultADoctorPage() {
		PageFactory.initElements(super.getDriver(), this);
	}
	
	public boolean selectASpeciality(String specialities) {
			
//		switch(specialities) {
//		case "Orthopedics" :
//			utils.log().info("inside orthopedics switch case");
//			new ScrollAction().ScrollTillElementVisible(orthopedics, this.driver);
//			speciality = orthopedics;
//			break;
//		case "General Medicine" :
//			new ScrollAction().ScrollTillElementVisible(generalMedicine);
//			speciality = generalMedicine;
//			break;
//		case "Internal medicine" :
//			new ScrollAction().ScrollTillElementVisible(internalMedicine);
//			speciality = internalMedicine;
//			break;
//		case "Pediatrics" :
//			new ScrollAction().ScrollTillElementVisible(pediatrics);
//			speciality = pediatrics;
//			break;
//		case "Obs & Gynae" :
//			new ScrollAction().ScrollTillElementVisible(obsGynea);
//			speciality = obsGynea;
//			break;
//		default:
//			utils.log().info("No speciality selected");
//		}
		
		switch(specialities) {
		case "Orthopedics" :
			speciality = orthopedics;
			break;
		case "General Medicine" :
			speciality = generalMedicine;
			break;
		case "Internal medicine" :
			speciality = internalMedicine;
			break;
		case "Pediatrics" :
			speciality = pediatrics;
			break;
		case "Obs & Gynae" :
			speciality = obsGynea;
			break;
		default:
			utils.log().info("No speciality selected");
		}
		
		sa.scrollDown();
		
		if(isUIElementDisplayed(speciality)) {
			super.click(speciality);
			utils.log().info("Clicked on \" "+specialities +"\" speciality ");
			return true;
		}else {
			utils.log().info("\" " + specialities + "\" speciality is not displayed");
			return false;
		}
	}

	@Override
	public boolean isAt() {
//		if(activeBreadcrumb.getText().equals("Consult a Doctor")) {
		if(getDriver().getTitle().equals("myAster online doctor appointment booking and video consultation platform")) {
			utils.log().info("On myAster online doctor appointment booking and video consultation platform");
			return true;
		} else {
			utils.log().info("Not on myAster online doctor appointment booking and video consultation platform");
			return false;
		}
	}
}
