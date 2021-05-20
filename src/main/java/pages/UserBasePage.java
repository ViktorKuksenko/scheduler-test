package pages;

import org.openqa.selenium.WebDriver;

public class UserBasePage extends TopPage {

    private WebDriver driver;

    public UserBasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
