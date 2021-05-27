package pages.customers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.APTSElement;
import pages.TopPage;

public class CorporateCustomerDetailsPage extends TopPage {

    private WebDriver driver;
    private APTSElement emailTextField = getAPTSElement(By.id("email"));
    private APTSElement companyNameTextField = getAPTSElement(By.id("companyName"));
    private APTSElement vatNumberTextField = getAPTSElement(By.id("vatNumber"));
    private APTSElement firstNameTextField = getAPTSElement(By.id("firstName"));
    private APTSElement lastNameTextField = getAPTSElement(By.id("lastName"));
    private APTSElement mobileTextField = getAPTSElement(By.id("mobile"));
    private APTSElement streetTextField = getAPTSElement(By.id("street"));
    private APTSElement cityTextField = getAPTSElement(By.id("city"));
    private APTSElement postcodeTextField = getAPTSElement(By.id("postcode"));
    private APTSElement saveButtonTextField = getAPTSElement(By.xpath("//button[@class='btn btn-primary' and text()='Save']"));

    public CorporateCustomerDetailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public CorporateCustomerDetailsPage setEmailTextField(String email) {
        emailTextField.waitForElementToBeClickable().clearAndSendText(email);
        return this;
    }

    public CorporateCustomerDetailsPage setCompanyNameTextField(String companyName) {
        companyNameTextField.clearAndSendText(companyName);
        return this;
    }

    public CorporateCustomerDetailsPage setVatNumberTextField(String vatNumber) {
        vatNumberTextField.clearAndSendText(vatNumber);
        return this;
    }

    public CorporateCustomerDetailsPage setFirstNameTextField(String firstName) {
        firstNameTextField.clearAndSendText(firstName);
        return this;
    }

    public CorporateCustomerDetailsPage setLastNameTextField(String lastName) {
        lastNameTextField.clearAndSendText(lastName);
        return this;
    }

    public CorporateCustomerDetailsPage setMobileTextField(String mobile) {
        mobileTextField.clearAndSendText(mobile);
        return this;
    }

    public CorporateCustomerDetailsPage setStreetTextField(String street) {
        streetTextField.clearAndSendText(street);
        return this;
    }

    public CorporateCustomerDetailsPage setCityTextField(String city) {
        cityTextField.clearAndSendText(city);
        return this;
    }

    public CorporateCustomerDetailsPage setPostcodeTextField(String postcode) {
        postcodeTextField.clearAndSendText(postcode);
        return this;
    }

    public CustomersPage clickSaveButton() {
        saveButtonTextField.waitForElementToBeClickable().click();
        return new CustomersPage(driver);
    }
}
