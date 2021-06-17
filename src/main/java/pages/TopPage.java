package pages;

import helpers.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class TopPage {

    private WebDriver driver;

    public TopPage(WebDriver driver) {
        this.driver = driver;
    }

    protected APTSElement getAPTSElement(By locator) {
        return new APTSElement(driver, locator);
    }

    protected String getCommonNavBarXPathExp(String exp) {
        return String.format("//a[contains(text(), \"%s\")]", exp);
    }

    protected WebElement getHomePageWebElement() {
        return driver.findElement(By.xpath(getCommonNavBarXPathExp("Home")));
    }

    protected WebElement getAppointmentsPageWebElement() {
        return driver.findElement(By.xpath(getCommonNavBarXPathExp("Appointments")));
    }

    public WebElement clickMyAccountPageWebElement() {
        return driver.findElement(By.xpath(getCommonNavBarXPathExp("My account")));
    }

    public NotificationsPage clickNotificationsPageWebElement() {
        getAPTSElement(By.id("notifications-count")).click();
        return new NotificationsPage(driver);
    }

    public String getLoggedAsWebElement() {
        return getAPTSElement(By.xpath("//p[@class=\"navbar-nav ml-auto navbar-text\"]")).getText();
    }

    public APTSElement clickLogOutWebElement() {
        APTSElement logOutElement = getAPTSElement(By.xpath("//a[text()=\"Log out\"]")).waitForElementToBeClickable()
                .click();
        WebElement logOutWebElement = logOutElement.getWebElement();
        WaitUtils.waitForStalenessOfElementLocated(driver, logOutWebElement);
        return logOutElement;
    }

    public String getPageTitleText() {
        return driver.getTitle();
    }

    public boolean isPresentPageTitle() {
        return driver.getTitle().length() > 0;
    }

}
