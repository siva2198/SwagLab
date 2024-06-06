package Pages;

import AbstractComponent.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

// page_url = about:blank
public class ProductPage extends AbstractComponent {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='app_logo']")
    WebElement logo;

    @FindBy(xpath = "")
    WebElement allProductName;

    @FindBy(xpath = "(//button[normalize-space()='Add to cart'])[1]")
    WebElement addToCartButtonParticularProduct;

    public boolean getLogo() {
        return logo.isDisplayed();
    }
    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    WebElement shoppingCartLink;

    public String getLogoText() {
        return logo.getText();
    }
    public void clickAddToCartButton() {
        addToCartButtonParticularProduct.click();
    }

    //List of all the products
    public void getAllProductName() {
        List<WebElement> productNameElements = driver.findElements(By.xpath("//div[@class='inventory_item_name ']"));
        List<WebElement> priceElements = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        if (productNameElements.size() != priceElements.size()) {
            System.out.println("Does not have the same size");
        } else {
            for (int i = 0; i < productNameElements.size(); i++) {
                System.out.println(productNameElements.get(i).getText() + " -- " + priceElements.get(i).getText());
            }
        }
    }

    //Select the particular product
    public void selectProductWithProductName(String productName) throws InterruptedException {
        List<WebElement> selectParticularProducts = driver.findElements(By.xpath("//div[@class='inventory_item_name ']"));
        for (int i = 0; i < selectParticularProducts.size(); i++) {
            WebElement selectParticularProduct = driver.findElements(By.xpath("//div[@class='inventory_item_name ']")).get(i);
                if (selectParticularProduct.getText().equals(productName)) {
                    selectParticularProduct.click();
                    break;
            }
        }
    }
    public CartPage clickShoppingCartButton() {
        shoppingCartLink.click();
        return new CartPage(driver);
    }
}