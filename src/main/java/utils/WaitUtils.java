package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Class containing utility methods for selenium waiting
 */
public final class WaitUtils {
    public static final int DEFAULT_WAIT_TIME_SECONDS =60;
    private static final int LONG_WAIT_TIME = 60; // in seconds
    private static final int LONG_WAIT_TIME_MINUTES = 120; // in seconds
    private static final int WAIT_TIME = 10; // in seconds
    private static final int POLL_FREQUENCY_INTERVAL = 2; // in milli-seconds
    private static final List<Class<? extends Throwable>> IGNORE_EXCEPTIONS = Arrays.asList(NotFoundException.class,
            NoSuchElementException.class,
            StaleElementReferenceException.class);
    static Logger log = LogManager.getLogger(WaitUtils.class.getName());

    /**
     * Waits for the Javascript document ready state to be returned
     *
     * @param driver The WebDriver instance
     */
    public static void waitForDocumentReadyState(WebDriver driver) {

        ExpectedCondition<Boolean> pageLoadCondition =
                d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete");

        createWaitInstance(driver).until(pageLoadCondition);
    }

    /**
     * Waits for an element to appear
     *
     * @param element The WebElement instance to wait for
     * @param driver  The WebDriver instance
     * @return The located WebElement instance
     */
    public static WebElement waitForVisibleElement(WebElement element, WebDriver driver) {
        System.out.println("visibility: YES  for" + element);
        return waitForVisibleElement(element, driver, LONG_WAIT_TIME, POLL_FREQUENCY_INTERVAL);
    }

    /**
     * Waits for an element attribute to contain
     *
     * @param element The WebElement instance to wait for
     * @param driver  The WebDriver instance
     * @return The located WebElement instance
     */
    public static WebElement waitForElementAttributeContains(WebElement element, String attribute,
                                                             String value, WebDriver driver) {
        WebElement webElement = null;
        WebDriverWait wait = createWaitInstance(driver, LONG_WAIT_TIME, POLL_FREQUENCY_INTERVAL);
        boolean result = wait.until(ExpectedConditions.attributeContains(element, attribute, value));
        if (result) {
            webElement = element;
        }
        return webElement;
    }

    /**
     * Waits for an element attribute not to be empty
     *
     * @param element The WebElement instance to wait for
     * @param driver  The WebDriver instance
     * @return The located WebElement instance
     */
    public static WebElement waitForElementToBeSelected(WebElement element, WebDriver driver) {
        WebElement webElement = null;
        WebDriverWait wait = createWaitInstance(driver, LONG_WAIT_TIME, POLL_FREQUENCY_INTERVAL);
        boolean result = wait.until(ExpectedConditions.elementSelectionStateToBe(element, true));
        if (result) {
            webElement = element;
        }
        return webElement;
    }

    /**
     * Waits for an element attribute not to be empty
     *
     * @param element The WebElement instance to wait for
     * @param driver  The WebDriver instance
     * @return The located WebElement instance
     */
    public static WebElement waitForElementAttributeNotEmpty(WebElement element, String attribute, WebDriver driver) {
        WebElement webElement = null;
        WebDriverWait wait = createWaitInstance(driver, LONG_WAIT_TIME, POLL_FREQUENCY_INTERVAL);
        boolean result = wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
        if (result) {
            webElement = element;
        }
        return webElement;
    }

    /**
     * Waits for an element to appear
     *
     * @param element The WebElement instance to wait for
     * @param driver  The WebDriver instance
     * @return The located WebElement instance
     */
    public static WebElement waitForElementAttributeNotContains(WebElement element, String attribute,
                                                                String value, WebDriver driver) {
        WebElement webElement = null;
        WebDriverWait wait = createWaitInstance(driver, LONG_WAIT_TIME, POLL_FREQUENCY_INTERVAL);
        boolean result = wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(element, attribute, value)));
        if (result) {
            webElement = element;
        }
        return webElement;
    }

    /**
     * Waits for all in list to appear
     *
     * @param elements The list of Webelements to wait for
     * @param driver   The WebDriver instance
     * @return The located WebElement instance
     */
    public static List<WebElement> waitForListOfElementsToBeVisible(List<WebElement> elements, WebDriver driver) {
        WebDriverWait wait = createWaitInstance(driver, LONG_WAIT_TIME, POLL_FREQUENCY_INTERVAL);
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    /**
     * Waits for the WebElement to become visible
     *
     * @param element          The WebElement instance to wait for
     * @param driver           The WebDriver instance
     * @param timeOutInSeconds The timeout to wait for
     * @param pollIntervalInMs The polling interval in ms
     * @return The located WebElement instance
     */
    public static WebElement waitForVisibleElement(WebElement element, WebDriver driver, long timeOutInSeconds,
                                                   long pollIntervalInMs) {
        WebDriverWait wait = createWaitInstance(driver, timeOutInSeconds, pollIntervalInMs);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for the WebElement to become visible. WebElement is located by Locator
     *
     * @param locator CSS locator as String
     * @param driver  The WebDriver instance
     * @return The located WebElement instance
     */
    public static WebElement waitForVisibleElement(String locator, WebDriver driver) {
        WebDriverWait wait = createWaitInstance(driver, LONG_WAIT_TIME, POLL_FREQUENCY_INTERVAL);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
    }

    /**
     * Waits for the WebElement to become visible. WebElement is located by Xpath
     *
     * @param xpath  xpath as By
     * @param driver The WebDriver instance
     * @return The located WebElement instance
     */
    public static WebElement waitForVisibleElement(By xpath, WebDriver driver) {
        WebDriverWait wait = createWaitInstance(driver, LONG_WAIT_TIME, POLL_FREQUENCY_INTERVAL);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }

    /**
     * Waits for the WebElement to become visible. WebElement is located by Xpath
     *
     * @param xpath  xpath as By
     * @param driver The WebDriver instance
     * @return The located WebElement instance
     */
    public static List<WebElement> waitForVisibleElements(By xpath, WebDriver driver) {
        WebDriverWait wait = createWaitInstance(driver, LONG_WAIT_TIME, POLL_FREQUENCY_INTERVAL);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(xpath));
    }

    /**
     * Waits for the presence of Element
     *
     * @param element The WebElement instance to wait for
     * @param driver  The WebDriver instance
     * @return The located WebElement instance
     */
    public static WebElement waitForPresenceOfElement(WebElement element, WebDriver driver) {
        WebDriverWait wait = createWaitInstance(driver, LONG_WAIT_TIME, POLL_FREQUENCY_INTERVAL);
        return wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
    }

    /**
     * Waits for the presence of List of Elements
     *
     * @param elements The WebElement instance to wait for
     * @param driver   The WebDriver instance
     * @return The located WebElement instance
     */
    public static List<WebElement> waitForPresenceOfElements(List<WebElement> elements, WebDriver driver) {
        WebDriverWait wait = createWaitInstance(driver, LONG_WAIT_TIME, POLL_FREQUENCY_INTERVAL);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) elements));
    }

    public static void waitForAjaxToFinish(WebDriver driver) {
        WebDriverWait wait = createWaitInstance(driver);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0").equals(true);
            }

        });
    }

    public static boolean isElementDisplayed(WebElement element, WebDriver driver) {
        waitForVisibleElement(element, driver);
        WebDriverWait wait = createWaitInstance(driver, DEFAULT_WAIT_TIME_SECONDS, POLL_FREQUENCY_INTERVAL);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            System.out.println("Element is displayed within timeframe: " + element);
            return true;
        } catch (Throwable t) {
            System.out.println("Could not find element within timeframe: " + element);
            return false;
        }
    }

    public static void waitForListOfElementsSizeToBe(String locator, int count, WebDriver driver) {

        WebDriverWait wait = createWaitInstance(driver, DEFAULT_WAIT_TIME_SECONDS, POLL_FREQUENCY_INTERVAL);

        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(locator), count));
    }


    /**
     * Waits for the WebElement to become invisible
     *
     * @param driver The WebDriver instance
     * @return The invisible WebElement instance
     */
    public static void waitForInvisibilityOfElement(WebElement element, WebDriver driver) {
        if (isElementPresent(By.cssSelector("span>svg[class*='MuiCircularProgress-svg']"), driver)) {
            WebDriverWait wait = createWaitInstanceLong(driver);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span>svg[class*='MuiCircularProgress-svg']")));
            System.out.println("visibility: NO " +element);
        }else {
            System.out.println("element not visible");
        }
    }

    public static boolean isElementPresent(By xpath, WebDriver driver) {
        try {
            WebElement element = driver.findElement(xpath);
            if (element == null)
                return false;
            else
                return true;
        } catch (NoSuchElementException exception) {
            System.out.println(exception);
            return false;
        }
    }

    /**
     * Waits for an element to become interactable
     *
     * @param element The WebElement instance to wait for
     * @param driver  The WebDriver instance
     * @return The located WebElement instance
     */
    public static WebElement waitForClickableElement(WebElement element, WebDriver driver) {

        WebDriverWait wait = createWaitInstance(driver);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Removes the implicit wait time
     *
     * @param driver the WebDriver instance to affect
     */
    public static void removeImplicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    /**
     * Re-instates the implicit wait time after it has been de-activated
     *
     * @param driver the WebDriver instance to affect
     */
    public static void restoreImplicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIME_SECONDS, TimeUnit.SECONDS);
    }

    /**
     * Creates an instance of the WebDriverWait class
     *
     * @param driver The WebDriver instance source object
     * @return An instance of {@link WebDriverWait} bound to the source WebDriver object
     */
    private static WebDriverWait createWaitInstance(WebDriver driver) {
        return createWaitInstance(driver, LONG_WAIT_TIME, POLL_FREQUENCY_INTERVAL);
    }

    private static WebDriverWait createWaitInstanceLong(WebDriver driver) {
        return createWaitInstance(driver, LONG_WAIT_TIME_MINUTES, POLL_FREQUENCY_INTERVAL);
    }
    /**
     * Creates an instance of the WebDriverWait class
     *
     * @param driver           The WebDriver instance source object
     * @param timeOutInSeconds The timeout to set (in seconds)
     * @param pollIntervalInMs The interval to poll (in milliseconds)
     * @return An instance of {@link WebDriverWait} bound to the source WebDriver object
     */
    private static WebDriverWait createWaitInstance(WebDriver driver, long timeOutInSeconds, long pollIntervalInMs) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds, pollIntervalInMs);
        wait.ignoreAll(IGNORE_EXCEPTIONS);
        wait.withMessage("The specified element cannot be located, timed out after " + timeOutInSeconds + " seconds");
        return wait;
    }
}
