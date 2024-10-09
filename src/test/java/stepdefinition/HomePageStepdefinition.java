package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HomePage;
import utilities.PasswordManager;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

public class HomePageStepdefinition extends PasswordManager {

    public WebDriver driver;
    private HomePage homePage;

    //This step will launch the chrome driver and navigate to entrata site

    @Given("I am on the Entrata homepage")
    public void i_am_on_the_entrata_homepage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage = new HomePage(driver);
        homePage.navigateTo();
    }

    // This step will wait and check the title of web page
    @When("I check the title of the page")
    public void i_check_the_title_of_the_page() {
        String actualTitle = driver.getTitle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(titleContains(actualTitle));
        System.out.println("Title is :" + actualTitle);
        Assert.assertEquals("Property Management Software | Entrata", actualTitle);
    }

    //Validate the title  with assertion
    @Then("The title should be {string}")
    public void the_title_should_be(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
        driver.quit();
    }

    //step is used to click on nav tab features
    @Then("User clicks on {string}")
    public void user_clicks_on(String optionName) {
        System.out.println("option Name is:" + optionName);
        homePage.clickOn(optionName);
    }

    //step verifies the option displayed on NavBar
    @Then("User Verifies that {string} option is displayed")
    public void user_verifies_that_option_is_displayed(String menuOptionNam) {
        homePage.verifyNavMenus(menuOptionNam);
    }

    @When("User clicks on {string} button")
    public void user_clicks_on_button(String text) {
        homePage.clickOnButton(text);
    }
    //This step will launch the chrome driver and navigate to entrata site
    @When("^User enters firtsName as (.*) lastName as(.*) email as(.*)companyName as(.*)phonenumber as(.*)$")
    public void enterUserDetails(String fname, String lname, String email,String comp,String phone) {
        homePage.enterScheduleUserDetails(fname,lname,email,comp,phone);
    }

    @Then("User verifies that demo is scheduled is success")
    public void user_verifies_that_demo_is_scheduled_is_success() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}

