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

import javax.print.DocFlavor;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SchedulePage {
    private final WebDriver driver;
    WebDriverWait wait;
    private ExtentTest test;

     By txt_fname=By.xpath("//input[contains(@name,'FirstName')]");
     By txt_Lname=By.xpath("//input[contains(@name,'LastName')]");
     By txt_email=By.xpath("//input[contains(@name,'Email')]");
     By txt_compName= By.xpath("//input[contains(@name,'Company')]");
     By txt_phone=By.xpath("//input[contains(@name,'Phone')]");
     By txt_job=By.xpath("//input[contains(@name,'Title')]");
     By dpdn_unitCount=By.xpath("//select[@name='Unit_Count__c']");
     By dpdn_Iam=By.xpath("//select[@name='demoRequest']");


    public SchedulePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        ExtentReports extent = ExtentTestManager.getInstance();
        test = extent.createTest("Sauce Lab Automation Checkout ", "Checkout feature");

    }

   public void enterScheduleUserDetails(String fname, String lname, String email,String comp, String phone){
        try{
            WebElement firstNameInput = driver.findElement(txt_fname);
            WebElement lastNameInput = driver.findElement(txt_Lname);
            WebElement email1 = driver.findElement(txt_email);
            WebElement companyName = driver.findElement(txt_compName);
            WebElement phoneNumber = driver.findElement(txt_phone);
            WebElement unitCount = driver.findElement(dpdn_unitCount);
            WebElement jobTitle = driver.findElement(txt_job);
            WebElement Iam = driver.findElement(dpdn_Iam);

            if (firstNameInput.isDisplayed() && lastNameInput.isDisplayed() && email1.isDisplayed() && companyName.isDisplayed() && phoneNumber.isDisplayed() &&  unitCount.isDisplayed() && jobTitle.isDisplayed() && Iam.isDisplayed()  ) {
                firstNameInput.click();
                wait(10);
                firstNameInput.sendKeys(fname);
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                lastNameInput.sendKeys(lname);
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                email1.sendKeys(email);
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                companyName.sendKeys(comp);
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                phoneNumber.sendKeys(phone);
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                jobTitle.sendKeys("AccountManager");


            } else {
                System.out.println("Login form is not displayed");
                Assert.assertFalse(false, "Login not success");
                test.fail("User login failed");
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
   }

   public void clickOnButton(String text)
    {
        try {
            WebElement buttonDemo = driver.findElement(By.xpath("//div[text()='" + text + "']"));
            buttonDemo.click();
            wait(100);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }



}
