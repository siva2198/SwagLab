package ConfigurationHelper.DriverFactory;


import ConfigurationHelper.Utilites.ConfigurationPropertiesReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.Properties;

public class BaseBrowserConfiguration {
    public WebDriver driver;
    public Properties properties;
    public ConfigurationPropertiesReader configReader;
    private static final Logger log = LogManager.getLogger(BaseBrowserConfiguration.class);
    public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();


    public BaseBrowserConfiguration() {
        log.info("Base Browser Configuration");
        configReader = new ConfigurationPropertiesReader();
        properties = configReader.loadProperties();
    }

    public static WebDriver initializeDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            tldriver.set(new ChromeDriver());
        } else if (browserName.equalsIgnoreCase("firefox")) {
            tldriver.set(new FirefoxDriver());
        } else if (browserName.equalsIgnoreCase("edge")) {
            tldriver.set(new EdgeDriver());
        } else if (browserName.equalsIgnoreCase("safari")) {
            tldriver.set(new SafariDriver());
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browserName);
        }

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
        return getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return tldriver.get();
    }

    public void getLoginURL() {
        try {
            log.info("{}trigger client base URL", this.getClass().getName());
            String url = properties.getProperty("loginURL");
            getDriver().get(url);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}


