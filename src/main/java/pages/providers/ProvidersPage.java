package pages.providers;

import org.openqa.selenium.WebDriver;
import pages.TopPage;

public class ProvidersPage extends TopPage {
    private WebDriver driver;

    public ProvidersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
