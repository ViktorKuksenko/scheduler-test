package pages;

import helpers.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class APTSElement {

    private WebDriver driver;
    private WebElement webElement;
    private By locator;
    private static final long WAIT_TIMEOUT = 10;

    public APTSElement(WebDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
    }

    public <T> APTSElement waitUntil(ExpectedCondition<T> expectedConditions, long timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
        webDriverWait.until(expectedConditions);
        return this;
    }

    public APTSElement waitForElementToBeVisible(long timeout) {
        WaitUtils.setZeroImplicitlyWait(driver);
        APTSElement aptsElement = waitUntil(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator), timeout);
        WaitUtils.setDefaultImplicitlyWait(driver);
        return aptsElement;
    }

    public APTSElement waitForElementToBeVisible() {
        WaitUtils.setZeroImplicitlyWait(driver);
        APTSElement aptsElement = waitUntil(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator), WAIT_TIMEOUT);
        WaitUtils.setDefaultImplicitlyWait(driver);
        return aptsElement;
    }

    public APTSElement waitForElementToBeClickable() {
        WaitUtils.setZeroImplicitlyWait(driver);
        APTSElement aptsElement = waitUntil(ExpectedConditions.elementToBeClickable(locator), WAIT_TIMEOUT);
        WaitUtils.setDefaultImplicitlyWait(driver);
        return aptsElement;
    }

    public APTSElement waitForElementToBePresent() {
        return waitUntil(ExpectedConditions.presenceOfElementLocated(locator), WAIT_TIMEOUT);
    }

    public APTSElement waitForInvisibilityOfElementLocatedBy() {
        findElement();
        return waitUntil(ExpectedConditions.invisibilityOf(webElement), WAIT_TIMEOUT);
    }

    public APTSElement waitForStalenessOfElement() {
        findElement();
        return waitUntil(ExpectedConditions.stalenessOf(webElement), WAIT_TIMEOUT);
    }

    private void findElement() {
        webElement = driver.findElement(locator);
    }

    public String getText() {
        findElement();
        return webElement.getText();
    }

    public APTSElement click() {
        findElement();
        webElement.click();
        return this;
    }

    public APTSElement clickByJS() {
        String clickScript = "try { return arguments[0].click();} catch(e) { var c = document.createEvent('MouseEvent'); c.initEvent('click', true, false, window, 0, 0, 0); return arguments[0].dispatchEvent(c)}";
        ((JavascriptExecutor) driver).executeScript(clickScript, webElement);
        return this;
    }

    public APTSElement clearAndSendText(String text) {
        findElement();
        webElement.click();
        webElement.clear();
        webElement.sendKeys(text);
        return this;
    }

    public boolean isElementVisible() {
        findElement();
        return webElement.isDisplayed();
    }

    public boolean isElementEnabled() {
        findElement();
        return webElement.isEnabled();
    }

    public WebElement getWebElement() {
        return webElement;
    }
}
