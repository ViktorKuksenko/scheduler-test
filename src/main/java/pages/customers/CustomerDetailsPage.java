package pages.customers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.APTSElement;
import pages.TopPage;

public class CustomerDetailsPage extends TopPage {

    private WebDriver driver;
    private APTSElement username = getAPTSElement(By.cssSelector("#userName"));
    private APTSElement firstName = getAPTSElement(By.id("firstName"));
    private APTSElement lastName = getAPTSElement(By.id("lastName"));
    private APTSElement mobilePhone = getAPTSElement(By.id("mobile"));
    private APTSElement city = getAPTSElement(By.id("city"));
    private APTSElement email = getAPTSElement(By.id("email"));
    private APTSElement street = getAPTSElement(By.id("street"));
    private APTSElement postcode = getAPTSElement(By.id("postcode"));
    private APTSElement saveButton = getAPTSElement(By.xpath("//button[@class='btn btn-primary' and text()='Save']"));


    public CustomerDetailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public CustomerDetailsPage setUsernameTextField(String usernameText) {
        username.clearAndSendText(usernameText);
        return this;
    }

    public CustomerDetailsPage setFirstNameTextField(String firstNameText) {
        firstName.clearAndSendText(firstNameText);
        return this;
    }

    public CustomerDetailsPage setLastNameTextField(String lastNameText) {
        lastName.clearAndSendText(lastNameText);
        return this;
    }

    public CustomerDetailsPage setMobilePhoneTextField(String mobilePhoneText) {
        mobilePhone.clearAndSendText(mobilePhoneText);
        return this;
    }

    public CustomerDetailsPage setCityTextField(String cityText) {
        city.clearAndSendText(cityText);
        return this;
    }

    public CustomerDetailsPage setEmailTextField(String emailText) {
        email.clearAndSendText(emailText);
        return this;
    }

    public CustomerDetailsPage setStreetTextField(String streetText) {
        street.clearAndSendText(streetText);
        return this;
    }

    public CustomerDetailsPage setPostcodeTextField(String postcodeText) {
        postcode.clearAndSendText(postcodeText);
        return this;
    }

    public CustomerDetailsPage clickSaveButton() {
        saveButton.click();
        return this;
    }
}
