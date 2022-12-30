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
    
    @FindBy(xpath="//div[@class='menuButton']/following-sibling::div[@class='logo']")
    private WebElement logo;
    
    @FindBy(xpath="//div[contains(@class,'searchBox')]//input[@type='text']")
    private WebElement globalSearchbox;
    
    @FindBy(xpath="//div[@class='searchMenu']//div[text()='No Results Found']")
    private WebElement NoResults;
    
    
    @FindBy(xpath="//div[@class='icGlobalSearch icCrossSearch']")
    private WebElement Cleartext;
    
    
    @FindBy(xpath="//button[text()='Login/Register']")
    private WebElement LoginRegister;
    
  
    @FindBy(xpath="//button[contains(@class,'btn btn-primary btn-md')][text()='Pharmacy']")
    private WebElement Pharmacy;
    
    @FindBy(xpath="//button[contains(@class,'btn btn-primary btn-md')][text()='Consult a Doctor']")
    private WebElement consultaDoctor;
    
  
    
  //div[@class='icGlobalSearch icCrossSearch']
  //ul[@role='listbox']/li[@role='option']
  //div[@class='searchMenu']//div[text()='No Results Found']
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.waitForDocumentReadyState(driver);
    }

  

    /**
     * This method is used to facilitate the login process
     * (it ends before Stay Signed In confirmation)
     *
     * @param email    The username email
     * @param password The password
     * @return 
     * @throws InterruptedException 
     */
    public boolean VerifyHomepageFields(String fieldValue) throws InterruptedException {
    	try
    	{
    	Thread.sleep(6000);
    	WebElement ele =driver.findElement(By.xpath("//h2[text()='"+fieldValue+"']"));
    	
    	 WaitUtils.waitForDocumentReadyState(driver);
    	 WaitUtils.isElementDisplayed(ele, driver);
    	 return true;
    	}
    	catch(org.openqa.selenium.NoSuchElementException e)
    	{
    		return false;
    	}
    }
    public void ClickonViewAll(String fieldValue) throws InterruptedException {
    	Thread.sleep(6000);
    	WebElement ele =driver.findElement(By.xpath("//h2[text()='"+fieldValue+"']/following-sibling::a[text()='View All']"));
    	
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
    	 if(NoResults.isDisplayed())
    	 {
    		 Val="";
    		return Val; 
    	 }
    	 }catch(org.openqa.selenium.NoSuchElementException e) 
    	 {
    		 List<WebElement> searchlist=driver.findElements(By.xpath("//ul[@role='listbox']/li[@role='option']"));
    		 int count=searchlist.size();
    		 for(int i=1;i<=count;i++)
    		 {
    			
    			Val=getSearchtext(i);
    			if(Val.contains(SearchVal))
    			{
    				
    				break;
    			}
    		 }
    	
    	 }
    	 return Val;
    
    }

    public String getSearchtext(int i) throws InterruptedException
	{
		
		Thread.sleep(6000);
		WaitUtils.waitForDocumentReadyState(driver);
		//WaitUtils.isElementDisplayed(AsterClinics, driver);
		 WebElement ele =driver.findElement(By.xpath("(//ul[@role='listbox']/li[@role='option'])["+i+"]"));
		WaitUtils.isElementDisplayed(ele, driver);
		String searchvalues=ele.getText();
		return searchvalues;
		
	}
   
    public boolean clickoncrossicon() throws InterruptedException
	{
		try {
			Thread.sleep(6000);
			WaitUtils.waitForDocumentReadyState(driver);
			if(Cleartext.isDisplayed())
			{
				WebElementUtils.mouseClick(Cleartext, driver);
			}
			//ViewAllforClinicsDoctors.isDisplayed();
		//	ViewAllforClinicsDoctors.click();
	        return true;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	}
    public boolean verifyLoginRegister() throws InterruptedException
   	{
   		try {
   			Thread.sleep(6000);
   			WaitUtils.waitForDocumentReadyState(driver);
   			LoginRegister.isDisplayed();
   		  return true;
   	       
   	    } catch (org.openqa.selenium.NoSuchElementException e) {
   	        return false;
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
}
