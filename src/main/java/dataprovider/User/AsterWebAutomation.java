package dataprovider.User;

import org.testng.annotations.DataProvider;
import utils.DataProviderUtils;

public class AsterWebAutomation {

	 @DataProvider(name = "HomePage")
	    public static Object[] getHomepageListValues() {
	       // Object[] arrObj = DataProviderUtils.getExcelData("./AsterWebAutomation.xlsx", "HomePage");
	    	return new Object [] { "Popular Categories in Pharmacy","Doctors By Specialities","Bestseller Of The Month","Trending Pharmacy Categories","Near You","Shop By Brands"}; 
	    			//;
	    }
	 @DataProvider(name = "searchValues")
	    public static Object[] getSearchValues() {
	       // Object[] arrObj = DataProviderUtils.getExcelData("./AsterWebAutomation.xlsx", "HomePage");
	    	return new Object [] { "Udaya","Udan","Pana"}; 
	    			//;
	    }

	 @DataProvider(name = "Pharmacy")
	    public static Object[] getPharmacyCategory() {
	       // Object[] arrObj = DataProviderUtils.getExcelData("./AsterWebAutomation.xlsx", "HomePage");
	    	return new Object [] { "Top Deals for You","Bestseller Of The Month"}; 
	    			//;
	    }
	 
	 @DataProvider(name = "specialties")
	    public static Object[] specialtyName() {
	       // Object[] arrObj = DataProviderUtils.getExcelData("./AsterWebAutomation.xlsx", "HomePage");
	    	return new Object [] { "General Medicine"}; 
	    			//;
	    }
	 
	 @DataProvider(name = "PharmacyPage")
	    public static Object[] getPharmacypageListValues() {
	       // Object[] arrObj = DataProviderUtils.getExcelData("./AsterWebAutomation.xlsx", "HomePage");
	    	return new Object [] { "Popular Categories","Shop By Brands","Top Deals For You","New Arrivals","Featured Categories","Essential Products"}; 
	    			//;
	    }
	 @DataProvider(name = "PharmacyPageCategory")
	    public static Object[] getPharmacypagecategory() {
	       // Object[] arrObj = DataProviderUtils.getExcelData("./AsterWebAutomation.xlsx", "HomePage");
	    	return new Object [] {"Top Deals For You","New Arrivals","Essential Products"}; 
	    			//;
	    }

//	 /,"Top Deals for You","Bestseller Of The Month"
	
}
