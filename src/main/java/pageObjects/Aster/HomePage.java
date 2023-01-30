package pageObjects.Aster;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.WaitUtils;
import utils.WebElementUtils;

/**
 * Login Page Object class representing the log in page
 *
 * @author yavort
 */
public class HomePage {
	WebDriver driver;

	@FindBy(xpath = "//h2[@class='title']")
	private WebElement title;

	@FindBy(xpath = "//div[@class='menuButton']/following-sibling::div[@class='logo']")
	private WebElement logo;

	@FindBy(xpath = "//div[contains(@class,'searchBox')]//input[@type='text']")
	private WebElement globalSearchbox;

	@FindBy(xpath = "//div[@class='searchMenu']//div[text()='No Results Found']")
	private WebElement NoResults;

	@FindBy(xpath = "//div[@class='icGlobalSearch icCrossSearch']")
	private WebElement Cleartext;

	@FindBy(xpath = "//div[contains(text(),'Login')]")
	private WebElement LoginRegister;

	@FindBy(xpath = "//button[contains(@class,'btn btn-primary btn-md')][text()='Pharmacy']")
	private WebElement Pharmacy;

	@FindBy(xpath = "//button[contains(@class,'btn btn-primary btn-md')][text()='Consult a Doctor']")
	private WebElement consultaDoctor;

	@FindBy(xpath = "//div[@class='modal-content']")
	private WebElement MergecartPopup;

	@FindBy(xpath = "//div[@class='modal-content']//button[text()='Cancel']")
	private WebElement Mergecartcancel;

	@FindBy(xpath = "//div[@class='modal-content']//button[text()='Create a new cart']")
	private WebElement Mergenewcart;
	@FindBy(xpath = "//div[@class='menuButton']")
	private WebElement menuButton;

	@FindBy(xpath = "//li[@id='mouseTarget0']//div[text()='My Profile']")
	private WebElement menuMyProfile;

	@FindBy(xpath = "//li[@id='mouseTarget1']//div[text()='Insurance']")
	private WebElement menuInsurance;

	@FindBy(xpath = "//li[@id='mouseTarget2']//div[text()='Appointments']")
	private WebElement menuAppointments;

	@FindBy(xpath = "//li[@id='mouseTarget3']//div[text()='Lists']")
	private WebElement menuLists;

	@FindBy(xpath = "//li[@id='mouseTarget4']//div[text()='Records']")
	private WebElement menuRecords;

	@FindBy(xpath = "//li[@id='mouseTarget5']//div[text()='My Orders']")
	private WebElement menuMyOrders;

	@FindBy(xpath = "//li[@id='mouseTarget6']//div[text()='Manage Address']")
	private WebElement menuManageAddress;
	// button[text()='Create a new cart']

	// div[@class='icGlobalSearch icCrossSearch']
	// ul[@role='listbox']/li[@role='option']
	// div[@class='searchMenu']//div[text()='No Results Found']
	public HomePage(WebDriver driver) {
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
	public boolean VerifyHomepageFields(String fieldValue) throws InterruptedException {
		try {
			Thread.sleep(6000);
			Thread.sleep(6000);
			WebElement ele = driver.findElement(By.xpath("//h2[text()='" + fieldValue + "']"));

			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(ele, driver);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public void ClickonViewAll(String fieldValue) throws InterruptedException {
		Thread.sleep(6000);
		WebElement ele = driver
				.findElement(By.xpath("//h2[text()='" + fieldValue + "']/following-sibling::a[text()='View All']"));

		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(ele, driver);
		WebElementUtils.mouseClick(ele, driver);
		// ele.click();

	}

	public void ClickonMainLogo() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(logo, driver);
		WebElementUtils.mouseClick(logo, driver);

	}

	public String Globalsearch(String SearchVal) throws InterruptedException {
		String Val = null;
		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(globalSearchbox, driver);
		clickoncrossicon();
		globalSearchbox.sendKeys(SearchVal);
		globalSearchbox.click();
		Thread.sleep(3000);
		try {
			if (NoResults.isDisplayed()) {
				Val = "";
				return Val;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			List<WebElement> searchlist = driver.findElements(By.xpath("//ul[@role='listbox']/li[@role='option']"));
			int count = searchlist.size();
			for (int i = 1; i <= count; i++) {

				Val = getSearchtext(i);
				if (Val.contains(SearchVal)) {

					break;
				}
			}

		}
		return Val;

	}

	public String getSearchtext(int i) throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		// WaitUtils.isElementDisplayed(AsterClinics, driver);
		WebElement ele = driver.findElement(By.xpath("(//ul[@role='listbox']/li[@role='option'])[" + i + "]"));
		WaitUtils.isElementDisplayed(ele, driver);
		String searchvalues = ele.getText();
		return searchvalues;

	}

	public boolean clickoncrossicon() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			if (Cleartext.isDisplayed()) {
				WebElementUtils.mouseClick(Cleartext, driver);
			}
			// ViewAllforClinicsDoctors.isDisplayed();
			// ViewAllforClinicsDoctors.click();
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean verifyLoginRegister() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			// WaitUtils.isElementDisplayed(LoginRegister, driver);
			if (LoginRegister.isDisplayed()) {
				return true;
			} else {
				return false;
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public void clickLoginRegister() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			WaitUtils.isElementDisplayed(LoginRegister, driver);
			WebElementUtils.mouseClick(LoginRegister, driver);

		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
	}

	public void ClickonPharmacy() throws InterruptedException {
		Thread.sleep(6000);

		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(Pharmacy, driver);
		WebElementUtils.mouseClick(Pharmacy, driver);
		// ele.click();

	}

	public void ClickonConsultaDoctor() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(consultaDoctor, driver);
		WebElementUtils.mouseClick(consultaDoctor, driver);

	}

	public boolean verifyMergecartPopup() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			MergecartPopup.isDisplayed();
			// WaitUtils.isElementDisplayed(MergecartPopup, driver);
			// LoginRegister.isDisplayed();
			return true;

		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public void clickcancelMergecart() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(Mergecartcancel, driver);
		WebElementUtils.mouseClick(Mergecartcancel, driver);

	}

	public void clicknewMergecart() throws InterruptedException {

		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		WaitUtils.isElementDisplayed(Mergenewcart, driver);
		WebElementUtils.mouseClick(Mergenewcart, driver);

	}

	public String clickOnMenuButton() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			String val = menuButton.getText();
			if (menuButton.isDisplayed()) {
				WebElementUtils.mouseClick(menuButton, driver);
			}

			return val;

		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
		return null;
	}

	public boolean clickOnMenuMyProfile() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			if (menuMyProfile.isDisplayed()) {
				WebElementUtils.mouseClick(menuMyProfile, driver);
			}
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean clickOnMenuMyInsurance() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			if (menuInsurance.isDisplayed()) {
				WebElementUtils.mouseClick(menuInsurance, driver);
			}
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean clickOnMenuAppointments() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			if (menuAppointments.isDisplayed()) {
				WebElementUtils.mouseClick(menuAppointments, driver);
			}
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public String clickOnMenuLists() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			if (menuLists.isDisplayed()) {
				WebElementUtils.mouseClick(menuLists, driver);
			}
			String val = menuLists.getText();
			return val;
		} catch (org.openqa.selenium.NoSuchElementException e) {

		}
		return null;
	}

	public boolean clickOnMenuRecords() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			if (menuRecords.isDisplayed()) {
				WebElementUtils.mouseClick(menuRecords, driver);
			}
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean clickOnMenuMyOrders() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			if (menuMyOrders.isDisplayed()) {
				WebElementUtils.mouseClick(menuMyOrders, driver);
			}
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean clickOnMenuManageAddresss() throws InterruptedException {
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			if (menuManageAddress.isDisplayed()) {
				WebElementUtils.mouseClick(menuManageAddress, driver);
			}
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
}
