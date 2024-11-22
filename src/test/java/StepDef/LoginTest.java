package StepDef;

import ConfigurationHelper.DriverFactory.BaseBrowserConfiguration;
import Pages.LoginPage;
import Pages.ProductPage;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

public class LoginTest {

    private static final Logger log = Logger.getLogger(LoginTest.class);
    private LoginPage loginPage = new LoginPage(BaseBrowserConfiguration.getDriver());
    BaseBrowserConfiguration baseBrowserConfiguration = new BaseBrowserConfiguration();
    ExtentTest extentTest;
    ProductPage productPage;

    @Given("User is able to launch the browser and navigate to Client Portal")
    public void user_is_able_to_launch_the_browser_and_navigate_to_client_portal(){
        try{
            log.info("Launching the browser and navigate to Portal");
            baseBrowserConfiguration.getLoginURL();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @When("User enters the {string} and {string}")
    public void userEntersTheAnd(String username, String password) {
        try{
            log.info("Entering the " + username + " and " + password);
            loginPage.enterPassword(username);
            loginPage.enterPassword(password);
            ProductPage productPage = loginPage.clickLoginButton();
            extentTest.info("Entered username and password : " + username + " and " + password);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Then("User should be redirected to the dashboard")
    public void userShouldBeRedirectedToTheDashboard() {
        try {
            productPage.validateDashboard();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("User should see an error message")
    public void userShouldSeeAnErrorMessage() {
        try{
            loginPage.getErrorMessage();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
