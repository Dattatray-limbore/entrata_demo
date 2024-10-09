package pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.ExtentTestManager;
import utilities.PasswordManager;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HomePage {
    private final WebDriver driver;
    WebDriverWait wait;
    private ExtentTest test;

    // Locators for form fields
    By txt_fname = By.xpath("//input[contains(@name,'FirstName')]");
    By txt_Lname = By.xpath("//input[contains(@name,'LastName')]");
    By txt_email = By.xpath("//input[contains(@name,'Email')]");
    By txt_compName = By.xpath("//input[contains(@name,'Company')]");
    By txt_phone = By.xpath("//input[contains(@name,'Phone')]");
    By txt_job = By.xpath("//input[contains(@name,'Title')]");
    By dpdn_unitCount = By.xpath("//select[@name='Unit_Count__c']");
    By dpdn_Iam = By.xpath("//select[@name='demoRequest']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        ExtentReports extent = ExtentTestManager.getInstance();
        test = extent.createTest("Entrata Automation", "Automation Testing for Entrata");
    }

    public void navigateTo() {
        driver.get(PasswordManager.getPassword("url"));
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        test.pass("Navigated successfully to url " + PasswordManager.getPassword("url"));
        WebElement cookieAlert = driver.findElement(By.xpath("//div//a[contains(text(),'Accept Cookies')]"));
        if (cookieAlert.isDisplayed()) {
            cookieAlert.click();
            System.out.println("Accepted cookies");
        }
    }

    public void verifyNavMenus(String menuText) {
        try {
            WebElement menu = driver.findElement(By.xpath("//div[contains(text(),'" + menuText + "')]/..//div[contains(@class,'dropdown-icon')]"));
            if (menu.isDisplayed()) {
                System.out.println("Menu option is displayed for " + menuText);
                Assert.assertTrue(true, "Menu option is displayed for " + menuText);
            } else {
                System.out.println("Menu option is not displayed for " + menuText);
                Assert.assertFalse(false, "Menu option is not displayed for " + menuText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickOn(String menuText) {
        WebElement menu = driver.findElement(By.xpath("//div[contains(text(),'" + menuText + "')]/..//div[contains(@class,'dropdown-icon')]"));
        WebElement button = driver.findElement(By.xpath("//div[@class='black_btn-text' and text()='Schedule Your Demo']"));
        try {
            if (menu.isDisplayed()) {
                System.out.println("Menu option is displayed for " + menuText);
                wait.until(ExpectedConditions.visibilityOf(menu));
                menu.click();
                Assert.assertTrue(true, "Menu option is displayed for " + menuText);
            } else if (button.isDisplayed()) {
                button.click();
                System.out.println("Successfully clicked");
                wait(100);
            } else {
                System.out.println("Menu option is not displayed and not able to click for " + menuText);
                Assert.assertFalse(false, "Menu option is not displayed for " + menuText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enterScheduleUserDetails(String fname, String lname, String email, String comp, String phone) {
        try {
            WebElement firstNameInput = driver.findElement(txt_fname);
            WebElement lastNameInput = driver.findElement(txt_Lname);
            WebElement emailInput = driver.findElement(txt_email);
            WebElement companyName = driver.findElement(txt_compName);
            WebElement phoneNumber = driver.findElement(txt_phone);
            WebElement unitCount = driver.findElement(dpdn_unitCount);
            WebElement jobTitle = driver.findElement(txt_job);
            WebElement iam = driver.findElement(dpdn_Iam);

            if (firstNameInput.isDisplayed() && lastNameInput.isDisplayed() && emailInput.isDisplayed() && companyName.isDisplayed() && phoneNumber.isDisplayed() && unitCount.isDisplayed() && jobTitle.isDisplayed() && iam.isDisplayed()) {
                firstNameInput.sendKeys(fname);
                lastNameInput.sendKeys(lname);
                emailInput.sendKeys(email);
                companyName.sendKeys(comp);
                phoneNumber.sendKeys(phone);
                jobTitle.sendKeys("Account Manager");
            } else {
                System.out.println("Form fields are not displayed correctly.");
                Assert.fail("Form fields are not displayed correctly.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickOnButton(String text) {
        try {
            // Capture the original window handle
            String originalWindow = driver.getWindowHandle();

            // Locate the button that opens the new tab
            WebElement buttonDemo = driver.findElement(By.xpath("//a[@href='https://go.entrata.com/schedule-demo.html']"));
            buttonDemo.click();

            // Wait for the new tab to open and switch to it
            Set<String> windowHandles = driver.getWindowHandles();
            for (String windowHandle : windowHandles) {
                if (!originalWindow.contentEquals(windowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            // Perform actions in the new tab (e.g., fill form or interact with elements)
            WebElement firstNameInput = driver.findElement(txt_fname);
            firstNameInput.sendKeys("John");

            // Optionally, close the new tab and switch back to the original window
            driver.close();
            driver.switchTo().window(originalWindow);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
