package tests;

import enums.BasePageByUserType;
import helpers.DBHelper;
import helpers.PropertiesUtils;
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
import pages.AdminBasePage;
import pages.LoginPage;
import pages.UserBasePage;
import pages.providers.ProvidersPage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class AppointmentsSchedulerTestRunner {

    private final String BASE_URL = PropertiesUtils.getInstance("C:\\Users\\volod\\OneDrive\\Рабочий стол\\BK\\" +
            "ui-scheduler\\src\\main\\resources\\config.properties").getPropertyByName("system.url");
    private final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss";
    protected final Logger slf4JLogger = LoggerFactory.getLogger(this.getClass());
    private Map<Long, WebDriver> drivers;
    protected DBHelper dbHelper;


    protected WebDriver getWebDriver() {
        slf4JLogger.info("getting web driver");
        WebDriver currentWebDriver = drivers.get(Thread.currentThread().getId());
        if (currentWebDriver == null) {
            currentWebDriver = new ChromeDriver();
            drivers.put(Thread.currentThread().getId(), currentWebDriver);
        }
        return currentWebDriver;
    }

    @BeforeSuite
    public void beforeSuite() {
        drivers = new HashMap<>();
        WebDriverManager.chromedriver().setup();
        dbHelper = DBHelper.getInstance();
        getWebDriver().manage().window().maximize();
    }

    @BeforeClass
    public void beforeClass(ITestContext context) {

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        for (Map.Entry<Long, WebDriver> currentWebDriver : drivers.entrySet()) {
            if (currentWebDriver.getValue() != null) {
                currentWebDriver.getValue().close();
                currentWebDriver.getValue().quit();
            }
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        getWebDriver().get(BASE_URL);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (!result.isSuccess()) {
            slf4JLogger.error("FAILED TEST" + result.getName() + " ERROR");
        } else {
            slf4JLogger.info("TEST PASSED SUCCESSFULLY" +  result.getName());
        }
        getWebDriver().manage().deleteAllCookies();
    }

    private void createScreenShot(String methodName) {
        slf4JLogger.info("ScreenShot was successfully created!");
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE)
                .format(new Date());
        File scrFile = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + currentTime + "_Test_Method_Name_" + methodName + "_screenshot.png"));
        } catch (IOException e) {
            slf4JLogger.error("ERROR during screenShot creation!");
            e.printStackTrace();
        }
    }


    protected void setWindowSize(int width, int height) {
        getWebDriver().manage().window().setSize(new Dimension(width, height));
    }

    protected <T> T loadApplication(String basePageByUserType) {
        T obj = null;
        String pathToPropertiesFile = "C:\\Users\\volod\\OneDrive\\Рабочий стол\\BK\\ui-scheduler\\src\\main\\resources\\users.properties";
        PropertiesUtils propertiesUtils = new PropertiesUtils(pathToPropertiesFile);
        String username = null;
        String password = propertiesUtils.getPropertyByName("all.password");
        if (basePageByUserType.equals(BasePageByUserType.ADMIN_PAGE.toString())) {
            slf4JLogger.info("Logging as " + BasePageByUserType.ADMIN_PAGE.toString());
            username = propertiesUtils.getPropertyByName("admin.username");
            AdminBasePage adminBasePage = new LoginPage(getWebDriver()).setLoginField(username)
                    .setPasswordField(password)
                    .clickLoginButton(AdminBasePage.class);
            obj = (T) adminBasePage;
        } else if (basePageByUserType.equals(BasePageByUserType.PROVIDER_PAGE.toString())) {
            slf4JLogger.info("Logging as " + BasePageByUserType.PROVIDER_PAGE.toString());
            username = propertiesUtils.getPropertyByName("provider.username");
            ProvidersPage providersPage = new LoginPage(getWebDriver()).setLoginField(username)
                    .setPasswordField(password)
                    .clickLoginButton(ProvidersPage.class);
            obj = (T) providersPage;
        } else if (basePageByUserType.equals(BasePageByUserType.USER_PAGE.toString())) {
            slf4JLogger.info("Logging as " + BasePageByUserType.USER_PAGE.toString());
            username = propertiesUtils.getPropertyByName("user.username");
            UserBasePage userBasePage = new LoginPage(getWebDriver()).setLoginField(username)
                    .setPasswordField(password)
                    .clickLoginButton(UserBasePage.class);
            obj = (T) userBasePage;
        } else {
            slf4JLogger.error("No such user!");
        }
        return obj;
    }

    protected LoginPage loadApplication() {
        slf4JLogger.info("loading application");
        return new LoginPage(getWebDriver());
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