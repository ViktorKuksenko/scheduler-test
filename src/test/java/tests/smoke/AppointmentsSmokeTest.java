package tests.smoke;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminBasePage;
import tests.AppointmentsSchedulerTestRunner;

public class AppointmentsSmokeTest extends AppointmentsSchedulerTestRunner {

    @Test
    public void isPageTitlePresent() {
        AdminBasePage adminBasePage = loadApplication("Admin");
        Assert.assertTrue(adminBasePage.isPresentPageTitle());
    }


}
