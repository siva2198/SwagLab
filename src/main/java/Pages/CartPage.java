package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    WebElement nameOfProductInCartItem;
    @FindBy(xpath = "//button[@id='checkout']")
    WebElement checkoutButton;
    @FindBy(xpath = "//input[@id='first-name']")
    WebElement firstNameInCart;
    @FindBy(xpath = "//input[@id='last-name']")
    WebElement lastNameInCart;
    @FindBy(xpath = "//input[@id='postal-code']")
    WebElement postalCodeInCart;
    @FindBy(xpath = "//input[@id='continue']")
    WebElement continueButton;
    @FindBy(xpath = "//div[@class='summary_info']")
    WebElement orderDetailsLabel;

    @FindBy(xpath = "//button[@id='finish']")
    WebElement finishButton;
    @FindBy(css = ".complete-header")
    WebElement confirmationThankMessage;

    public String getNameOfProductInCartItem() {
        return nameOfProductInCartItem.getText();
    }

    public void clickCheckoutButton() {
        checkoutButton.click();
    }
    public String orderDetailsCheckout() {
        return orderDetailsLabel.getText();
    }
    public void clickFinishButton() {
        finishButton.click();
    }
    public String getTextThanksNote(){
         return confirmationThankMessage.getText();
    }
    public void fillYourInformation() {
        firstNameInCart.sendKeys("Test");
        lastNameInCart.sendKeys("id");
        postalCodeInCart.sendKeys("12345");
        continueButton.click();
    }
}
