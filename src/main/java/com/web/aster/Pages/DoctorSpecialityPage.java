package com.web.aster.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.web.aster.Base.BaseClass;
import com.web.aster.Interfaces.Page;
import com.web.aster.Utilities.ScrollAction;
import com.web.aster.Utilities.TestUtils;


public class DoctorSpecialityPage extends BaseClass implements Page{

	TestUtils utils = new TestUtils();
	ScrollAction sa = new ScrollAction();
	
	
	@FindBy(xpath = "//div[@class='col-lg-9 col-md-8']") 
	private WebElement doctorsList;
	
	@FindBy(xpath = "//span[@class='form-label']") 
	private WebElement doctorsCount;

	////div[@class='flex-grow-1 mobile-right-panel']/div[5]/button
	//@FindBy(xpath = "//button[@class='primary-outlined card-btn-width']") 
	
	@FindBy(xpath = "//div[@class='flex-grow-1 mobile-right-panel']/div[5]/button") 
	private WebElement bookAppointmentButton;

	
	public DoctorSpecialityPage() {
		PageFactory.initElements(super.getDriver(), this);
	}
	
	public boolean clickBookAppointmentButton() {
		if(isUIElementDisplayed(bookAppointmentButton)) {
			super.click(bookAppointmentButton);
			utils.log().info("Clicked on Book Appointment button");
			return true;
		}else {
			utils.log().info("Book Appointment button is not displayed");
			return false;
		}
	}
	
	public String getDoctorCount() {
		String dname = doctorsCount.getText(); //27 Doctor(s)
		String[] dcount = dname.split("");
		return dcount[0];
	}
	
	
	public void get10DoctorDetails(){
		Integer docCount = Integer.valueOf(getDoctorCount());
		if(docCount > 0 ) {
			if (docCount >= 10) {
				for (int i = 0; i < 10; i++) {
					utils.log().info("Doctor Name 		: " + getDriver().findElement(By.xpath("//div[@class='flex-grow-1 mobile-right-panel']/div[1]/div")).getText());
					utils.log().info("Doctor Speciality : " + getDriver().findElement(By.xpath("//div[@class='flex-grow-1 mobile-right-panel']/div[1]/p")).getText());
					utils.log().info("Speaks Languages	: " + getDriver().findElement(By.xpath("//div[@class='flex-grow-1 mobile-right-panel']/div[2]/p")).getText());
					utils.log().info("Consultation Type	: " + getDriver().findElement(By.xpath("//div[@class='flex-grow-1 mobile-right-panel']/div[3]/p[1]")).getText() + "" + getDriver().findElement(By.xpath("//div[@class='flex-grow-1 mobile-right-panel']/div[3]/p[2]")).getText());
					utils.log().info("Works At			: " + getDriver().findElement(By.xpath("//div[@class='flex-grow-1 mobile-right-panel']/div[4]/p")).getText());
				
					if(i == 1 || i == 3 || i ==5 || i ==7 || i == 9) {
						sa.scrollUp();
					}
				}
			}else {
				utils.log().info("There are less tham 10 doctors listed for the selected speciality");
			}
		}else {
			utils.log().info("There are no doctors listed for the selected speciality");
		}
	}
	@Override
	public boolean isAt() {
		if(getDriver().getTitle().contains("Specialty | ")) {
			utils.log().info("On One Aster online doctor speciality Page");
			return true;
		} else {
			utils.log().info("Not on One Aster online doctor speciality Page");
			return false;
		}
	}
}
