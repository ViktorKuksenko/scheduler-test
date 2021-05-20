package pages.customers;

import org.openqa.selenium.WebDriver;
import pages.TopPage;

public class CustomerDetailsPage extends TopPage {

    private WebDriver driver;

    public CustomerDetailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
