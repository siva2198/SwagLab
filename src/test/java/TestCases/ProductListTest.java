package TestCases;

import BaseConfig.BaseConfig;
import Pages.ProductPage;
import org.testng.annotations.Test;

public class ProductListTest extends BaseConfig {
    ProductPage productPage;
    @Test(dependsOnMethods = {"TestCases.LoginTest.testLogin"},description = "List of all the products")
    public void productList() {
        productPage = new ProductPage(driver);
        productPage.getAllProductName();
}
}
