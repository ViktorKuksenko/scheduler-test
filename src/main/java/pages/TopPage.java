package pages;

import helpers.TableUtils;
import helpers.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    public WebElement getMyAccountPageWebElement() {
        return driver.findElement(By.xpath(getCommonNavBarXPathExp("My account")));
    }

    public WebElement getNotificationsPageWebElement() {
        return driver.findElement(By.id("notifications-count"));
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


    public boolean isPresentPageTitle() {
        return driver.getTitle().length() > 0;
    }



}
