package pageObjects.Aster;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import utils.WaitUtils;
import utils.WebElementUtils;

import java.util.List;

/**
 * Login Page Object class representing the log in page
 *
 * @author yavort
 */
public class CommonHomeDoctor {
	WebDriver driver;
	private HomePage homepage;
	@FindBy(xpath = "//h2[@class='title']")

	private WebElement title;
	@FindBy(xpath = "//span[@class='form-label']")
	private WebElement doctorscount;

	@FindBy(xpath = "//span[text()='All Specialties']")
	private WebElement AllSpecialties;

	@FindBy(xpath = "//div[contains(@class,'form-label')]/strong")
	private WebElement clinicsavailable;

	@FindBy(xpath = "//li[contains(@class,'pagination-item')]//div[@class='arrow right']")
	private WebElement arrowright;

	@FindBy(xpath = "//li[contains(@class,'breadcrumb-item')]/*[text()='Aster Clinics']")
	private WebElement AsterClinics;

	@FindBy(xpath = "//span[@title='Aster Facilities']")
	private WebElement ClinicsTitle;

	@FindBy(xpath = "//div[contains(@class,'form-label')]/strong[text()='Soonest Available']/following::div[text()='View All']")
	private WebElement ViewAllforClinicsDoctors;

	@FindBy(xpath = "//div[@class='slotWrapper']//div[@class='slotDate']")
	private WebElement slotDate;

	@FindBy(xpath = "(//div[@class='slotWrapper']//div[@class='slotTime'])[1]")
	private WebElement slottimeperiod;

	@FindBy(xpath = "(//div[@class='slotWrapper']//div[@class='slots']//div[contains(@class,'slot')])[1]")
	private WebElement slottime;

	public CommonHomeDoctor(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WaitUtils.waitForDocumentReadyState(driver);
	}

	/**
	 * This method is used to facilitate the login process (it ends before Stay
	 * Signed In confirmation)
	 *
	 * @param email    The username email
	 * @param password The password
	 * @return
	 * @throws InterruptedException
	 */

	public boolean VerifyDoctorSpecialitylistpagetitle(String fieldValue) throws InterruptedException {
		boolean val = false;
		Thread.sleep(6000);
		WebElement ele = driver.findElement(By.xpath("//li[@class='breadcrumb-item active']"));

		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(ele, driver);
		if (ele.getText().equalsIgnoreCase(fieldValue)) {
			val = true;
		}
		return val;
	}

	public String ClickDoctorSpecialitylistpage(int i) throws InterruptedException {
		Thread.sleep(6000);
		WebElement ele = driver
				.findElement(By.xpath("(//div[@class='specialty-item-wrapper']//div[@class='item'])[" + i + "]"));

		WaitUtils.isElementDisplayed(ele, driver);
		// test.log(Status.PASS, "Category " + Category + " is displayed");
		WebElement specialtylistvalue = driver.findElement(
				By.xpath("(//div[@class='specialty-item-wrapper']//div[contains(@class,'type')])[" + i + "]"));
		String specialty = specialtylistvalue.getText();
		ele.click();
		return specialty;

	}

	public String getDoctorSpecialitytitle(int i) throws InterruptedException {
		Thread.sleep(6000);
		WebElement ele = driver
				.findElement(By.xpath("(//div[@class='specialty-item-wrapper']//div[@class='item'])[" + i + "]"));

		WaitUtils.isElementDisplayed(ele, driver);
		// test.log(Status.PASS, "Category " + Category + " is displayed");
		WebElement specialtylistvalue = driver.findElement(
				By.xpath("(//div[@class='specialty-item-wrapper']//div[contains(@class,'type')])[" + i + "]"));
		String specialty = specialtylistvalue.getText();

		return specialty;

	}

	public boolean verifydoctorcount() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			if (doctorscount.isDisplayed()) {

			}
			// ViewAllforClinicsDoctors.isDisplayed();
			// ViewAllforClinicsDoctors.click();
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public String returndoctorcount() throws InterruptedException {
		// String value = "";
		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(doctorscount, driver);
		String productcounttext = doctorscount.getText();
		return productcounttext;

	}

	public boolean VerifyProductscount() {

		String doctorcounttext = doctorscount.getText();

		String[] arrOfStr = doctorcounttext.split(" ");
		if (arrOfStr[0].equals("0")) {

			return false;
		} else {
			return true;
		}

	}

	public List<WebElement> getTopSpecialties() throws InterruptedException {
		Thread.sleep(3000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(AllSpecialties, driver);
		List<WebElement> specialtieslist = driver
				.findElements(By.xpath("//div[@class='specialty-item-wrapper']//div[@class='item']"));
		return specialtieslist;

	}

	public String clinicsavailable() throws InterruptedException {
		Thread.sleep(3000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(clinicsavailable, driver);
		String count = clinicsavailable.getText();
		return count;

	}

	public boolean arrowright() throws InterruptedException {
		boolean val = true;
		Thread.sleep(3000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(arrowright, driver);
		if (arrowright.isEnabled()) {
			val = false;
		}
		return val;

	}

	public void clickarrowright() throws InterruptedException {

		Thread.sleep(3000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(arrowright, driver);
		WebElementUtils.mouseClick(arrowright, driver);
		// arrowright.click();

	}

	public boolean clickonViewAll() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			if (ViewAllforClinicsDoctors.isDisplayed()) {
				WebElementUtils.mouseClick(ViewAllforClinicsDoctors, driver);
			}
			// ViewAllforClinicsDoctors.isDisplayed();
			// ViewAllforClinicsDoctors.click();
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public List<WebElement> getClinicsList() throws InterruptedException {
		Thread.sleep(3000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(ClinicsTitle, driver);
		List<WebElement> clinicslist = driver.findElements(By
				.xpath("//div[contains(@class,'clinic-item-wrapper row')]//div[@class='clinic_item cursor-pointer']"));
		return clinicslist;

	}

	public void ClinicPageDisplayed() throws InterruptedException {
		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(ClinicsTitle, driver);
	}

	public String getClinicname(int i) throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		// WaitUtils.isElementDisplayed(AsterClinics, driver);
		WebElement ele = driver.findElement(
				By.xpath("(//div[@class='info-wrapper']//div[contains(@class,'clinic_name')])[" + i + "]"));
		WaitUtils.isElementDisplayed(ele, driver);
		String clinicsname = ele.getText();
		return clinicsname;

	}

	public void clickonClinics(int i) throws InterruptedException {
		Thread.sleep(3000);
		WaitUtils.waitForDocumentReadyState(driver);
		// WaitUtils.isElementDisplayed(AsterClinics, driver);
		WebElement clinicslist = driver
				.findElement(By.xpath("(//div[@class='clinic_item cursor-pointer'])[" + i + "]"));
		WaitUtils.isElementDisplayed(clinicslist, driver);
		// WebElementUtils.executeJavaScriptClick(clinicslist, driver);
		// WebElementUtils.mouseOver(clinicslist, driver);
		clinicslist.click();

	}

	public void clickonAsterclinicinHirerachy() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(AsterClinics, driver);
		WebElementUtils.mouseClick(AsterClinics, driver);
		// Asterclinic.click();

	}

	public void clickonPage(int i) throws InterruptedException {
		Thread.sleep(6000);
		WebElement ele = driver.findElement(By.xpath("//li[contains(@class,'pagination-item')][" + i + "]"));
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(ele, driver);
		// test.log(Status.PASS, "Category " + Category + " is displayed");
		WebElementUtils.mouseClick(ele, driver);
		// ele.click();

	}

	public List<WebElement> getPagenumbers() throws InterruptedException {
		Thread.sleep(3000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(ClinicsTitle, driver);
		List<WebElement> clinicslist = driver.findElements(By.xpath("//li[contains(@class,'pagination-item')]"));
		return clinicslist;

	}

	public String getDoctorName(int i) throws InterruptedException {
		String val = null;
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			// WaitUtils.isElementDisplayed(AsterClinics, driver);
			WebElement ele = driver.findElement(
					By.xpath("(//div[contains(@class,'DoctorCard')]//div[@class='doctorName'])[" + i + "]"));
			WaitUtils.isElementDisplayed(ele, driver);
			String doctorName = ele.getText();
			val = doctorName;
			return val;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return val;
		}

	}

	public boolean ClickDoctorName(int i) throws InterruptedException {
		boolean val = false;
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			// WaitUtils.isElementDisplayed(AsterClinics, driver);
			WebElement ele = driver.findElement(By.xpath("(//div[@class='doctorDetail'])[" + i + "]"));
			WaitUtils.isElementDisplayed(ele, driver);
			WebElementUtils.mouseClick(ele, driver);
			val = true;
			return val;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return val;
		}

	}

	public String getSlotDate() throws InterruptedException {
		String val = null;
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			// WaitUtils.isElementDisplayed(AsterClinics, driver);

			WaitUtils.isElementDisplayed(slotDate, driver);
			String slotdate = slotDate.getText();
			val = slotdate;
			return val;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return val;
		}
	}

	public String getSlottimeperiod() throws InterruptedException {
		String val = null;
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			// WaitUtils.isElementDisplayed(AsterClinics, driver);

			WaitUtils.isElementDisplayed(slottimeperiod, driver);
			String slot = slottimeperiod.getText();
			val = slot;
			return val;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return val;
		}

	}

	public String getSlottimeslot() throws InterruptedException {
		String val = null;
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			// WaitUtils.isElementDisplayed(AsterClinics, driver);

			WaitUtils.isElementDisplayed(slottime, driver);
			String slot = slottime.getText();
			val = slot;
			return val;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return val;
		}

	}
	
	public void clickontimeslot() throws InterruptedException
	{
		
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			// WaitUtils.isElementDisplayed(AsterClinics, driver);

			WaitUtils.isElementDisplayed(slottime, driver);
			WebElementUtils.mouseClick(slottime, driver);
			
		} catch (org.openqa.selenium.NoSuchElementException e) {
			
		}
	}

}
