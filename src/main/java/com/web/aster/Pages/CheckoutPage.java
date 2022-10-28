package com.web.aster.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.web.aster.Base.BaseClass;
import com.web.aster.Interfaces.Page;
import com.web.aster.Utilities.ScrollAction;
import com.web.aster.Utilities.TestUtils;


public class CheckoutPage extends BaseClass implements Page{

	TestUtils utils = new TestUtils();
	ScrollAction sa = new ScrollAction();
	
	@FindBy(xpath = "//li[@class='breadcrumb-item active']") 
	private WebElement activeBreadcrum;    //Checkout
	
	@FindBy(xpath = "//label[contains(@class,'mobile-form-label mb-0 form-label')]") 
	private WebElement titleText;    //Checkout
	
	@FindBy(xpath = "//label[normalize-space()='Appointment Summary']") 
	private WebElement subTitleText;    //AppointmentSummary
	
	@FindBy(xpath = "//div[@class='py-2 border-bottom distance label']") 
	private WebElement patientDetailsLabel;    //Patient Details Label
	
	@FindBy(xpath = "//div[@class='patient-name']") 
	private WebElement patientName;    
	
	@FindBy(xpath = "//div[@class='dr_name']") 
	private WebElement doctorName; 

	@FindBy(xpath = "//div[@class='dr_type']") 
	private WebElement doctorSpeciality; 
	
	@FindBy(xpath = "//div[normalize-space()='Consultation Type']") 
	private WebElement consultationTypeLabel; 
	
	@FindBy(xpath = "//div[normalize-space()='In-Person Consultation']") 
	private WebElement consultationType; 
	
	@FindBy(xpath = "//div[class='address-wrapper border-0 mobile-address-wrapper pe-0']/div/div") 
	private WebElement clinicName; 
	
	@FindBy(xpath = "//div[class='address-sub-wrapper']/div") 
	private WebElement clinicSpeciality; 
	
	@FindBy(xpath = "//div[class='address-sub-wrapper']/div/div") 
	private WebElement clinicDistance; 
	
	@FindBy(xpath = "//*[@id=\"__next\"]/div[2]/div[3]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div[3]/div/div[2]/div[3]/p/text()") 
	private WebElement clinicAddress; 
	
	@FindBy(xpath = "//label[normalize-space()='Payment Summary']") 
	private WebElement paymentSummaryLabel; 
	
	@FindBy(xpath = "//div[contains(@class,'title-wrapper no-border')]//div[contains(text(),'Consultation Fees')]") 
	private WebElement consultationFeesLabel; 
	
	@FindBy(xpath = "//div[normalize-space()='To be Paid']") 
	private WebElement toBePaidLabel; 
	
	@FindBy(xpath = "//div[class='fw-bold']/text()") 
	private WebElement feesAmount; 
	
	@FindBy(xpath = "//div[@class='py-3 list-group-item']//input[@id='inpersonType']") 
	private WebElement payAtClinicRadioButton;
	
	@FindBy(xpath = "//input[@id='inperson']") 
	private WebElement payByCreditDebitRadioButton;

	@FindBy(xpath = "//button[@type='submit']") 
	private WebElement payNowConfirmButton;
	
	
	public CheckoutPage() {
		PageFactory.initElements(super.getDriver(), this);
	}
	
	public boolean displayAppointmentSummary() {
		utils.log().info("Patient Details : " + patientName.getText());
		utils.log().info("Doctor Name : " + doctorName.getText());
		utils.log().info("Doctor Speciality : " + doctorSpeciality.getText());
		utils.log().info("Cosultation Type : " + consultationType.getText());
		utils.log().info("Clinic Name : " + clinicName.getText());
		utils.log().info("Clinic Speciality : " + clinicSpeciality.getText());
		utils.log().info("Clinic Distance : " + clinicDistance.getText());
		utils.log().info("Clinic Address : " + clinicAddress.getText());
		utils.log().info(paymentSummaryLabel.getText() + "\n " + consultationFeesLabel.getText() + " : " + feesAmount.getText() + "\n " + toBePaidLabel.getText() + " : " + feesAmount.getText());
		return true;
	}
	
	public boolean clickPayAtClinicRadioButton() {
		if(isUIElementDisplayed(payAtClinicRadioButton)) {
			super.click(payAtClinicRadioButton);
			utils.log().info("Clicked on Pay At Clinic Radio button");
			return true;
		}else {
			utils.log().info("Pay At Clinic Radio button is not displayed");
			return false;
		}
	}
	
	public boolean clickPayByCreditDebitCardRadioButton() {
		if(isUIElementDisplayed(payByCreditDebitRadioButton)) {
			super.click(payByCreditDebitRadioButton);
			utils.log().info("Clicked on Pay By Credeit Debit Radio button");
			return true;
		}else {
			utils.log().info("Pay By Credeit Debit Radio button is not displayed");
			return false;
		}
	}
	
	public boolean clickPayNowButton() {
		if(isUIElementDisplayed(payNowConfirmButton)) {
			super.click(payNowConfirmButton);
			utils.log().info("Clicked on Pay Now button");
			return true;
		}else {
			utils.log().info("Pay Now button is not displayed");
			return false;
		}
	}
	
	public boolean clickConfirmButton() {
		if(isUIElementDisplayed(payNowConfirmButton)) {
			super.click(payNowConfirmButton);
			utils.log().info("Clicked on Confirm button");
			return true;
		}else {
			utils.log().info("Confirm button is not displayed");
			return false;
		}
	}
	
	@Override
	public boolean isAt() {
		if(activeBreadcrum.getText().equals("Checkout")) {
			utils.log().info("On One Aster Consult A Doctor Checkout Page");
			return true;
		} else {
			utils.log().info("Not on One Aster Consult A Doctor Checkout Page");
			return false;
		}
	}
}
