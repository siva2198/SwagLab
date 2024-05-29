package BaseConfig;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;


public class BaseConfig {
    public static WebDriver driver;
    public static final String BROWSER = System.getProperty("browser", "Chrome");
    private static final Logger log = LogManager.getLogger(BaseConfig.class);
    public static Properties properties;
    WebDriverWait wait;

    @BeforeSuite(alwaysRun = true)
    public void setUpBrowser() throws Exception {
        if (BROWSER.equals("Chrome")) {
            log.info("Chrome driver got initialized");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (BROWSER.equals("Firefox")) {
            log.info("Firefox driver got initialized");
            driver = new FirefoxDriver();
        } else {
            throw new Exception("Browser not supported");
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        InputStream config = Files.newInputStream(Paths.get(System.getProperty("user.dir") + "/src/main/resources/global.properties"));
        properties = new Properties();
        properties.load(config);
    }
    public static WebDriver getDriver() {
        return driver;
    }

    public void loadURL() {
        String baseURL = properties.getProperty("baseURL");
        if (baseURL == null) {
            log.error("Base URL not set {} is not properties file", properties.getProperty("baseURL"));
            throw new IllegalStateException("Base URL not set is not properties file" + properties.getProperty("baseURL"));
        }
        log.info("Base URL: {}", baseURL);
        driver.get(baseURL);

    }

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        File source = ((TakesScreenshot) BaseConfig.driver).getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(new Date());
        File file = new File(System.getProperty("user.dir") +"//screenshots//" + " " + testCaseName + "_" + timeStamp + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") +"//screenshots//" + " " + testCaseName + "_" + timeStamp + ".png";
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownBrowser() {
        log.info("Closing Browser");
        driver.quit();
    }
}

