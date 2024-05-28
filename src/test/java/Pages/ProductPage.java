package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = about:blank
public class ProductPage {
    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//div[@class='app_logo']")
    WebElement logo;

    public boolean getLogo() {
        return logo.isDisplayed();
    }
    public String getLogoText() {
        return logo.getText();
    }
}