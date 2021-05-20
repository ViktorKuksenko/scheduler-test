package pages.invoices;

import org.openqa.selenium.WebDriver;
import pages.TopPage;

public class InvoicesPage extends TopPage {

    private WebDriver driver;

    public InvoicesPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
