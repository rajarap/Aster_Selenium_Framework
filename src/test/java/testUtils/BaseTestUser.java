package testUtils;

import org.openqa.selenium.WebDriver;

import pageObjects.Aster.HomePage;
import utils.LoadPropertiesFileUtils;

public class BaseTestUser extends BaseTest {

    public WebDriver driver;

    /**
     * This method invokes getUrlFromProperties() from BaseTest class
     * providing the baseUrlUser property as a parameter.
     *
     * @return returns the result of combining the url as a string
     */
    public String getUrlFromPropertiesUser() {
        return LoadPropertiesFileUtils.getUrlFromProperties();
    }

    /**
     * This method is used to sign in with user's credentials
     */
  
}
