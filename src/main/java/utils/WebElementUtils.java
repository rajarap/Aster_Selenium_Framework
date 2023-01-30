package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class containing utility methods for operating with WebElements
 */
public class WebElementUtils {
    private static List<WebElement> elements;

    public static void clickElementWithTextFromListOfElements(String text, List<WebElement> listOfElements) {
        for (WebElement element : listOfElements) {
            if (element.getText().equals(text)) {
                element.click();
                break;
            }
        }
    }

    /**
     * Method to perform Async Click Action
     */
    public static void JSClick(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        //executor.executeScript("arguments[0].click();", element);
        ((JavascriptExecutor) driver).executeAsyncScript("arguments[0].click();", element);
    }

    /**
     * Method to get webElement with desired text from a list of webElements
     *
     * @param text           the text of the webElement
     * @param listOfElements the list of the webElements
     * @return return the filtered element as webElement
     */
    public static WebElement getElementWithTextFromListOfElements(String text, List<WebElement> listOfElements) {
        WebElement elementResult = null;
        for (WebElement element : listOfElements) {
            if (element.getText().equals(text)) {
                elementResult = element;
                break;
            }
        }
        return elementResult;
    }

    public static WebElement getRandomElementFromListOfElements(List<WebElement> listOfElements) {
        WebElement elementResult = null;
        int maxElements = listOfElements.size();
        Random random = new Random();
        int randomElement = random.nextInt(maxElements);
        elementResult = listOfElements.get(randomElement);
        return elementResult;
    }

    /**
     * Method to get webElement with desired text from a list of webElements
     *
     * @param title          the text of the webElement
     * @param listOfElements the list of the webElements
     * @return return the filtered element as webElement
     */
    public static WebElement getElementWithTitleFromListOfElements(String title, List<WebElement> listOfElements) {
        WebElement elementResult = null;
        for (WebElement element : listOfElements) {
            if (element.getAttribute("title").equals(title)) {
                elementResult = element;
                break;
            }
        }
        return elementResult;
    }

    /**
     * Method to scroll to the desired element visibility
     *
     * @param driver  the instance of the WebDriver
     * @param element the element to scroll to
     */
    public static void scrollElementIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Method to determine if a webelement has a specified attribute
     *
     * @param element       the webElement
     * @param attributeType the type of the searched attribute
     * @param attributeText the text of the attribute
     * @return return result if the webElement has the attribute
     */
    public static boolean isAttributePresentInWebElement(WebElement element, String attributeType, String attributeText) {
        return element.getAttribute(attributeType).contains(attributeText);
    }

    /**
     * Method to hover the mouse over a specified element
     *
     * @param element the webElement to mouse hover
     * @param driver  the webDriver instance
     * @return WebElement which is mouse hovered
     */
    public static WebElement mouseOverWebElement(WebElement element, WebDriver driver) {
        Actions a = new Actions(driver);
        a.moveToElement(element).build().perform();
        return element;
    }

    /**
     * Method to select the specified menu item
     *
     * @param menulist the string of menu item
     * @param driver   the webDriver instance
     */
    public static void SelectMenuItem(WebDriver driver, String menulist) {
        if (menulist.contains(",")) {
            var menu = menulist.split(",");
            for (var menuitem : menu) {
                WaitUtils.waitForVisibleElement(By.xpath("//div[contains(text(),'" + menuitem + "')]"), driver);
                WebElement selectmenu = driver.findElement(By.xpath("//div[contains(text(),'" + menuitem + "')]"));
                WaitUtils.waitForVisibleElement(selectmenu, driver);
                selectmenu.click();
            }
        } else {
            WaitUtils.waitForVisibleElement(By.xpath("//div[contains(text(),'" + menulist + "')]"), driver);
            WebElement selectmenu = driver.findElement(By.xpath("//div[contains(text(),'" + menulist + "')]"));
            WaitUtils.waitForVisibleElement(selectmenu, driver);
            selectmenu.click();
        }
    }

    /**
     * Method to verify table column header titles
     *
     * @param driver           the webDriver instance
     * @param columnHeaderList the list of column headers
     * @return boolean value
     */
    public static boolean verifyColumnOrder(WebDriver driver, List<WebElement> pageHeaders, List<String> columnHeaderList) {

        List<String> texts = pageHeaders.stream().map(WebElement::getText).collect(Collectors.toList());
        String expectedHeaders = String.join(",", columnHeaderList);
        String visibleHeaders = String.join(",", texts);

        return expectedHeaders.equals(visibleHeaders);
    }

    /**
     * This Method is used to selectByVisibleText
     *
     * @param element
     * @param visibleText
     */
    public static void selectByVisibleText(WebElement element, String visibleText) {
        Select oSelect = new Select(element);
        oSelect.selectByVisibleText(visibleText);
    }

    /**
     * This Method is used to selectByValue
     *
     * @param element
     * @param value
     */
    public static void selectByValue(WebElement element, String value) {
        Select oSelect = new Select(element);
        oSelect.selectByValue(value);
    }

    /**
     * This Method is used to selectByIndex
     *
     * @param element
     * @param index
     */
    public static void selectByIndex(WebElement element, int index) {
        Select oSelect = new Select(element);
        oSelect.selectByIndex(index);
    }

    /**
     * This Method is used to Verify Select Options
     *
     * @param element
     * @param expectedList
     * @return
     */
    public static boolean checkOptions(WebElement element, String[] expectedList) {

        List<WebElement> options = element.findElements(By.xpath(".//option"));
        int size = 0;
        for (WebElement opt : options) {
            if (!opt.getText().equals(expectedList[size])) {
                return false;
            }
            size = size + 1;
        }
        return true;
    }

    /**
     * This Method is used to Verify Select Options
     *
     * @param element
     * @param expectedList
     * @return
     */
    public static boolean checkSelectOptions(WebElement element, String[] expectedList) {
        boolean result = false;
        Select options = new Select(element);
        List<WebElement> lists = options.getOptions();
        List<String> OriginalList = new ArrayList<String>();
        for (WebElement ele : lists) {
            OriginalList.add(ele.getText());
        }
        for (int i = 0; i < OriginalList.size(); i++) {
            if (expectedList[0].contains(OriginalList.get(i))) {
                if (i + 1 == OriginalList.size())
                    result = true;
            }
        }
        return result;
    }

    /**
     * Method to check the Select Option Lists wth Expected String[]
     *
     * @param element
     * @param expectedList
     * @param test
     * @return
     */
    public static boolean checkSelectOptions(WebElement element, String[] expectedList, ExtentTest test) {
        boolean result = false;
        Select options = new Select(element);
        List<WebElement> lists = options.getOptions();
        List<String> OriginalList = new ArrayList<String>();
        for (WebElement ele : lists) {
            OriginalList.add(ele.getText());
        }
        test.log(Status.INFO, "Actual list : " + OriginalList);

        for (int i = 0; i < OriginalList.size(); i++) {
            if (expectedList[i].contains(OriginalList.get(i))) {
                if (i + 1 == OriginalList.size())
                    result = true;
            }
        }
        test.log(Status.INFO, "Expected list : " + expectedList);
        return result;
    }

    /**
     * Method used to validate List of WebElements text with expected String[]
     *
     * @param elements
     * @param expectedList
     * @return
     */
    public static boolean checkListWebElements(List<WebElement> elements, String[] expectedList) {
        boolean result = false;
        List<String> OriginalList = new LinkedList<String>();
        for (WebElement ele : elements) {
            OriginalList.add(ele.getText());
        }
        for (int i = 0; i < OriginalList.size(); i++) {
            if (expectedList[i].contains(OriginalList.get(i))) {
                if (i + 1 == OriginalList.size())
                    result = true;
            }
        }
        return result;
    }

    /**
     * This Method is used to Perform Mouse Over Action
     * @param element
     */
    public static void mouseOver(WebElement element, WebDriver driver)
    {
        Actions act=new Actions(driver);
        act.moveToElement(element).click().perform();
    }

     /**
     * Method used to check the list of web elements with expected list
     *
     * @param elements
     * @param expectedList
     * @param test
     * @return
     */

     public static boolean checkListWebElements(List<WebElement> elements, String[] expectedList, ExtentTest test) {
         boolean result = false;
         List<String> OriginalList = new LinkedList<String>();
         for (WebElement ele : elements) {
             if (!ele.getText().toString().isEmpty())
                 OriginalList.add(ele.getText());
         }
         for (int i = 0; i < OriginalList.size(); i++) {
             if (OriginalList.get(i).contains(expectedList[i])) {
                 if (i + 1 == OriginalList.size())
                     result = true;
             }
         }
         if (result)
             test.log(Status.PASS, "Expected list : " + Arrays.deepToString(expectedList) + "matched with actual Web list data : " + OriginalList);
         else
             test.log(Status.FAIL, "Expected list : " + Arrays.deepToString(expectedList) + " not matched with actual Web list data : " + OriginalList);
         return result;
     }

    /**
     * method used to perform mouse click
     * @param element
     * @param driver
     */
    public static void mouseClick(WebElement element, WebDriver driver) {
        Actions act = new Actions(driver);
        act.moveToElement(element).click().perform();
    }

    /**
     * Method used to zoom out the selenium browser to 90 % using JavaScript
     *
     * @param driver
     */
    public static void zoomOut(WebDriver driver) {
        System.out.println("About to zoom out to 90 %");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.body.style.zoom = '0.9'");
    }

    /**
     * Method used to perform Click from Javascript executor
     *
     * @param element
     * @param driver
     * @return
     */
    public static boolean executeJavaScriptClick(WebElement element, WebDriver driver) {
        //  executeJavaScriptScrollIntoView(element,driver);
        mouseClick(element, driver);
        return true;
    }

    /**
     * method used to scroll the view of element using javascript
     *
     * @param element
     * @param driver
     * @return
     */
    public static boolean executeJavaScriptScrollIntoView(WebElement element, WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
        return true;
    }

    public static String getDefaultSelectedValue(WebElement element) {
        Select oSelect = new Select(element);
        return oSelect.getFirstSelectedOption().getText();
    }
    
    

}
