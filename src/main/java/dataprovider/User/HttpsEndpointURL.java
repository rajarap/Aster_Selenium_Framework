package dataprovider.User;

import org.testng.annotations.DataProvider;
import utils.DataProviderUtils;

public class HttpsEndpointURL {

	 @DataProvider(name = "EndPointURL")
	    public static Object[] getHomepageListValues() {
	       // Object[] arrObj = DataProviderUtils.getExcelData("./AsterWebAutomation.xlsx", "HomePage");
	    	return new Object [] { "https://search-service-uae.myaster.com/api/category/2599?zone=SDU12,Dz13&page=0&pageSize=52&sort=relevance&q=categoryPath%3ACold%20%26%20Flu%20Remedies"}; 
	    			//;
	    }

	
	 
	
}
