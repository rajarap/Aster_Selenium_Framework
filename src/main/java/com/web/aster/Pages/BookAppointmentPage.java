package com.web.aster.Pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.web.aster.Base.BaseClass;
import com.web.aster.Interfaces.Page;
import com.web.aster.Utilities.ScrollAction;
import com.web.aster.Utilities.TestUtils;


public class BookAppointmentPage extends BaseClass implements Page{

	TestUtils utils = new TestUtils();
	ScrollAction sa = new ScrollAction();
	List<WebElement> timeSlot;
	
	
	@FindBy(xpath = "//input[@id='inperson']") 
	private WebElement inPersonRadioButton;
	
	@FindBy(xpath = "//input[@id='video']") 
	private WebElement videoRadioButton;

	@FindBy(xpath = "//div[normalize-space()='Confirm Appointment']") 
	private WebElement confirmAppointmentButton;
		
	@FindBy(xpath = "//input[@id='selfpay']") 
	private WebElement selfPayRadioButton;
	
	@FindBy(xpath = "//input[@id='insurance']") 
	private WebElement payViaInsuranceRadioButton;
	
	@FindBy(xpath = "//h2[normalize-space()='Preferred Payment Method']") 
	private WebElement preferredPaymentMethodHeading;
	
	@FindBy(xpath = "//div[@class='HereToHelpStyled-sc-g3iw3m-0 iRIPDo bg-white']//div[@class='text']") 
	private WebElement weAreHereToHelp;
	
	@FindBy(xpath = "//button[normalize-space()='Help Center']") 
	private WebElement helpCenterButton;
	
	
	
	
	public BookAppointmentPage() {
		PageFactory.initElements(super.getDriver(), this);
	}
	
	public boolean clickInPersonConsultation() {
		if(isUIElementDisplayed(inPersonRadioButton)) {
			super.click(inPersonRadioButton);
			utils.log().info("Clicked on InPerson Radio button");
			return true;
		}else {
			utils.log().info("InPerson Radio button is not displayed");
			return false;
		}
	}
	
	public boolean clickVideoConsultation() {
		if(isUIElementDisplayed(videoRadioButton)) {
			super.click(videoRadioButton);
			utils.log().info("Clicked on Video Consultation Radio button");
			return true;
		}else {
			utils.log().info("Video Consultation Radio button is not displayed");
			return false;
		}
	}
	
	public boolean clickConfirmAppointmentButton() {
		if(isUIElementDisplayed(confirmAppointmentButton)) {
			super.click(confirmAppointmentButton);
			utils.log().info("Clicked on Confirm Appointment button");
			return true;
		}else {
			utils.log().info("Confirm Appointment button is not displayed");
			return false;
		}
	}
	
	public void selectNextTimeSlot() {
		sa.scrollDown();

		timeSlot = getDriver().findElements(By.xpath("//div[@class='time-slot-wrapper d-flex flex-wrap']/div/span"));
		utils.log().info("No of timeslot items are : " + timeSlot.size());

		if (timeSlot.size() > 0) {
			for (int i = 0; i < timeSlot.size(); i++) {
				WebElement item = timeSlot.get(i);	
				int j = i + 1;
				WebElement timeSlotElement = getDriver().findElement(By.xpath("//div[contains(@class,'time-slot-wrapper d-flex flex-wrap')]//div["+j+"]"));
				
				if (!timeSlotElement.getAttribute("class").contains("disabled")) {
					utils.log().info("First Time slot available :" + item.getText());
					super.click(item);
					utils.log().info("Selected Time slot :" + item.getText());
					break;
				}
			}
		}
	}
		
	public boolean selectPaymentMethod() {
		if(timeSlot.size() > 15) {
			sa.scrollDownTwice();
		}
		if(isUIElementDisplayed(selfPayRadioButton)) {
			super.click(selfPayRadioButton);
			utils.log().info("Clicked on Self Pay Radio button");
			return true;
		}else {
			utils.log().info("Self Pay Radio button is not displayed");
			return false;
		}

	}
	
	@Override
	public boolean isAt() {
		if(getDriver().getTitle().equalsIgnoreCase("Doctor Details")) {
			utils.log().info("On One Aster online Doctor Details Page");
			return true;
		} else {
			utils.log().info("Not on One Aster online Doctor Details Page");
			return false;
		}
	}
}
