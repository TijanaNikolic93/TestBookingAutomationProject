package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class TravelData extends BaseBookingPage {

    private static final Logger logger = LogManager.getLogger(TravelData.class);
    Reservation reservation;

    private By selectDropDownDestination = By.cssSelector("label[class='sb-destination-label-sr']");
    private By selectPlaceToStay = By.cssSelector("li[data-label='Zlatibor']");
    private By selectDayOfArrival = By.cssSelector("span[aria-label='31 decembar 2022']");
    private By selectedDayOfLeaving = By.cssSelector("span[aria-label='8 januar 2023']");
    private By selectSearchButtonForAvailableHotel = By.cssSelector(".js-sb-submit-text");

    private By filterBreakfastIncluded = By.cssSelector("input[for='__bui-c401618-6'] , div[data-filters-item='popular:mealplan=1']");
    private By showAvailabilityOfHotel = By.xpath("//a[contains(@href,'https://www.booking.com/hotel/rs/monix-club-zlatibor.sr.')]/span[text()='Prikaži raspoloživost']");

    private By selectRoomDropDown = By.cssSelector("select[name='nr_rooms_44047316_359043235_2_1_0']");
    private By buttonIWillReserve = By.cssSelector("span[class='bui-button__text js-reservation-button__text']");

    private By priceOfTheHotel = By.cssSelector("span[class='fcab3ed991 fbd1d3018c e729ed5ab6']");
    private By reservationTotalPrice = By.cssSelector("div[class='bp-price-details__charge-value e2e-price-details__total-charge--user']");


    public TravelData(WebDriver driver) {
        super(driver);
    }

    @Step("Choosing place and date")
    public TravelData goToPlaceToStay() {
        clickOnElement(selectDropDownDestination);
        clickOnElement(selectPlaceToStay);
        clickOnElement(selectDayOfArrival);
        clickOnElement(selectedDayOfLeaving);
        clickOnElement(selectSearchButtonForAvailableHotel);
        return this;
    }

    @Step("Choosing filters and hotel")
    public TravelData goToOfferedHotel() {
        clickOnElement(filterBreakfastIncluded);
        scrollToMyElement(showAvailabilityOfHotel);
        clickOnElement(showAvailabilityOfHotel);
        Utils.waitForSeconds(2);
        return this;
    }

    @Step("Choosing room (select class)")
    public TravelData selectRoom() {
        scrollToMyElement(selectRoomDropDown);
        Select select = new Select(getElement(selectRoomDropDown));
        select.selectByValue("0");
        clickOnElement(selectRoomDropDown);
        select.selectByValue("1");
        clickOnElement(selectRoomDropDown);
        clickOnElement(buttonIWillReserve);
        Utils.waitForSeconds(2);
        return this;
    }

    public TravelData actualPrice() {
        String price = goToOfferedHotel().getList(priceOfTheHotel).getText();
        price.replaceAll("\\D", "");
        return this;
    }

    @Step("Verifycation price")
    public boolean verifyIsPriceEqual(){
        String price = reservation.getElement(reservationTotalPrice).getText();
        String totalPrice = price.replaceAll("\\D","");
       return actualPrice().equals(totalPrice);
    }
}
