package pages.appointments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.APTSElement;
import pages.TopPage;

public class AppointmentCalendar extends TopPage {

    private WebDriver driver;
    private APTSElement previousButton = getAPTSElement(By
            .xpath("//button[@class=\"fc-prev-button fc-button fc-state-default fc-corner-left\"]"));
    private APTSElement nextButton = getAPTSElement(By
            .xpath("//button[@class=\"fc-next-button fc-button fc-state-default fc-corner-right\"]"));
    private APTSElement todayButton = getAPTSElement(By.xpath("//button[contains(text(), \"today\")]"));
    private APTSElement currentTimeSlot = getAPTSElement(By.xpath("//div[@class=\"fc-center\"]"));
    private APTSElement monthButton;
    private APTSElement weekButton;

    public AppointmentCalendar(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
