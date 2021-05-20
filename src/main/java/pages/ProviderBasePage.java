package pages;

import org.openqa.selenium.WebDriver;

public class ProviderBasePage extends TopPage {

    private WebDriver driver;

    public ProviderBasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
