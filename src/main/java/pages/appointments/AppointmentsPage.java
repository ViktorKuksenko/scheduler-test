package pages.appointments;

import org.openqa.selenium.WebDriver;
import pages.TopPage;

public class AppointmentsPage extends TopPage {

    private WebDriver driver;

    public AppointmentsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }



}
