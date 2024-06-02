package AbstractComponent;

import BaseConfig.BaseConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent extends BaseConfig {
    WebDriver driver;
    WebDriverWait wait;
    public AbstractComponent(WebDriver driver){
        BaseConfig.driver = driver;
        PageFactory.initElements(driver, this);
    }public void waitUntilElementIsVisible(WebElement locator){
         wait.until(ExpectedConditions.visibilityOf(locator));
    }


}
