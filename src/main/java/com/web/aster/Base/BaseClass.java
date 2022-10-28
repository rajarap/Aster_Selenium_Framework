package com.web.aster.Base;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.web.aster.Utilities.EmailConfig;
import com.web.aster.Utilities.ScrollAction;
import com.web.aster.Utilities.TestUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public Properties properties = new Properties();
	TestUtils utils = new TestUtils();
	EmailConfig emailConfig = new EmailConfig();

	protected static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	protected static ThreadLocal<RemoteWebDriver> remoteWebDriver = new ThreadLocal<RemoteWebDriver>();
	protected static ThreadLocal<String> browserName = new ThreadLocal<String>();
	protected static ThreadLocal<Properties> props = new ThreadLocal<Properties>();
	protected static ThreadLocal<String> dateTime = new ThreadLocal<String>();

	public DesiredCapabilities dc;
	public WebDriver driver;
	public RemoteWebDriver remoteDriver;

	public ServerSocket socket = null;
	public InputStream inputStream = null;
	public InputStream testData = null;

	public File ssFile;
	public URL url;
	public int port;

	public String plt;
	public String dvc;

	private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";

	public BaseClass() {

	}

	// chrome, 103, uat, Windows 10
	@Parameters({ "browser", "version", "environment", "platform", "executionEnv" })
	@BeforeTest
	public void beforeTest(String browserName, String version, String environment, String platform, String exeEnv) throws MalformedURLException {
		
		setDateTime(utils.dateTime());
		setConfigProperties();
		setBrowserName(browserName);
		
		if(exeEnv.equalsIgnoreCase("local")) {
			if (browserName.equalsIgnoreCase("chrome")) {
				setBrowserName(browserName);
				utils.log().info("Setting " + getBrowserName() + " Webdriver capabilities");
				
				ChromeOptions opt = new ChromeOptions();
				opt.addArguments("--start-maximized");
				opt.addArguments("--ignore-certificate-errors");
				opt.addArguments("--disable-popup-blocking");
				opt.addArguments("--deny-permission-prompts");
				opt.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
				
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);

				opt.setExperimentalOption("prefs", prefs);
				
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(opt);
				setDriver(driver);
				getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
				utils.log().info(getBrowserName() + " WebDriver has been initialized");
				launchApp(environment);
			}

			if (browserName.equalsIgnoreCase("firefox")) {
				setBrowserName(browserName);
				utils.log().info("Setting " + getBrowserName() + " Webdriver capabilities");
				
				FirefoxOptions ffProfile = new FirefoxOptions();
				ffProfile.addPreference("geo.enabled", false);
				ffProfile.addPreference("signon.rememberSignons", false);
				
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver(ffProfile);
				setDriver(driver);
				getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
				utils.log().info(getBrowserName() + " WebDriver has been initialized");
				launchApp(environment);
			}

			if (browserName.equalsIgnoreCase("ie")) {
				setBrowserName(browserName);
				utils.log().info("Setting " + getBrowserName() + " Webdriver capabilities");
				
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				setDriver(driver);
				getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
				utils.log().info(getBrowserName() + " WebDriver has been initialized");
				launchApp(environment);
			}
		}
		
		if(exeEnv.equalsIgnoreCase("remote")) {
			if (browserName.equalsIgnoreCase("chrome")) {
				setBrowserName(browserName);
				
				ChromeOptions chOpt = new ChromeOptions();
				chOpt.addArguments("--start-maximized");
				chOpt.addArguments("--ignore-certificate-errors");
				chOpt.addArguments("--disable-popup-blocking");
				chOpt.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
				chOpt.setPlatformName(platform);
				chOpt.setBrowserVersion(version);

				Map<String, Object> sauceOptions = new HashMap<>();
				sauceOptions.put("build", "2.1.2-16-UAT");
				sauceOptions.put("name", "1Aster_UAT_Regression_Test");
				chOpt.setCapability("sauce:options", sauceOptions);

				String URL = "https://"+getProps().getProperty("username")+ ":" + getProps().getProperty("accesskey")
						+ "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";
				
				url = new URL(URL);
				WebDriver driver = new RemoteWebDriver(url, chOpt);
				setDriver(driver);
				getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
				utils.log().info(getBrowserName() + " WebDriver has been initialized");
				launchApp(environment);
			}

			if (browserName.equalsIgnoreCase("firefox")) {
				setBrowserName(browserName);
				utils.log().info("Setting " + getBrowserName() + " Webdriver capabilities");
				
				FirefoxOptions ffOpts = new FirefoxOptions();
				ffOpts.addPreference("geo.enabled", false);
				ffOpts.setPlatformName(platform);
				ffOpts.setBrowserVersion(version);

				Map<String, Object> sauceOptions = new HashMap<>();
				sauceOptions.put("build", "2.1.2-16-UAT");
				sauceOptions.put("name", "1Aster_UAT_Regression_Test");
				ffOpts.setCapability("sauce:options", sauceOptions);

				String URL = "https://oauth-prabhu.rajarathinam-74cf9" + ":" + getProps().getProperty("accesskey")
						+ "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";
				
				url = new URL(URL);
				WebDriver driver = new RemoteWebDriver(url, ffOpts);
				setDriver(driver);
				getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
				utils.log().info(getBrowserName() + " WebDriver has been initialized");
				launchApp(environment);
			}

			if (browserName.equalsIgnoreCase("ie")) {
				setBrowserName(browserName);
				utils.log().info("Setting " + getBrowserName() + " Webdriver capabilities");
				
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				setDriver(driver);
				getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
				utils.log().info(getBrowserName() + " WebDriver has been initialized");
				launchApp(environment);
			}		
		}
	}


	@AfterTest
	public void afterTest() {
		utils.log().info("Closing App...");
		closeApp();
	}

	public void closeApp() {
		getDriver().close();
	}

	public void launchApp(String env) {
		if (env.equalsIgnoreCase("qa")) {
			getDriver().get(getProps().getProperty("qaURL"));
			utils.log().info("Launching 1Aster - QA Environemnt");
		}

		if (env.equalsIgnoreCase("uat")) {
			getDriver().get(getProps().getProperty("uatURL"));
			utils.log().info("Launching 1Aster - UAT Environemnt");
		}

		if (env.equalsIgnoreCase("prod")) {
			getDriver().get(getProps().getProperty("prodURL"));
			utils.log().info("Launching 1Aster - PROD Environemnt");
		}
		
		if (env.equalsIgnoreCase("pre-prod")) {
			getDriver().get(getProps().getProperty("preProdURL"));
			utils.log().info("Launching myAster - PRE-PROD Environemnt");
		}
	}

	public void takeScreenshot(String methodName, ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		String ssPath = "Screenshots" + File.separator + getBrowserName() + File.separator + utils.onlyDate();
		ssFile = new File(ssPath);
		if (!ssFile.exists()) {
			ssFile.mkdirs();
		}
		File file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(ssFile.getAbsolutePath() + File.separator
					+ result.getTestClass().getRealClass().getSimpleName() + File.separator + methodName + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Reporter.log("<a href = " + ssFile.getAbsolutePath() + File.separator
				+ result.getTestClass().getRealClass().getSimpleName() + File.separator + methodName + ".jpg"
				+ ">screenshot</a>");
	}

	public void setConfigProperties() {
		String propFileName = "config.properties";
		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setProps(properties);
	}
	
	public void loadTestData(String filePath) {
		try {
			String dataFilename = filePath;
			utils.log().info("loading..." + dataFilename);
			testData = getClass().getClassLoader().getResourceAsStream(dataFilename);
			properties.load(testData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver getDriver() {
		return webDriver.get();
	}

	public void setDriver(WebDriver driver) {
		webDriver.set(driver);
	}

	public Properties getProperties() {
		return props.get();
	}

	public void setProperties(Properties properties) {
		props.set(properties);
	}

	public String getDateTime() {
		return dateTime.get();
	}

	public void setDateTime(String dateTime2) {
		dateTime.set(dateTime2);
	}

	public String getBrowserName() {
		return browserName.get();
	}

	public void setBrowserName(String platform2) {
		browserName.set(platform2);
	}

	public Properties getProps() {
		return props.get();
	}

	public void setProps(Properties props2) {
		props.set(props2);
	}

	@AfterSuite
	public void afterSuite() throws MessagingException {
		utils.log().info("Execution Done....");
		utils.log().info("Quitting Driver...");
		driver.quit();
	}

	public void waitForVisibility(WebElement e) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public void clear(WebElement w) {
		waitForVisibility(w);
		w.clear();
	}

	public void click(WebElement e) {
		waitForVisibility(e);
		e.click();
	}

	public void typeData(WebElement e, String txt) {
		waitForVisibility(e);
		e.sendKeys(txt);
	}

	public boolean isUIElementDisplayed(WebElement e) {
		waitForVisibility(e);
		if (e.isDisplayed()) {
			return true;
		} else {
			return false;
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

	public synchronized String getMobileNumber(String countryCode) {
		Random random = new Random();
		Integer number = 0;
		String mobNo = "";

		if (countryCode.equals("+971")) {
			number = random.nextInt(9999999);
			if(number.toString().length() == 7) {
				mobNo = "55" + number.toString();
			}else {
				getMobileNumber("+971");
			}
		}
		
		if (countryCode.equals("+91")) {
			number = random.nextInt(9999999);
			if(number.toString().length() == 7) {
				mobNo = "998" + number.toString();
			}else {
				getMobileNumber("+91");
			}
		}

		return mobNo;
	}

	public String getCountryCode() {
		String[] ccode = {"+971", "+91"};
//		String[] ccode = { "+971" };
		int index = new Random().nextInt(ccode.length);
		return ccode[index];
	}
	
	public String getCountryName() {
//     	String[] arr={"India", "Australia"};
      	String[] arr={"India"};
      	Random r=new Random();        
      	int randomNumber=r.nextInt(arr.length);
      	return(arr[randomNumber]);
	}
	
	public String getFirstName() {
		Random random = new Random();
		Integer number = random.nextInt(9999);
		String fname = "first" + number.toString();
		return fname;
	}
	
	public String getLastName() {
		Random random = new Random();
		Integer number = random.nextInt(9999);
		String fname = "last" + number.toString();
		return fname;
	}
	
	//nationality on registration page for Native
	public void selectOptionFromList(WebElement e, String value) {
		Select s = new Select(e);
		List<WebElement>options = s.getOptions();
		utils.log().info("Total Number of items availabe in dropdown box is : " + options.size());
		
		for (int i = 0; i < options.size(); i++) {
			WebElement item = (WebElement) options.get(i);
			if (item.getText().equals(value)) {
				click(item);
				break;
			}
		}
	}
	
	public void scrollUp(){
		 new ScrollAction().scrollUp();
	}
	 
	public void scrollDown(){
		 new ScrollAction().scrollDown();
	}
	
	public void scrollToElement(WebElement e){
		 new ScrollAction().ScrollTillElementVisible(e);
	}

	public void scrollToBottom() {
		 new ScrollAction().ScrollToBottom();
	}
	
}
