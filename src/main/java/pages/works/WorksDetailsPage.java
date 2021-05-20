package pages.works;

import helpers.WaitUtils;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.APTSElement;
import pages.TopPage;


public class WorksDetailsPage extends TopPage {

    private WebDriver driver;
    private APTSElement nameTextField = getAPTSElement(By.id("name"));
    private APTSElement priceTextField = getAPTSElement(By.id("price"));
    private APTSElement durationTextField = getAPTSElement(By.id("duration"));
    private APTSElement descriptionTextField = getAPTSElement(By.id("description"));
    private APTSElement retailCheckBox = getAPTSElement(By.id("retail"));
    private APTSElement corporateCheckBox = getAPTSElement(By.id("corporate"));
    private APTSElement cancelableByCustomerYesCheckBox = getAPTSElement(By.id("option1"));
    private APTSElement cancelableByCustomerNoCheckBox = getAPTSElement(By.id("option2"));
    private APTSElement saveButton = getAPTSElement(By.xpath("//button[text()='Save']"));
    public WorksDetailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WorksDetailsPage setNameTextField(String name) {
        nameTextField.waitForElementToBeVisible()
                .clearAndSendText(name);
        return this;
    }


    public WorksDetailsPage setPriceTextField(String price) {
        priceTextField.clearAndSendText(price);
        return this;
    }

    public WorksDetailsPage setDurationTextField(String duration) {
        durationTextField.clearAndSendText(duration);
        return this;
    }

    public WorksDetailsPage setDescriptionTextField(String description) {
        descriptionTextField.clearAndSendText(description);
        return this;
    }

    public WorksDetailsPage clickCorporateCheckBox(boolean value) {
        if (value) {
            setRetailCheckBox();
        } else {
            setCorporateCheckBox();
        }
        return this;
    }

    private WorksDetailsPage setRetailCheckBox() {
        retailCheckBox.click();
        return this;
    }

    private WorksDetailsPage setCorporateCheckBox() {
        corporateCheckBox.click();
        return this;
    }

    public WorksDetailsPage clickCancelableByCustomer(boolean value) {
        if (value) {
            setCancelableByCustomerYesCheckBox();
        } else {
            setCancelableByCustomerNoCheckBox();
        }
        return this;
    }

    private WorksDetailsPage setCancelableByCustomerYesCheckBox() {
        cancelableByCustomerYesCheckBox.click();
        return this;
    }

    private WorksDetailsPage setCancelableByCustomerNoCheckBox() {
        cancelableByCustomerNoCheckBox.click();
        return this;
    }

    public WorksPage clickSaveButton() {
        saveButton.click();
        return new WorksPage(driver);
    }
}

