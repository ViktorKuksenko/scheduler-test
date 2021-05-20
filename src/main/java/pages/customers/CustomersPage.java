package pages.customers;

import helpers.TableUtils;
import helpers.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.APTSElement;
import pages.TopPage;
import pages.works.WorksDetailsPage;
import pages.works.WorksPage;

import java.util.List;

public class CustomersPage extends TopPage {

    private WebDriver driver;
    private TableUtils tableUtils;

    public CustomersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        tableUtils = new TableUtils(driver,".table.text-center");
    }

    public boolean isElementPresentInTheTable(String elementToFind) {
        return tableUtils.isElementPresentInTheTable(elementToFind, "table");
    }

    public CustomerDetailsPage clickDetailsButtonByTableRow(long tableRow) {
        String tableRowElementXpathExp = String.format("//tbody/tr[%d]/td/a[text()='Details']", tableRow);
        getAPTSElement(By.xpath(tableRowElementXpathExp)).click();
        return new CustomerDetailsPage(driver);
    }

    public CustomersPage clickDeleteButtonByTableRowNumber(long tableRow) {
        String tableRowElementXpathExp = String.format("//tr[%d]/td/form/button[text()='Delete']", tableRow);
        APTSElement deleteButton = getAPTSElement(By.xpath(tableRowElementXpathExp)).waitForElementToBeClickable().click();
        WaitUtils.waitForStalenessOfElementLocated(driver, deleteButton.getWebElement());
        return this;
    }


    public CustomersPage clickDeleteButtonByTableRowName(String name) {
        long positionOfTableRow = 0L;

        List<String> listColumns = tableUtils.getListOfColumnValuesByColumnName("Name" ,"thead-dark", "table");
        for (int i = 0; i < listColumns.size(); i++) {
            if (name.equals(listColumns.get(i))) {
                positionOfTableRow = i + 1;
            }
        }
//        System.out.println(positionOfTableRow);
        return clickDeleteButtonByTableRowNumber(positionOfTableRow);
    }
}
