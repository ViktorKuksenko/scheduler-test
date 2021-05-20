package pages;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private WebDriver driver;

    public DriverFactory(WebDriver driver) {
        this.driver = driver;
    }

    public AdminBasePage getAdminBasePagePO() {
        return new AdminBasePage(driver);
    }

//    public ProviderBasePage getProviderBasePagePO() {
//        return new ProviderBasePage(driver);
//    }
//
//    public UserBasePage getUserBasePagePO() {
//        return new UserBasePage(driver);
//    }
}
