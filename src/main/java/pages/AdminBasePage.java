package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.appointments.AppointmentsPage;
import pages.customers.CustomersPage;
import pages.invoices.InvoicesPage;
import pages.providers.ProvidersPage;
import pages.works.WorksPage;

public class AdminBasePage extends TopPage {

    private WebDriver driver;
    private APTSElement worksPage = getAPTSElement(By.xpath(getCommonNavBarXPathExp("Works")));
    private APTSElement providersPage = getAPTSElement(By.xpath(getCommonNavBarXPathExp("Providers")));
    private APTSElement customersPage = getAPTSElement(By.xpath(getCommonNavBarXPathExp("Customers")));
    private APTSElement invoicesPage = getAPTSElement(By.xpath(getCommonNavBarXPathExp("Invoices")));

    public AdminBasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public AdminBasePage getAdminBasePage() {
        getHomePageWebElement().click();
        return this;
    }

    public AppointmentsPage getAppointmentsPage() {
        getAppointmentsPageWebElement().click();
        return new AppointmentsPage(driver);
    }

    public WorksPage getWorksPage() {
        worksPage.click();
        return new WorksPage(driver);
    }

    public ProvidersPage getProvidersPage() {
        providersPage.click();
        return new ProvidersPage(driver);
    }

    public CustomersPage getCustomersPage() {
        customersPage.click();
        return new CustomersPage(driver);
    }

    public InvoicesPage getInvoicesPage() {
        invoicesPage.click();
        return new InvoicesPage(driver);
    }
}
