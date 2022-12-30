package testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

import static constants.GeneralConstants.DATE_FORMAT_PATTERN;
import static constants.GeneralConstants.TIME_FORMAT_PATTERN;

/**
 * Class for creating directories for setting and storing reports
 */
public class ReportsUtil {
    static ExtentReports extentReports;
    private static String logFolderFullPath;
    private static String reportFileName;

    public static ExtentReports setReports() {
        String currentDate = new SimpleDateFormat(DATE_FORMAT_PATTERN).format(new Date());
        String fileSuffix = new SimpleDateFormat(TIME_FORMAT_PATTERN).format(new Date());
        String path = System.getProperty("user.dir") + "\\reports\\" + currentDate + "\\extentReportFile-" + fileSuffix + ".html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("Test Results");
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        return extentReports;
    }

    public static ExtentTest getNewTest(String testCaseName) {
        return extentReports.createTest(testCaseName);
    }

    public static String returnLogFolderFullPath() {
        return logFolderFullPath;
    }

    public static String returnReportFileName() {
        return reportFileName;
    }
}
