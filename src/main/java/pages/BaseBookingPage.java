package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.Utils.dotEnv;

public class BaseBookingPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(BaseBookingPage.class.getName());
    private long waitTime = Long.parseLong(dotEnv().get("EXPLICIT_WAIT_TIME"));

    public BaseBookingPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
    }

    protected WebElement getElement(By locator) {
        WebElement element = null;
        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (StaleElementReferenceException st) {
            logger.warn("Stale Element Exception occured.");
            st.printStackTrace();
            driver.findElement(locator);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }

    protected void typeIn(By locator, String text) {
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void clickOnElement(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (ElementClickInterceptedException ex) {
            logger.warn("ElementClickInterceptedException occurred!");
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            hoverAndClick(locator);
        } catch (StaleElementReferenceException staleEl) {
            getElement(locator).click();
            logger.warn("Stale Element Exception occurred.");
        } catch (TimeoutException te) {
            te.printStackTrace();
            WebElement element = getElement(locator);
            js.executeScript("arguments.click();", element);
            logger.warn("TimeoutException occurred!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("FAILED - Unable to click on element " + locator.toString());
        }

    }

    private void hoverAndClick(By locator) {
        new Actions(driver)
                .moveToElement(getElement(locator))
                .click()
                .perform();
    }


    protected void scrollToMyElement(By locator) {
        WebElement element = getElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", element);
    }
}
