package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Utils;

public class Reservation extends BaseBookingPage{

    By reservationFirstName = By.id("firstname");
    By reservationLastName = By.id("lastname");
    By reservationEmail = By.id("email");
    By reservationEmailConfirm = By.id("email_confirm");
    By reservationButton = By.cssSelector("button[name='book']");

    public Reservation(WebDriver driver) {
        super(driver);
    }


    @Step("Filling reservation form")
    public Reservation userReservation(){
        typeIn(reservationFirstName, "Test");
        typeIn(reservationLastName, "Test");
        typeIn(reservationEmail, "test@mailinator.com");
        typeIn(reservationEmailConfirm, "test@mailinator.com");
        clickOnElement(reservationButton);
        scrollToMyElement(reservationButton);
        Utils.waitForSeconds(2);
        return this;
    }
}
