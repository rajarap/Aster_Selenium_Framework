package com.web.aster.Base;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.ThreadContext;
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
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.web.aster.Utilities.TestUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public Properties properties = new Properties();
	TestUtils utils = new TestUtils();

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

	public String browser;
	public String env;

	public String plt;
	public String dvc;

	private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";

	public BaseClass() {

	}

	@Parameters({ "browserName", "environment" })
	@BeforeTest
	public void beforeTest(String browserName, String environment) {
		this.browser = browserName;
		this.env = environment;
		
		this.browser = browserName;
		this.env = environment;

		setDateTime(utils.dateTime());
		setConfigProperties();

		if (this.browser.equalsIgnoreCase("chrome")) {
			setBrowserName(this.browser);
			utils.log().info("Setting " + getBrowserName() + " Webdriver capabilities");
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--start-maximized");
			opt.addArguments("--ignore-certificate-errors");
			opt.addArguments("--disable-popup-blocking");
			opt.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(opt);
			setDriver(driver);
			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
			utils.log().info(getBrowserName() + " WebDriver has been initialized");
			launchApp(this.env);
		}

		if (this.browser.equalsIgnoreCase("firefox")) {
			setBrowserName(this.browser);
			utils.log().info("Setting " + getBrowserName() + " Webdriver capabilities");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			setDriver(driver);
			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
			utils.log().info(getBrowserName() + " WebDriver has been initialized");
			launchApp(this.env);
		}

		if (this.browser.equalsIgnoreCase("ie")) {
			setBrowserName(this.browser);
			utils.log().info("Setting " + getBrowserName() + " Webdriver capabilities");
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			setDriver(driver);
			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
			utils.log().info(getBrowserName() + " WebDriver has been initialized");
			launchApp(this.env);
		}
	}
	


	@AfterTest
	public void afterTest() {
		utils.log().info("Closing App...");
		closeApp();
		utils.log().info("Quitting Driver...");
		getDriver().quit();
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

	public WebDriver getDriver() {
		return webDriver.get();
	}

	public void setDriver(WebDriver driver) {
		webDriver.set(driver);
	}

	public RemoteWebDriver getRemoteWebDriver() {
		return remoteWebDriver.get();
	}

	public void setRemoteWebDriver(RemoteWebDriver driver) {
		remoteWebDriver.set((RemoteWebDriver) driver);
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
	public void afterSuite() {
		utils.log().info("Execution Done....");

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
		
		for(WebElement item : options) {
			if (item.getText().equals(countryCode)) {
				click(item);
				break;
			}
		}

	}

	public long generateMobileNumber(String countryCode) {
		Random random = new Random();
		long number=0;
		
		if(countryCode.equals("+971")) {
			number = random.nextInt(999999999);
		if(countryCode.equals("+91")) {
			number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
			}
		}
		return number;
	}

	public String getCountryCode() {
		String[] ccode = {"+971", "+91"};
		int index = new Random().nextInt(ccode.length);
	    return ccode[index];
	}
}



	












































//@SuppressWarnings("deprecation")
//@Parameters({ "browserName", "environment" })
//@BeforeTest
//public void beforeTest(String browserName, String environment) {
//	this.browser = browserName;
//	this.env = environment;
//
//	try {
//		setDateTime(utils.dateTime());
//		setConfigProperties();
//
//		switch (this.browser) {
//		case "chrome":
//			setBrowserName(this.browser);
//			utils.log().info("Setting " + getBrowserName() + " Webdriver capabilities");
//			ChromeOptions opt = new ChromeOptions();
//			opt.addArguments("--start-maximized");
//			opt.addArguments("--ignore-certificate-errors");
//			opt.addArguments("--disable-popup-blocking");
//			opt.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver(opt);
//			setDriver(driver);
//			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
//			utils.log().info(getBrowserName() + " WebDriver has been initialized");
//			launchApp(this.env);
//			break;
//		case "firefox":
//			setBrowserName(this.browser);
//			utils.log().info("Setting " + getBrowserName() + " Webdriver capabilities");
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
//			setDriver(driver);
//			// getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
//			utils.log().info(getBrowserName() + " WebDriver has been initialized");
//			launchApp(this.env);
//			break;
//		case "ie":
//			setBrowserName(this.browser);
//			utils.log().info("Setting " + getBrowserName() + " Webdriver capabilities");
//			WebDriverManager.iedriver().setup();
//			driver = new InternetExplorerDriver();
//			setDriver(driver);
//			// getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
//			utils.log().info(getBrowserName() + " WebDriver has been initialized");
//			launchApp(this.env);
//			break;
//		default:
//			// utils.log().info("Please mention platform name to execute your automation
//			// scripts!!!! ");
//		}
//	} catch (Exception e) {
//		utils.log().fatal(
//				"Unable to initialize Webdriver for " + getBrowserName() + "...... ABORTING !!!\n" + e.toString());
//	}
//}

//@SuppressWarnings("deprecation")
//@Parameters({ "browserName", "environment", "host", "portNo" })
//@BeforeTest
//public void beforeTest(String browserName, String environment, String host, String portNo) {
//	this.browser = browserName;
//	this.env = environment;
//	this.host = host;
//	this.portNo = portNo;
//
//	try {
//		setDateTime(utils.dateTime());
//		setConfigProperties();
//
//		if (this.browser != null && this.env != null && this.host != null && this.portNo != null) {
//			switch (this.browser) {
//			case "chrome":
//				setBrowserName(this.browser);
//				utils.log().info("Setting " + getBrowserName() + " Webdriver capabilities");
//				ChromeOptions chormeOptions = new ChromeOptions();
//				chormeOptions.addArguments("--start-maximized");
//				chormeOptions.addArguments("--ignore-certificate-errors");
//				chormeOptions.addArguments("--disable-popup-blocking");
//				chormeOptions.addArguments("--incognito");
//
//				hub_url = "http://" + this.host + ":" + this.portNo + "/wd/hub";
//
//				remoteDriver = new RemoteWebDriver(new URL(hub_url), chormeOptions);
//				setRemoteWebDriver(remoteDriver);
//				getRemoteWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
//				utils.log().info(getBrowserName() + " RemoteWebDriver has been initialized");
//				launchApp(this.env);
//				break;
//			case "firefox":
//				setBrowserName(this.browser);
//				utils.log().info("Setting " + getBrowserName() + " Webdriver capabilities");
//				FirefoxOptions firefoxOptions = new FirefoxOptions();
//				firefoxOptions.addArguments("--start-maximized");
//				firefoxOptions.addArguments("--ignore-certificate-errors");
//				firefoxOptions.addArguments("--disable-popup-blocking");
//				firefoxOptions.addArguments("--incognito");
//
//				hub_url = "http://" + this.host + ":" + this.portNo + "/wd/hub";
//				remoteDriver = new RemoteWebDriver(new URL(hub_url), firefoxOptions);
//				setRemoteWebDriver(remoteDriver);
//				getRemoteWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
//				utils.log().info(getBrowserName() + " RemoteWebDriver has been initialized");
//				launchApp(this.env);
//				break;
//			case "ie":
//				setBrowserName(this.browser);
//				utils.log().info("Setting " + getBrowserName() + " Webdriver capabilities");
//				InternetExplorerOptions ieOptions = new InternetExplorerOptions();
//				ieOptions.introduceFlakinessByIgnoringSecurityDomains();
//				ieOptions.ignoreZoomSettings();
//				ieOptions.destructivelyEnsureCleanSession();
//
//				hub_url = "http://" + this.host + ":" + this.portNo + "/wd/hub";
//				remoteDriver = new RemoteWebDriver(new URL(hub_url), ieOptions);
//				setRemoteWebDriver(remoteDriver);
//				getRemoteWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
//				utils.log().info(getBrowserName() + " RemoteWebDriver has been initialized");
//				launchApp(this.env);
//				break;
//			default:
//				// utils.log().info("Please mention platform name to execute your automation
//				// scripts!!!! ");
//			}
//		} else {
//			// default
//			this.browser = "chrome";
//			this.env = "uat";
//			this.host = "localhost";
//			this.portNo = "8090";
//
//			setBrowserName(this.browser);
//			ChromeOptions chOptions = new ChromeOptions();
//			chOptions.addArguments("--start-maximized");
//			chOptions.addArguments("--ignore-certificate-errors");
//			chOptions.addArguments("--disable-popup-blocking");
//			chOptions.addArguments("--incognito");
//
//			hub_url = "http://" + this.host + ":" + this.portNo + "/wd/hub";
//
//			remoteDriver = new RemoteWebDriver(new URL(hub_url), chOptions);
//			setRemoteWebDriver(remoteDriver);
//			getRemoteWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
//			utils.log().info(getBrowserName() + " RemoteWebDriver has been initialized");
//			launchApp(this.env);
//		}
//
//	} catch (Exception e) {utils.log().fatal("Unable to initialize RemoteWebdriver for " + getBrowserName() + "...... ABORTING !!!\n" + e.toString());
//	}
//}