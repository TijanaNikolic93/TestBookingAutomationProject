package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;


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

    private By selectRoomDropDown = By.cssSelector("select[data-room-id='44047313']");
    private By buttonIWillReserve = By.cssSelector("div[data-component='hotel/new-rooms-table/reservation-cta']");
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
        clickOnElement(selectRoomDropDown);
        Select select = new Select(getElement(selectRoomDropDown));
        select.selectByValue("1");
        clickOnElement(selectRoomDropDown);
        scrollToMyElement(selectRoomDropDown);
        clickOnElement(buttonIWillReserve);
        return this;
    }

    @Step("Verifycation price")
    public boolean verifyIsPriceEqual(){
        String expectedPrice = "99750";
        String totalPrice = getElement(reservationTotalPrice).getText().replaceAll("\\D", "");
        if (!totalPrice.equals(expectedPrice)){
            logger.info("PASSED - Price found in element " + totalPrice + " DOES NOT MATCH expected text [ " + expectedPrice + " ]");
            return true;
        }else {
            logger.error("FAILED - Price found in element " + totalPrice + " MATCHES expected text [ " + expectedPrice + " ]");
        }
        return false;
    }

}
