package tests;

import core.DriverManager;
import core.Environment;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class BaseBookingTest {

    WebDriver driver;
    protected SoftAssert softAssert;

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = DriverManager.getInstance().setDriver();
        softAssert = new SoftAssert();
        new  Environment(driver).openBrowser();
    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}

