package TestCases;

import AbstractComponent.AbstractComponent;
import BaseConfig.BaseConfig;
import Pages.CartPage;
import Pages.ProductPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCart extends BaseConfig {
    ProductPage productPage;
    private static final Logger log = LogManager.getLogger(AddToCart.class);

    @Test(dependsOnMethods = {"TestCases.LoginTest.testLogin"})
    public void addToCart() throws InterruptedException {
        String productToAddToCart = "Sauce Labs Backpack";
        String thanksNote ="Thank you for your order!";
        productPage = new ProductPage(driver);
        AbstractComponent abstractComponent = new AbstractComponent(driver);
        Thread.sleep(1000);
        productPage.selectProductWithProductName(productToAddToCart);
        log.info("product {} got selected ",productToAddToCart);
        Thread.sleep(1000);
        productPage.clickAddToCartButton();
        log.info("add to cart button clicked");
        CartPage cartPage =productPage.clickShoppingCartButton();
        log.info("cart button clicked");
        Assert.assertEquals(cartPage.getNameOfProductInCartItem(),productToAddToCart);
        log.info("Validate with product name");
        cartPage.clickCheckoutButton();
        cartPage.fillYourInformation();
        Assert.assertEquals(cartPage.getNameOfProductInCartItem(),productToAddToCart);
        System.out.println(cartPage.orderDetailsCheckout());
        cartPage.clickFinishButton();
        Assert.assertEquals(cartPage.getTextThanksNote(),thanksNote);
    }
}
