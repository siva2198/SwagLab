package Pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.logging.LogManager;

// page_url = about:blank
public class ProductPage {
    WebDriver driver;
        public ProductPage(WebDriver driver) {
            this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//div[@class='app_logo']")
    WebElement logo;

    @FindBy(xpath = "")
    WebElement allProductName;

    public boolean getLogo() {
        return logo.isDisplayed();
    }
    public String getLogoText() {
        return logo.getText();
    }
    public void getAllProductName() {
        List<WebElement> productNameElements = driver.findElements(By.xpath("//div[@class='inventory_item_name ']"));
        List<WebElement> priceElements = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
       if (productNameElements.size() != priceElements.size()) {
           System.out.println("Does not have the same size");
       }
       else {
           for (int i = 0; i < productNameElements.size(); i++) {
               System.out.println(productNameElements.get(i).getText() + " -- " + priceElements.get(i).getText());
           }
       }
    }
}