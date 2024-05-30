package TestCases;
import BaseConfig.BaseConfig;
import Pages.LoginPage;
import Pages.ProductPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseConfig {
    LoginPage loginPage;
    private static final Logger log= LogManager.getLogger(LoginTest.class);
    @Test(dataProvider = "loginData",description = "Login test case")
    public void testLogin(String username, String password) {
        loadURL();
        loginPage=new LoginPage(driver);
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
        log.info("Entered Login details :{} and {}", username, password);
        ProductPage productPage=loginPage.clickLoginButton();
        log.info("Current URL :{}", driver.getCurrentUrl());
        Assert.assertEquals(productPage.getLogoText(),"Swag Labs");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
return new Object[][] {
        {"standard_user", "secret_sauce"}
};
    }
}
