package TestCases;

import BaseConfig.BaseConfig;
import Pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InvalidLoginTest  extends BaseConfig {
    LoginPage loginPage;
    public static final Logger log = LogManager.getLogger(InvalidLoginTest.class);

    @Test(description = "Invalid Login credentials",dataProvider = "loginData")
    public void invalidLogin(String username, String password) {
        loadURL();
        loginPage = new LoginPage(driver);
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        log.info("User login details {} and {}", username, password);
        Assert.assertEquals(loginPage.getErrorMessage(),"Epic sadface: Sorry, this user has been locked out.");
    }
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
                {"locked_out_user","secret_sauce"}
        };
    }
}
