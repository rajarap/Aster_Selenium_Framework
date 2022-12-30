package testUtils;

import enums.user.UserRolesEnum;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.LoadPropertiesFileUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static constants.GeneralConstants.*;

/**
 * Base class for Testing containing the common methods for preparation
 * of the environment, url, browser, screenshots, users
 */
public class BaseTest {
    public static RemoteWebDriver driver;
    public static WiniumDriverService service;
    static WiniumDriver winDriver = null;
    static DesktopOptions options;
    protected static String pingIdPassCode = null;
    public static String testUser;

    Properties properties;

    /**
     * Method for getting the browser specified in the
     * properties file and then initializing it
     *
     * @return returns the instance of the webDriver
     */
    public WebDriver initializeDriver() throws MalformedURLException {
        properties = LoadPropertiesFileUtils.loadPropertiesFile(DATA_PROPERTIES_FILE_NAME);
        String grid = properties.getProperty("grid");
        String browserName = properties.getProperty("browser");

        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            if (grid.equals("false")) {
               // ChromeOptions options = new ChromeOptions();
               // options.addArguments("--incognito");

                
                ChromeOptions opt = new ChromeOptions();
		
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
               
               // opt.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
				opt.addArguments("--start-maximized");
				opt.addArguments("--ignore-certificate-errors");
				opt.addArguments("--disable-popup-blocking");
				opt.addArguments("--deny-permission-prompts");
				opt.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
				opt.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);

				opt.setExperimentalOption("prefs", prefs);
				desiredCapabilities.setBrowserName("chrome");
	            desiredCapabilities.setPlatform(Platform.WINDOWS);
                desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new ChromeDriver(desiredCapabilities);
            } else {
                ChromeOptions options = new ChromeOptions();
               // options.addArguments("--incognito");

                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setBrowserName("chrome");
                desiredCapabilities.setPlatform(Platform.WINDOWS);
                options.setExperimentalOption("excludeSwitches",Arrays.asList("disable-popup-blocking"));
                desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
            }
        }

        if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            if (grid.equals("false")) {
                driver = new FirefoxDriver();
            } else {
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setBrowserName("firefox");
                desiredCapabilities.setPlatform(Platform.WINDOWS);
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
            }
        }

        if (browserName.equals("ie")) {
            WebDriverManager.edgedriver().setup();
            if (grid.equals("false")) {
                driver = new EdgeDriver();
            } else {
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setBrowserName("edge");
                desiredCapabilities.setPlatform(Platform.WINDOWS);
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
            }
        }
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Method to get the path for storing the reports and screenshots
     *
     * @param testCaseName the name of the executed testcase to store
     * @param driver       the instance of the webDriver
     * @return return the path as a string
     * @throws IOException
     */
    public String getScreenshotPath(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String currentDate = new SimpleDateFormat(DATE_FORMAT_PATTERN).format(new Date());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String path = System.getProperty("user.dir") + "\\reports\\" + currentDate + "\\" + testCaseName + formatter.format(date) + ".png";
        File screenshotFile = new File(path);
        FileUtils.copyFile(source, screenshotFile);
        return path;
    }

    @BeforeTest
    @Parameters({"pipeline","testUser"})
    public static void getPingIdToken(@Optional String pipeline,@Optional String userForTest) throws IOException {

        if(!(pipeline == null))
        if (pipeline.equalsIgnoreCase("false")) {

            String pingIdPin = LoadPropertiesFileUtils.getItemFromProperties(CREDENTIALS_DATA_PROPERTIES_FILE_NAME, "pingIdPin");
            String winiumDriverPath = System.getProperty("user.dir") + "\\resources\\Winium.Desktop.Driver.exe";

            service = new WiniumDriverService.Builder().usingDriverExecutable(new File(winiumDriverPath)).usingPort(9999).buildDesktopService();
            service.start();

            options = new DesktopOptions();
            options.setApplicationPath("C:\\Program Files (x86)\\Ping Identity\\PingID\\PingID.exe");
            options.setDebugConnectToRunningApp(false);
            options.setLaunchDelay(2);
            winDriver = new WiniumDriver(new URL("http://localhost:9999"), options);

            String parentWindowHandle = winDriver.getWindowHandle();
            System.out.println("parentWindowHandle : " + parentWindowHandle);
            try {
                Thread.sleep(5000);
                var winFinder = winDriver.findElement(By.name("PingID"));
                var pinTextBox = winDriver.findElement(By.name("Enter PIN"));

                String pingWindowHandle = "";
                if (pinTextBox.isDisplayed()) {
                    System.out.println("PingId launched and displayed");
                    pingWindowHandle = winDriver.getWindowHandle();
                    System.out.println("pingWindowHandle : " + pingWindowHandle);
                    winDriver.switchTo().window(pingWindowHandle);
                }
                winFinder.click();
                pinTextBox.click();
                Thread.sleep(2000);
                pinTextBox.click();
                pinTextBox.sendKeys(pingIdPin);
                Thread.sleep(2000);
                var nextButton = winDriver.findElement(By.name("Next"));
                var refreshButton = winDriver.findElement(By.name("Refresh"));
                var copyButton = winDriver.findElement(By.name("Copy"));

                nextButton.click();
                Thread.sleep(2000);
                refreshButton.click();
                refreshButton.click();
                refreshButton.click();
                Thread.sleep(2000);
                copyButton.click();
                Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
                // Get data stored in the clipboard that is in the form of a string (text)
                pingIdPassCode = c.getData(DataFlavor.stringFlavor).toString();
                System.out.println(pingIdPassCode);

                winDriver.switchTo().window(pingWindowHandle).quit();

            } catch (IOException | InterruptedException | UnsupportedFlavorException e) {
                System.out.println("Exception while starting WINIUM service");
                e.printStackTrace();
            }
        }
        if (userForTest == null)
            testUser = "PIPELINE";
        else
            testUser = userForTest;
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {

        if (service != null)
            service.stop();
        if (driver != null)
            driver.quit();
    }

    /**
     * Method to initialize user depending on the enum provided
     *
     * @param userRole user role enum
     * @return initialized TestingUser
     */
    public TestingUser bringOnTheScene(UserRolesEnum userRole) {
        return new TestingUser(userRole.name());
    }

    /**
     * capture Screenshot method at suite level
     *
     * @param testCaseName
     * @return
     * @throws IOException
     */
    public String captureScreen(String testCaseName) throws IOException {
        String currentDate = new SimpleDateFormat(DATE_FORMAT_PATTERN).format(new Date());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\reports\\" + currentDate + "\\" + testCaseName + "\\" + formatter.format(date) + ".png";
        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (Exception e) {
            e.getMessage();
        }
        return destination;

    }
}
