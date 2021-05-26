package tests.admin;

import models.DetailsPageModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AdminBasePage;
import pages.works.WorksDetailsPage;
import pages.works.WorksPage;
import tests.TestRunner;

import java.util.Random;


public class WorksTest extends TestRunner {

    private static long tableRows = 1L;

    @DataProvider
    public Object[][] detailsPageDataProvider() {
        return new Object[][]{
                {new DetailsPageModel("Simple name1", "10.00", "100", "description!!!", false, true)},
                {new DetailsPageModel("Simple name2", "20.00", "200", "description!!!", false, true)},
                {new DetailsPageModel("Simple name3", "30.00", "300", "description!!!", false, true)},
                {new DetailsPageModel("Simple name4", "40.00", "400", "description!!!", false, true)},
                {new DetailsPageModel("Simple name5", "50.00", "500", "description!!!", false, true)}
        };
    }

    @Test(dataProvider = "detailsPageDataProvider", priority = 1)
    public void verifyPresenceOfElementsInTheTableAfterFillingForm(DetailsPageModel detailsPageModel) {
        AdminBasePage adminBasePage = loadApplication("Admin");

        WorksPage worksPage = adminBasePage.getWorksPage();

        createWork(worksPage, detailsPageModel);

        Assert.assertTrue(worksPage.isElementPresentInTheTable(detailsPageModel.getName()));
        Assert.assertTrue(worksPage.isElementPresentInTheTable(detailsPageModel.getPrice()));
        Assert.assertTrue(worksPage.isElementPresentInTheTable(detailsPageModel.getDuration()));

        boolean isRecordPresentInTheTable = dbHelper.isObjectPresentInWorksTable(detailsPageModel);

        Assert.assertTrue(isRecordPresentInTheTable, "record is not saved to database after filling form");

        //clean up
        deleteWorks(worksPage, detailsPageModel);

        //log out
        worksPage.clickLogOutWebElement();
    }

    @Test(dataProvider = "detailsPageDataProvider", priority = 2)
    public void verifyDeletionOfElements(DetailsPageModel detailsPageModel) {
        AdminBasePage adminBasePage = loadApplication("Admin");

        WorksPage worksPage = adminBasePage.getWorksPage();

        createWork(worksPage, detailsPageModel);

        deleteWorks(worksPage, detailsPageModel);

        Assert.assertFalse(worksPage.isElementPresentInTheTable(detailsPageModel.getName()));
        Assert.assertFalse(worksPage.isElementPresentInTheTable(detailsPageModel.getPrice()));
        Assert.assertFalse(worksPage.isElementPresentInTheTable(detailsPageModel.getDuration()));

        boolean isRecordPresentInTheTable = dbHelper.isObjectPresentInWorksTable(detailsPageModel);

        Assert.assertFalse(isRecordPresentInTheTable, "record is not deleted from database after clicking delete button");

        //log out
        worksPage.clickLogOutWebElement();
    }

    @Test(dataProvider = "detailsPageDataProvider", priority = 3)
    public void verifyUpdatingTableAfterClickingDetailsButton(DetailsPageModel detailsPageModel) {
        /**
         * create new work
         * get size of table records
         * click details button by table row
         * change table items
         * verify changes  ui and db
         */
        AdminBasePage adminBasePage = loadApplication("Admin");
        WorksPage worksPage = adminBasePage.getWorksPage();
        createWork(worksPage, detailsPageModel);
        String newName = createRandomValueWithInteger(detailsPageModel.getName());
        String newPrice = createRandomValueWithDouble();
        String newDuration = createRandomValueWithInteger();
        worksPage.clickDetailsButtonByTableRow(tableRows)
                .setNameTextField(newName)
                .setPriceTextField(newPrice)
                .setDurationTextField(newDuration)
                .clickCorporateCheckBox(detailsPageModel.isCorporate())
                .clickCancelableByCustomer(detailsPageModel.isCancelableByCustomer())
                .clickSaveButton();
        tableRows++;
        Assert.assertTrue(worksPage.isElementPresentInTheTable(newName));
        Assert.assertTrue(worksPage.isElementPresentInTheTable(newPrice));
        Assert.assertTrue(worksPage.isElementPresentInTheTable(newDuration));
        detailsPageModel.setName(newName);
        detailsPageModel.setPrice(newPrice);
        detailsPageModel.setDuration(newDuration);
        boolean isRecordPresentInTheTable = dbHelper.isObjectPresentInWorksTable(detailsPageModel);
        Assert.assertTrue(isRecordPresentInTheTable);

        //clean up
        deleteWorks(worksPage, detailsPageModel);

        //log out
        worksPage.clickLogOutWebElement();
    }

    private void createWork(WorksPage worksPage, DetailsPageModel detailsPageModel) {
        WorksDetailsPage worksDetailsPage = worksPage.clickAddNewWorkButton();
        worksDetailsPage.setNameTextField(detailsPageModel.getName())
                .setPriceTextField(detailsPageModel.getPrice())
                .setDurationTextField(detailsPageModel.getDuration())
                .setDescriptionTextField(detailsPageModel.getDescription())
                .clickCorporateCheckBox(detailsPageModel.isCorporate())
                .clickCancelableByCustomer(detailsPageModel.isCancelableByCustomer())
                .clickSaveButton();
    }

    private void deleteWorks(WorksPage worksPage, DetailsPageModel detailsPageModel) {
        worksPage.clickDeleteButtonByTableRowName(detailsPageModel.getName());
    }

    private String createRandomValueWithInteger(String name) {
        Random random = new Random();
        return String.format(name + "_%d", random.nextInt(9999));
    }

}
