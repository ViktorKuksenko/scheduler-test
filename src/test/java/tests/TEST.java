package tests;

import org.testng.annotations.Test;
import pages.AdminBasePage;

public class TEST extends TestRunner{

    @Test
    public void lol() {
        AdminBasePage adminBasePage = loadApplication("Admin");
//        boolean lol = adminBasePage.getWorksPage().isElementPresentInTheTable("rgtert", ".table.text-center");

     adminBasePage.getWorksPage()
             .clickAddNewWorkButton()
             .setNameTextField("lol")
             .setPriceTextField("100")
             .setDurationTextField("60")
             .setDescriptionTextField("flgfdl")
             .clickCancelableByCustomer(true)
             .clickCorporateCheckBox(false)
             .clickSaveButton();
//        boolean lol = adminBasePage.getWorksPage().isElementPresentInTheTable( "lol", ".table.text-center", "table");
//                .getListOfColumnValuesByColumnName("Name", "thead-dark", ".table.text-center", "table");
//        System.out.println(lol);
    }
}
