package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static constants.GeneralConstants.DATA_PROPERTIES_FILE_NAME;

public class LoadPropertiesFileUtils {
    public static Properties prop = new Properties();

    /**
     * Method to load the Data Properties configuration file
     *
     * @return Properties object
     */
    public static Properties loadPropertiesFile(String fileName) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }

    /**
     * Method to extract property value from properties file
     *
     * @return returns the property value
     */
    public static String getItemFromProperties(String fileName, String name) {
        Properties properties = loadPropertiesFile(fileName);

        String urlResult = properties.getProperty(name);
        return urlResult;
    }

    /**
     * Method to extract and combine the different parts of the url
     * depending on the baseUrl provided as parameter.
     *
     * @return returns the result of combining the url as a string
     */
    public static String getUrlFromProperties() {
        String urlResult = "";
        LoadPropertiesFileUtils.loadPropertiesFile(DATA_PROPERTIES_FILE_NAME);
       // String envValue = System.getProperty("env").replace('"', ' ');
        String envValue =prop.getProperty("environmnet");
        if (envValue.equals("PREPROD")) {
            urlResult = prop.getProperty("baseUrlPREPROD");
        } else if (envValue.equals("QA")) {
            urlResult = prop.getProperty("baseUrlQA");
        } else if (envValue.equals("UAT")) {
            urlResult = prop.getProperty("baseUrlUAT");
        } else if (envValue.equals("PROD")) {
            urlResult = prop.getProperty("baseUrlPROD");
        }
    
        return urlResult;
    }

    public static String getenvironment() {
        
        LoadPropertiesFileUtils.loadPropertiesFile(DATA_PROPERTIES_FILE_NAME);
       // String envValue = System.getProperty("env").replace('"', ' ');
        String envValue =prop.getProperty("environmnet");
       
    
        return envValue;
    }

}
