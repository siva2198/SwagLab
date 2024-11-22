package Hooks;
import ConfigurationHelper.DriverFactory.BaseBrowserConfiguration;
import ConfigurationHelper.Utilites.ExtentReportUtils;
import ConfigurationHelper.Utilites.ConfigurationPropertiesReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import java.util.Properties;

public class ApplicationHooks {
    private WebDriver driver;
    private ConfigurationPropertiesReader configReader;
    private Properties properties;
    private ExtentReports extent;
    private ExtentTest test;

    @Before(order = 0)
    public void getProperty() {
        configReader = new ConfigurationPropertiesReader();
        properties = configReader.loadProperties();
    }

    @Before(order = 1)
    public void setUp(Scenario scenario) {
        // Initialize Extent Reports
        extent = ExtentReportUtils.getReportObject();
        test = extent.createTest(scenario.getName());

        // Log scenario start
        test.info("Starting scenario: " + scenario.getName());

        // Initialize WebDriver
        String browser = properties.getProperty("browser");
        driver = BaseBrowserConfiguration.initializeDriver(browser);
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Capture screenshot
            byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            test.fail("Scenario Failed");
        } else {
            test.pass("Scenario Passed");
        }
    }
    // Quit WebDriver
    @After(order = 0)
    public void quit(){
        driver.quit();

        // Flush Extent Reports
        extent.flush();
    }
}
