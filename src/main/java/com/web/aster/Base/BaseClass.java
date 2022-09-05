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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
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

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.web.aster.Utilities.TestUtils;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {
	
	public Properties properties = new Properties();
	TestUtils utils = new TestUtils();

	protected static ThreadLocal <WebDriver> webDriver = new ThreadLocal<WebDriver>();
	protected static ThreadLocal <String> browserName = new ThreadLocal<String>();
	protected static ThreadLocal <Properties> props = new ThreadLocal<Properties>();
	protected static ThreadLocal <String> dateTime = new ThreadLocal<String>();
	
	public DesiredCapabilities desiredCapabilities ;
	public WebDriver driver;
	
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
	public String[] row;
	
	
	private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
	
	public BaseClass() {
		
	}
	
 
	@SuppressWarnings("deprecation")
	@Parameters({"browserName", "environment"})
	@BeforeTest
	public void beforeTest(String browserName, String environment){
		this.browser = browserName;
		this.env = environment;
				
	
		try {
			setDateTime(utils.dateTime());
			setConfigProperties();
			
			
			switch(this.browser) {
			case "chrome":
				setBrowserName(this.browser);
				utils.log().info("Setting " + getBrowserName() + " Webdriver capabilities");
				ChromeOptions opt = new ChromeOptions();
				opt.addArguments("--start-maximized");
				opt.addArguments("--ignore-certificate-errors");
				opt.addArguments("--disable-popup-blocking");
				opt.addArguments("--incognito");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(opt);
				setDriver(driver);
				getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
				utils.log().info(getBrowserName() + " WebDriver has been initialized"); 
				launchApp(this.env);
				break;
			case "firefox":
				setBrowserName(this.browser);
				utils.log().info("Setting " + getBrowserName() + " Webdriver capabilities");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				setDriver(driver);
				//getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
				utils.log().info(getBrowserName() + " WebDriver has been initialized"); 
				launchApp(this.env);
				break;
			case "ie":
				setBrowserName(this.browser);
				utils.log().info("Setting " + getBrowserName() + " Webdriver capabilities");
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				setDriver(driver);
				//getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
				utils.log().info(getBrowserName() + " WebDriver has been initialized"); 
				launchApp(this.env);
				break;
			default:
				//utils.log().info("Please mention platform name to execute your automation scripts!!!! ");
			}
		}catch (Exception e) {
			  utils.log().fatal("Unable to initialize Webdriver for " + getBrowserName() + "...... ABORTING !!!\n" + e.toString());
			} 
	}

	@AfterTest
	public void afterTest()	{
		utils.log().info("Closing App...");
		closeApp();
		utils.log().info("Quitting Driver...");
		getDriver().quit();
	}
	
	
	 public void closeApp() {
		  getDriver().close();
	  }
	  
	  public void launchApp(String env) {
		  if(env.equalsIgnoreCase("qa")) {
			  getDriver().get(getProps().getProperty("qaURL"));
			  utils.log().info("Launching 1Aster - QA Environemnt");
		  }
		  
		  if(env.equalsIgnoreCase("uat")) {
			  getDriver().get(getProps().getProperty("uatURL"));
			  utils.log().info("Launching 1Aster - UAT Environemnt");
		  }
		  
		  if(env.equalsIgnoreCase("prod")) {
			  getDriver().get(getProps().getProperty("prodURL"));
			  utils.log().info("Launching 1Aster - PROD Environemnt");
		  }
	  }
	  
	  public void takeScreenshot(String methodName, ITestResult result) 
	  {
		  	System.setProperty("org.uncommons.reportng.escape-output", "false");
		  	String ssPath = "Screenshots" + File.separator + getBrowserName() + File.separator + utils.onlyDate();
			ssFile = new File(ssPath);
			if (!ssFile.exists()) 
			{
				ssFile.mkdirs();
			}
			File file  = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
			    try {
			        FileUtils.copyFile(file, new File(ssFile.getAbsolutePath() + File.separator + result.getTestClass().getRealClass().getSimpleName() + File.separator + methodName + ".jpg"));
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			    Reporter.log("<a href = " + ssFile.getAbsolutePath() + File.separator + result.getTestClass().getRealClass().getSimpleName() + File.separator + methodName + ".jpg" + ">screenshot</a>");
		}
	
	public void setConfigProperties()
	  {
			String propFileName = "config.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			setProps(properties);
	  }
	
	
	public WebDriver getDriver() { return webDriver.get(); }
	
	public void setDriver(WebDriver driver) { webDriver.set((WebDriver) driver); }
	
	public Properties getProperties() { return props.get(); }
	
	public void setProperties(Properties properties) { props.set(properties); }

	public String getDateTime() { return dateTime.get(); }
	  
	public void setDateTime(String dateTime2) { dateTime.set(dateTime2); }
	
	public String getBrowserName() { return browserName.get(); }
	  
	public void setBrowserName(String platform2) { browserName.set(platform2); }
	
	public Properties getProps() { return props.get(); }
	 
	public void setProps(Properties props2) { props.set(props2); }
	
	
	@AfterSuite
	public void afterSuite() 
	{
		utils.log().info("Execution Done....");
		
	}
  
	  public void waitForVisibility(WebElement e){
		  Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
		  .withTimeout(Duration.ofSeconds(30))
		  .pollingEvery(Duration.ofSeconds(5))
		  .ignoring(NoSuchElementException.class);
		  
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
		  if(e.isDisplayed()) {
			  return true;
		  } else {
			  return false;
		  }
	  }
	  
	  public void selectItemFromList(WebElement e, String countryCode){
		  click(e);
		  Select select = new Select(e);
		  List<WebElement> options = select.getOptions();
		  utils.log().info("Total Number of items availabe in dropdown box is : " + options.size() + " options");
		  
		  for (int i = 0; i <= options.size() ; i++) {
			  WebElement item = options.get(i);
				if (item.getText().equals(countryCode)) {
					click(item);
					break;
				}
		  }
		  
	  }
	  	  
		public int generateMobileNumber() {
			Random random = new Random();
			int number = random.nextInt(999999999);
			return number;
		}
		
		public String getCountryCode() {
			String[] arr={"+971", "+91"};
	      	Random r=new Random();        
	      	int randomNumber=r.nextInt(arr.length);
	      	return(arr[randomNumber]);
		}
}
