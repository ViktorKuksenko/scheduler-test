package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterUserPage extends TopPage {

    private WebDriver driver;
    private APTSElement usernameTextField = getAPTSElement(By.id("userName"));
    private APTSElement passwordTextField = getAPTSElement(By.id("password"));
    private APTSElement repeatPasswordTextField = getAPTSElement(By.id("matchingPassword"));
    private APTSElement firstNameTextField = getAPTSElement(By.id("firstName"));
    private APTSElement lastNameTextField = getAPTSElement(By.id("lastName"));
    private APTSElement emailTextField = getAPTSElement(By.id("email"));
    private APTSElement mobileTextField = getAPTSElement(By.id("mobile"));
    private APTSElement streetTextField = getAPTSElement(By.id("street"));
    private APTSElement postCodeTextField = getAPTSElement(By.id("postcode"));
    private APTSElement cityTextField = getAPTSElement(By.id("city"));
    private APTSElement registerButton = getAPTSElement(By.xpath("//button[text()='Register']"));

    public RegisterUserPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public RegisterUserPage setUsernameTextField(String userName) {
        usernameTextField.waitForElementToBeClickable()
                .clearAndSendText(userName);
        return this;
    }

    public RegisterUserPage setPasswordTextField(String password) {
        passwordTextField.waitForElementToBeClickable()
                .clearAndSendText(password);
        return this;
    }

    public RegisterUserPage setRepeatPasswordTextField(String repeatPassword) {
        repeatPasswordTextField.waitForElementToBeClickable()
                .clearAndSendText(repeatPassword);
        return this;
    }

    public RegisterUserPage setFirstNameTextField(String firstName) {
        firstNameTextField.waitForElementToBeClickable()
                .clearAndSendText(firstName);
        return this;
    }

    public RegisterUserPage setLastNameTextField(String lastName) {
        lastNameTextField.waitForElementToBeClickable()
                .clearAndSendText(lastName);
        return this;
    }

    public RegisterUserPage setEmailTextField(String email) {
        emailTextField.waitForElementToBeClickable()
                .clearAndSendText(email);
        return this;
    }

    public RegisterUserPage setMobileTextField(String mobile) {
        mobileTextField.waitForElementToBeClickable()
                .clearAndSendText(mobile);
        return this;
    }

    public RegisterUserPage setStreetTextField(String street) {
        streetTextField.waitForElementToBeClickable()
                .clearAndSendText(street);
        return this;
    }

    public RegisterUserPage setPostCodeTextField(String postCode) {
        postCodeTextField.waitForElementToBeClickable()
                .clearAndSendText(postCode);
        return this;
    }

    public RegisterUserPage setCityTextField(String city) {
        cityTextField.waitForElementToBeClickable()
                .clearAndSendText(city);
        return this;
    }

    public LoginPage clickRegisterButton() {
        registerButton.waitForElementToBeClickable()
                .click();
        return new LoginPage(driver);
    }
}
