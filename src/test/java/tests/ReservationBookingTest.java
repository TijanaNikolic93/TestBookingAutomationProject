package tests;

import listener.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Reservation;
import pages.TravelData;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

@Listeners(TestListener.class)
public class ReservationBookingTest extends BaseBookingTest {
    TravelData travelData;
    Reservation reservation;


    @BeforeMethod
    public void localSetup() {
        travelData = new TravelData(driver);
        reservation = new Reservation(driver);
    }

    @Test()
    public void userSelectTravelDate() {
        travelData.goToPlaceToStay()
                .goToOfferedHotel();
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        travelData.selectRoom();
        reservation.userReservation();
        softAssert.assertTrue(travelData.verifyIsPriceEqual());
        softAssert.assertAll();
    }
}
