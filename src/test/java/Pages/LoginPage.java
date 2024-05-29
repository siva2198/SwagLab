package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.saucedemo.com/
public class LoginPage {
    WebDriver driver;
    ProductPage productPage;
    public LoginPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='user-name']")
    WebElement userNameLoginTextField;
    @FindBy(xpath = "//*[@id='password']")
    WebElement passwordLoginTextField;
    @FindBy(xpath = "//*[@id='login-button']")
    WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    WebElement errorMessage;
    public void enterUserName(String userName) {
        userNameLoginTextField.sendKeys(userName);
    }
    public void enterPassword(String password) {
        passwordLoginTextField.sendKeys(password);
    }
    public String getErrorMessage() {
         return errorMessage.getText();
    }
    public ProductPage clickLoginButton() {
        loginButton.click();
        return new ProductPage(driver);
    }
}