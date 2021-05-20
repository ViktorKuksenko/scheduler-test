package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends TopPage{

    private WebDriver driver;
    private APTSElement loginTextField = getAPTSElement(By.id("username"));
    private APTSElement passwordTextField = getAPTSElement(By.id("password"));
    private APTSElement loginButton = getAPTSElement(By.xpath("//button[text()='Login']"));
    private APTSElement retail = getAPTSElement(By.xpath("//a[text()='Retail']"));
    private APTSElement corporate = getAPTSElement(By.xpath("//a[text()='Corporate']"));

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public LoginPage setLoginField(String login) {
        loginTextField.waitForElementToBeClickable()
                .clearAndSendText(login);
        return this;
    }

    public LoginPage setPasswordField(String password) {
        passwordTextField.waitForElementToBeClickable()
                .clearAndSendText(password);
        return this;
    }

    public <T> T clickLoginButton(Class<T> clazz) {
        T objToReturn = null;
        if (clazz == AdminBasePage.class) {
            loginButton.waitForElementToBeClickable().click();
            objToReturn = (T) new AdminBasePage(driver);
        } else if (clazz == ProviderBasePage.class) {
            loginButton.waitForElementToBeClickable().click();
            objToReturn = (T) new ProviderBasePage(driver);
        } else if (clazz == UserBasePage.class) {
            loginButton.waitForElementToBeClickable().click();
            objToReturn = (T) new UserBasePage(driver);
        }
        return objToReturn;
    }

    public RegisterUserPage clickRetailLink() {
        retail.waitForElementToBeClickable()
                .click();
        return new RegisterUserPage(driver);
    }

    public RegisterUserPage clickCorporateLink() {
        corporate.waitForElementToBeClickable()
                .click();
        return new RegisterUserPage(driver);
    }

}
