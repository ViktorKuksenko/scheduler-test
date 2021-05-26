package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.APTSElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TableUtils {

    private WebDriver driver;
    private String tableNameCssSelector;

    public TableUtils(WebDriver driver, String cssSelector) {
        this.driver = driver;
        this.tableNameCssSelector = cssSelector;
    }


    public long getTableRowsCountByTableName() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Long) js.executeScript(String.format("return $('%s').find('tr').length", tableNameCssSelector));
    }


    public boolean isElementPresentInTheTable(String elementToFind, String rowsClassName) {
        boolean isElementPresent = false;
        long tableRowsCount = getTableRowsCountByTableName();
        for (int i = 1; i < tableRowsCount; i++) {
            List<String> listOfRowsElements =  getTableRowDataByIndexAndTbodyClass(rowsClassName, i);
            for (String element : listOfRowsElements) {
                if (elementToFind.equals(element)) {
                    isElementPresent = true;
                    break;
                }
            }
        }
        return isElementPresent;
    }

    public List<String> getListOfColumnValuesByColumnName(String theadElement, String theadClassName
            , String rowsClassName) {
        // get elements in table head
        // chek if input element contains in table head
        // get position of the element
        // get values by position
        // save found values in list
        List<String> listOfColumnValues = new ArrayList<>();
        List<String> theadRows = getTheadRowsElementsListByTheadClassName(theadClassName);
        int positionOfTheadElement = 0;
        for (int i = 0; i < theadRows.size(); i++) {
            if (theadRows.get(i).equals(theadElement)) {
                positionOfTheadElement = i;
            }
        }

        long tableRowsCount = getTableRowsCountByTableName();
        for (int i = 0; i < tableRowsCount; i++) {
            List<String> listOfRowsElements = getTableRowDataByIndexAndTbodyClass(rowsClassName, i);
            for (int j = 0; j < listOfRowsElements.size(); j++) {
                if (j == positionOfTheadElement) {
                    listOfColumnValues.add(listOfRowsElements.get(j));
                }
            }

        }
        return listOfColumnValues;
    }

    public List<String> getTableRowDataByIndexAndTbodyClass(String rowsClassName, long index) {
        long size = getTableRowsCountByTableName();
        new APTSElement(driver, By.xpath(String
                .format("//tbody/tr[contains(@class, 'tabl')][last()]", rowsClassName, size)))
                .waitForElementToBePresent()
                .waitForElementToBeVisible();
        List<WebElement> elementsInTableRow = driver.findElements(By.xpath(String
                .format("//tbody/tr[contains(@class, '%s')][%d]/td/span", rowsClassName, index)));
        return getListOfWebElementsText(elementsInTableRow);
    }

    public List<String> getListOfWebElementsText(List<WebElement> webElementList) {
        return webElementList.stream()
                .map(webElement -> webElement.getText())
                .collect(Collectors.toList());
    }

    public List<String> getTheadRowsElementsListByTheadClassName(String theadClassName) {
        List<WebElement> elementsInFirstTableRow = driver.findElements(By.xpath(String
                .format("//thead[@class='%s']/tr/th", theadClassName)));
        //thead-dark
        return getListOfWebElementsText(elementsInFirstTableRow);
    }

}
