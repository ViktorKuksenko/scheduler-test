package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class WaitUtils {

    public static final long IMPLICITLY_WAIT_SECONDS = 10L;
    public static final long EXPLICITLY_WAIT_SECONDS = 10L;

    public static void setDefaultImplicitlyWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
    }

    public static void setCustomImplicitlyWait(WebDriver driver, long implicitlyWait) {
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
    }

    public static void setZeroImplicitlyWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public static void waitForInvisibilityOfElementLocated(WebDriver driver, By locator) {
        setZeroImplicitlyWait(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        setDefaultImplicitlyWait(driver);
    }

    public static void waitForPresenceOfElementLocated(WebDriver driver, By locator) {
        setZeroImplicitlyWait(driver);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        //wait until the element changed
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        setDefaultImplicitlyWait(driver);
    }

    public static void waitForVisibilityOfElementLocated(WebDriver driver, By locator) {
        setZeroImplicitlyWait(driver);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        setDefaultImplicitlyWait(driver);
    }

    public static void waitForStalenessOfElementLocated(WebDriver driver, WebElement webElement) {
        setZeroImplicitlyWait(driver);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        //wait until the element changed
        ExpectedCondition<Boolean> invsibility = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    if (!webElement.isDisplayed()) return false;
                    return false;
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return true;
                }
            }
        };
        wait.until(invsibility);
        setDefaultImplicitlyWait(driver);
    }
}
