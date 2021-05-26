package tests;

import enums.BasePageByUserType;
import helpers.DBHelper;
import helpers.PropertiesUtils;
import helpers.WaitUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.AdminBasePage;
import pages.LoginPage;
import pages.TopPage;
import pages.UserBasePage;
import pages.providers.ProvidersPage;

import java.io.File;
import java.io.IOException;
import java.security.acl.Acl;
import java.text.SimpleDateFormat;
import java.util.*;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;



public class TestRunner {

    private final String BASE_URL = "url";
    private final Long ONE_SECOND_DELAY = 1000L;
    private final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss";
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String serverUrl = "http://localhost:8080";
    private Map<Long, WebDriver> drivers;
    protected DBHelper dbHelper;

//    protected WebDriver getDriver() {
////        WebDriver currentWebDriver = drivers.get(Thread.currentThread().getId());
////        if (currentWebDriver == null) {
//
//        WebDriverManager.chromedriver().setup();
//        WebDriver  currentWebDriver = new ChromeDriver();
//            currentWebDriver.manage().timeouts().implicitlyWait(WaitUtils.IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
//            WaitUtils.setDefaultImplicitlyWait(currentWebDriver);
//        getDriver().get(serverUrl);
//            //currentWebDriver.manage().window().maximize();
////            drivers.put(Thread.currentThread().getId(), currentWebDriver);
////        }
//        return currentWebDriver;
//    }

    protected WebDriver getDriver() {
        WebDriver currentWebDriver = drivers.get(Thread.currentThread().getId());
        if (currentWebDriver == null) {
            currentWebDriver = new ChromeDriver();
            //currentWebDriver.manage().timeouts().implicitlyWait(WaitWrapper.IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
            WaitUtils.setDefaultImplicitlyWait(currentWebDriver);
            currentWebDriver.manage().window().maximize();
            drivers.put(Thread.currentThread().getId(), currentWebDriver);
        }
        return currentWebDriver;
    }

    @BeforeSuite
    public void beforeSuite() {
        drivers = new HashMap<>();
        WebDriverManager.chromedriver().setup();
        dbHelper = DBHelper.getInstance();
        // WebDriverManager.firefoxdriver().setup();
    }

    @BeforeClass
    public void beforeClass(ITestContext context) {
        // driver = new ChromeDriver();
        // driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS,
        // TimeUnit.SECONDS);
        // driver.manage().window().maximize();
        for (Map.Entry<String, String> entry : context.getCurrentXmlTest().getAllParameters().entrySet()) {
            System.out.println("Key: " + entry.getKey() + "  Value: " + entry.getValue());
            if (entry.getKey().toLowerCase().equals(BASE_URL)) {
                serverUrl = entry.getValue();
                break;
            }
        }
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        presentationSleep(); // For Presentation ONLY
        // driver.close();
        // if (driver != null) {
        // driver.quit();
        // };
        for (Map.Entry<Long, WebDriver> currentWebDriver : drivers.entrySet()) {
            if (currentWebDriver.getValue() != null) {
                currentWebDriver.getValue().close();
                currentWebDriver.getValue().quit();
            }
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        // driver.get(BASE_URL);
        getDriver().get(serverUrl);
        presentationSleep(); // For Presentation ONLY
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        presentationSleep(); // For Presentation ONLY
        // TODO Logout
        if (!result.isSuccess()) {
            getDriver().manage().deleteAllCookies();
            // Take Screenshot, save sourceCode, save to log, prepare report, Return to
            logger.info("***Test " + result.getName() + " ERROR");
            //takeScreenShot(result.getName());
            // previous state, logout, etc.
        }
        // logout; clear cache; delete cookie; delete session;
        // Save Screen;
    }

    private void createScreenShot(String name) {
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + currentTime + "_TestName_" + name + "_screenshot.png"));
        } catch (IOException e) {
            // TODO Add Log;
            e.printStackTrace();
        }
        // log.info("Screenshot was taken");
    }

    // Overload
    protected void presentationSleep() {
        presentationSleep(1);
    }

    // Overload
    protected void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void setWindowSize(int width, int height) {
        getDriver().manage().window().setSize(new Dimension(width, height));
    }

    protected <T> T loadApplication(String basePageByUserType) {
        T obj = null;
        String pathToPropertiesFile = "C:\\Users\\volod\\OneDrive\\Рабочий стол\\BK\\ui-scheduler\\src\\main\\resources\\users.properties";
        PropertiesUtils propertiesUtils = new PropertiesUtils(pathToPropertiesFile);
        String username = null;
        String password = propertiesUtils.getPropertyByName("all.password");
        if (basePageByUserType.equals(BasePageByUserType.ADMIN_PAGE.toString())) {
            username = propertiesUtils.getPropertyByName("admin.username");
            AdminBasePage adminBasePage = new LoginPage(getDriver()).setLoginField(username)
                    .setPasswordField(password)
                    .clickLoginButton(AdminBasePage.class);
            obj = (T) adminBasePage;
        } else if (basePageByUserType.equals(BasePageByUserType.PROVIDER_PAGE.toString())) {
            username = propertiesUtils.getPropertyByName("provider.username");
            ProvidersPage providersPage = new LoginPage(getDriver()).setLoginField(username)
                    .setPasswordField(password)
                    .clickLoginButton(ProvidersPage.class);
            obj = (T) providersPage;
        } else if (basePageByUserType.equals(BasePageByUserType.USER_PAGE.toString())) {
            username = propertiesUtils.getPropertyByName("user.username");
            UserBasePage userBasePage = new LoginPage(getDriver()).setLoginField(username)
                    .setPasswordField(password)
                    .clickLoginButton(UserBasePage.class);
            obj = (T) userBasePage;
        }
        return obj;
    }

    protected LoginPage loadApplication() {
        return new LoginPage(getDriver());
    }

    protected String createRandomValueWithInteger() {
        Random random = new Random();
        return Integer.toString(random.nextInt(900));
    }

    protected String createRandomValueWithDouble() {
        Random random = new Random();
        return String.format("%d.00", random.nextInt(9999));
    }

}