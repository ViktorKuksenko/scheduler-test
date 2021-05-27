package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.customers.CustomersPage;

public class RegisterCorporateCustomersPage extends TopPage {

    private WebDriver driver;
    private APTSElement usernameTextField = getAPTSElement(By.id("userName"));
    private APTSElement passwordTextField = getAPTSElement(By.id("password"));
    private APTSElement repeatPasswordTextField = getAPTSElement(By.id("matchingPassword"));
    private APTSElement companyNameTextField = getAPTSElement(By.id("companyName"));
    private APTSElement vatNumberTextField = getAPTSElement(By.id("vatNumber"));
    private APTSElement firstNameTextField = getAPTSElement(By.id("firstName"));
    private APTSElement lastNameTextField = getAPTSElement(By.id("lastName"));
    private APTSElement emailTextField = getAPTSElement(By.id("email"));
    private APTSElement mobileTextField = getAPTSElement(By.id("mobile"));
    private APTSElement streetTextField = getAPTSElement(By.id("street"));
    private APTSElement postcodeTextField = getAPTSElement(By.id("postcode"));
    private APTSElement cityTextField = getAPTSElement(By.id("city"));
    private APTSElement registerButton = getAPTSElement(By.xpath("//button[text()='Register']"));

    public RegisterCorporateCustomersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("get Username Text Field")
    public RegisterCorporateCustomersPage setUsernameTextField(String username) {
        usernameTextField.clearAndSendText(username);
        return this;
    }

    @Step("get Password Text Field")
    public RegisterCorporateCustomersPage setPasswordTextField(String password) {
        passwordTextField.clearAndSendText(password);
        return this;
    }

    @Step("get Repeat Password Text Field")
    public RegisterCorporateCustomersPage setRepeatPasswordTextField(String repeatPassword) {
        repeatPasswordTextField.clearAndSendText(repeatPassword);
        return this;
    }

    @Step("get Company Name Text Field")
    public RegisterCorporateCustomersPage setCompanyNameTextField(String companyName) {
        companyNameTextField.clearAndSendText(companyName);
        return this;
    }

    @Step("get Vat Number Text Field")
    public RegisterCorporateCustomersPage setVatNumberTextField(String vatNumber) {
        vatNumberTextField.clearAndSendText(vatNumber);
        return this;
    }

    @Step("get First Name Text Field")
    public RegisterCorporateCustomersPage setFirstNameTextField(String firstName) {
        firstNameTextField.clearAndSendText(firstName);
        return this;
    }

    @Step("get Last Name Text Field")
    public RegisterCorporateCustomersPage setLastNameTextField(String lastName) {
        lastNameTextField.clearAndSendText(lastName);
        return this;
    }

    @Step("get Email Text Field")
    public RegisterCorporateCustomersPage setEmailTextField(String email) {
        emailTextField.clearAndSendText(email);
        return this;
    }

    @Step("get Mobile Text Field")
    public RegisterCorporateCustomersPage setMobileTextField(String mobile) {
        mobileTextField.clearAndSendText(mobile);
        return this;
    }

    @Step("get Street Text Field")
    public RegisterCorporateCustomersPage setStreetTextField(String street) {
        streetTextField.clearAndSendText(street);
        return this;
    }

    @Step("get Postcode Text Field")
    public RegisterCorporateCustomersPage setPostcodeTextField(String postcode) {
        postcodeTextField.clearAndSendText(postcode);
        return this;
    }

    @Step("get City Text Field")
    public RegisterCorporateCustomersPage setCityTextField(String city) {
        cityTextField.clearAndSendText(city);
        return this;
    }

    public CustomersPage clickRegisterButton() {
        registerButton.waitForElementToBeClickable()
                .click();
        return new CustomersPage(driver);
    }
}
