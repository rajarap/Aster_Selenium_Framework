package com.web.aster.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.web.aster.Base.BaseClass;
import com.web.aster.Interfaces.Page;
import com.web.aster.Utilities.ScrollAction;
import com.web.aster.Utilities.TestUtils;


public class AppointmentBookingStatusPage extends BaseClass implements Page{

	TestUtils utils = new TestUtils();
	ScrollAction sa = new ScrollAction();
	
	
	@FindBy(xpath = "//span[@title='Booking Status']") 
	private WebElement bookingStatusTitle;
	
	@FindBy(xpath = "//span[normalize-space()='Your appointment is confirmed!']") 
	private WebElement appointmentIsConfirmedMessage;

	@FindBy(xpath = "//*[@id=\"__next\"]/div[2]/div[3]/div[2]/div[2]/div/div/div[3]/div[1]/div[1]/div[2]") 
	private WebElement bookingConfirmedStatus;
	
	@FindBy(xpath = "//*[@id=\"__next\"]/div[2]/div[3]/div[2]/div[2]/div/div/div[3]/div[1]/div[1]/div[1]/div[2]") 
	private WebElement bookingNumber;
	
	@FindBy(xpath = "//*[@id=\"__next\"]/div[2]/div[3]/div[2]/div[2]/div/div/div[3]/div[2]/div[2]/div[2]") 
	private WebElement patientName;
		
	@FindBy(xpath = "//*[@id=\"__next\"]/div[2]/div[3]/div[2]/div[2]/div/div/div[3]/div[3]/div[1]/div[1]/div[2]/div[1]") 
	private WebElement doctorName;
	
	@FindBy(xpath = "//*[@id=\"__next\"]/div[2]/div[3]/div[2]/div[2]/div/div/div[3]/div[3]/div[1]/div[1]/div[2]/div[2]") 
	private WebElement doctorSpeciality;
	
	@FindBy(xpath = "//*[@id=\"__next\"]/div[2]/div[3]/div[2]/div[2]/div/div/div[3]/div[3]/div[1]/div[2]/div[2]/div[2]") 
	private WebElement consultationType;
	
	@FindBy(xpath = "//*[@id=\"__next\"]/div[2]/div[3]/div[2]/div[2]/div/div/div[3]/div[3]/div[2]/div[1]") 
	private WebElement clinicName;
	
	@FindBy(xpath = "//div[@class='logo']//a") 
	private WebElement myAsterLogo;
	
	
	public AppointmentBookingStatusPage() {
		PageFactory.initElements(super.getDriver(), this);
	}
	
	public boolean displayBookingStatus() {
		utils.log().info("Patient Name : " + patientName.getText());
		utils.log().info("Doctor Name : " + doctorName.getText());
		utils.log().info("Doctor Speciality : " + doctorSpeciality.getText());
		utils.log().info("Cosultation Type : " + consultationType.getText());
		utils.log().info("Clinic Name : " + clinicName.getText());
		return true;
	}
	
	public boolean clickMyAsterLogo() {
		if(isUIElementDisplayed(myAsterLogo)) {
			super.click(myAsterLogo);
			utils.log().info("Clicked on MyAster Logo");
			return true;
		}else {
			utils.log().info("MyAster Logo is not displayed");
			return false;
		}
	}

	
	@Override
	public boolean isAt() {
		if(bookingStatusTitle.isDisplayed()) {
			utils.log().info("On One Aster Booking status Page");
			return true;
		} else {
			utils.log().info("Not on One Aster Booking status Page");
			return false;
		}
	}
}
