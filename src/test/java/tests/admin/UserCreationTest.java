package tests.admin;

import helpers.PropertiesUtils;
import models.RegisterCorporateUserModel;
import models.RegisterUserModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AdminBasePage;
import pages.UserBasePage;
import pages.customers.CorporateCustomerDetailsPage;
import pages.customers.CustomerDetailsPage;
import pages.customers.CustomersPage;
import tests.AppointmentsSchedulerTestRunner;

public class UserCreationTest extends AppointmentsSchedulerTestRunner {

    private static long tableRow = 2;
    private PropertiesUtils propertiesUtils;

    public UserCreationTest() {
       propertiesUtils = PropertiesUtils.getInstance("C:\\Users\\volod\\OneDrive\\Рабочий стол\\BK\\ui-scheduler\\src\\main\\resources\\users.properties");
    }

    @DataProvider
    public Object[][] registerRetailUserProvider() {
        return new Object[][] {
                {new RegisterUserModel(String.format("user%s", createRandomValueWithInteger()), "qwerty123",
                        "Tom", "Jackson", String.format("%s@gmail.com",
                        createRandomValueWithInteger()), "123456789", String.format("ul. Example %sa",
                        createRandomValueWithInteger()),
                        String.format("00-%d", 100 + Integer.parseInt(createRandomValueWithInteger())),
                        String.format("Example_city_%s", createRandomValueWithInteger()), "retail")},
                {new RegisterUserModel(String.format("user%s", createRandomValueWithInteger()),
                        "qwerty123", "Mike", "Tomson", String.format("%s@gmail.com",
                        createRandomValueWithInteger()), "123456788", String.format("ul. Example %sa",
                        createRandomValueWithInteger()),
                        String.format("00-%d", 100 + Integer.parseInt(createRandomValueWithInteger())),
                        String.format("Example_city_%s", createRandomValueWithInteger()), "retail"),},
                {new RegisterUserModel(String.format("user%s", createRandomValueWithInteger()),
                        "qwerty123", "Lee", "Smith", String.format("%s@gmail.com",
                        createRandomValueWithInteger()), "123456288", String.format("ul. Example %sa",
                        createRandomValueWithInteger()),
                        String.format("00-%d", 100 + Integer.parseInt(createRandomValueWithInteger())),
                        String.format("Example_city_%s", createRandomValueWithInteger()), "retail"),},
                {new RegisterUserModel(String.format("user%s", createRandomValueWithInteger()),
                        "qwerty123", "Nikola", "Noname", String.format("%s@gmail.com",
                        createRandomValueWithInteger()), "124456788", String.format("ul. Example %sa",
                        createRandomValueWithInteger()),
                        String.format("00-%d", 100 + Integer.parseInt(createRandomValueWithInteger())),
                        String.format("Example_city_%s", createRandomValueWithInteger()), "retail"),},
        };
    }

    @DataProvider
    public Object[][] registerCorporateUserProvider() {
        return new Object[][] {
                {new RegisterCorporateUserModel(String.format("user%s", createRandomValueWithInteger()), "qwerty123",
                        String.format("Some_company_%s", createRandomValueWithInteger()), "0123456789", "Jane",
                        "Willson", String.format("%s@gmail.com", createRandomValueWithInteger()), "123456789",
                        String.format("ul. Example %sa", createRandomValueWithInteger()),
                        String.format("00-%d", 100 + Integer.parseInt(createRandomValueWithInteger())),
                        String.format("Example_city_%s", createRandomValueWithInteger()), "corporate")},
                {new RegisterCorporateUserModel(String.format("user%s", createRandomValueWithInteger()), "qwerty123",
                        String.format("Some_company_%s", createRandomValueWithInteger()), "0123456789", "Robert",
                        "Genero", String.format("%s@gmail.com", createRandomValueWithInteger()), "123456789",
                        String.format("ul. Example %sa", createRandomValueWithInteger()),
                        String.format("00-%d", 100 + Integer.parseInt(createRandomValueWithInteger())),
                        String.format("Example_city_%s", createRandomValueWithInteger()), "corporate")},
                {new RegisterCorporateUserModel(String.format("user%s", createRandomValueWithInteger()), "qwerty123",
                        String.format("Some_company_%s", createRandomValueWithInteger()), "0123456789", "Sebastian",
                        "Tomson", String.format("%s@gmail.com", createRandomValueWithInteger()), "123456777",
                        String.format("ul. Example %sa", createRandomValueWithInteger()),
                        String.format("00-%d", 100 + Integer.parseInt(createRandomValueWithInteger())),
                        String.format("Example_city_%s", createRandomValueWithInteger()), "corporate")},
                {new RegisterCorporateUserModel(String.format("user%s", createRandomValueWithInteger()), "qwerty123",
                        String.format("Some_company_%s", createRandomValueWithInteger()), "0123456789", "Sam",
                        "Patison", String.format("%s@gmail.com", createRandomValueWithInteger()), "123456777",
                        String.format("ul. Example %sa", createRandomValueWithInteger()),
                        String.format("00-%d", 100 + Integer.parseInt(createRandomValueWithInteger())),
                        String.format("Example_city_%s", createRandomValueWithInteger()), "corporate")},

        };
    }

    @Test(dataProvider = "registerRetailUserProvider")
    public void verifyRetailUserCreation(RegisterUserModel registerUserModel) {
        slf4JLogger.info("@Test verifyRetailUserCreation");
        registerRetailUser(registerUserModel);

        UserBasePage userBasePage = loadApplication("User");
        Assert.assertTrue(userBasePage.getLoggedAsWebElement().contains(registerUserModel.getUserName()),
                "Username is different from " + registerUserModel.getUserName());

        //log out
        userBasePage.clickLogOutWebElement();

        AdminBasePage adminBasePage = loadApplication("Admin");
        CustomersPage customersPage = adminBasePage.getCustomersPage();
        Assert.assertTrue(customersPage.isElementPresentInTheTable(String.format("%s %s",
                registerUserModel.getFirstName(), registerUserModel.getLastName())));
        Assert.assertTrue(customersPage.isElementPresentInTheTable(registerUserModel.getEmail()));

        Assert.assertTrue(dbHelper.isObjectPresentInUsersTable(registerUserModel));

//        clean up
        deleteUser(customersPage, String.format("%s %s", registerUserModel.getFirstName(),
                registerUserModel.getLastName()));

        //log out
        userBasePage.clickLogOutWebElement();
    }

    @Test(dataProvider = "registerRetailUserProvider")
    public void verifyRetailUserDeletionByAdmin(RegisterUserModel registerUserModel) {
        slf4JLogger.info("@Test verifyRetailUserDeletionByAdmin");

        registerRetailUser(registerUserModel);

        AdminBasePage adminBasePage = loadApplication("Admin");
        CustomersPage customersPage = adminBasePage.getCustomersPage();

        deleteUser(customersPage, String.format("%s %s", registerUserModel.getFirstName(),
                registerUserModel.getLastName()));


        Assert.assertFalse(customersPage.isElementPresentInTheTable(String.format("%s %s",
                registerUserModel.getFirstName(), registerUserModel.getLastName())));

        Assert.assertFalse(customersPage.isElementPresentInTheTable(registerUserModel.getEmail()));

        Assert.assertFalse(dbHelper.isObjectPresentInUsersTable(registerUserModel));

        //log out
        customersPage.clickLogOutWebElement();
    }


    @Test(dataProvider = "registerRetailUserProvider")
    public void verifyRetailUserModifyingByAdmin(RegisterUserModel registerUserModel) {
        slf4JLogger.info("@Test verifyRetailUserModifyingByAdmin");

        registerRetailUser(registerUserModel);

        AdminBasePage adminBasePage = loadApplication("Admin");
        CustomersPage customersPage = adminBasePage.getCustomersPage();

        RegisterUserModel newUser = new RegisterUserModel();
        newUser.setUserName(registerUserModel.getUserName());
        newUser.setPassword(registerUserModel.getPassword());
        newUser.setEmail(String.format("%s@gmail.com", createRandomValueWithInteger()));
        newUser.setFirstName(String.format("First_Name_%s", createRandomValueWithInteger()));
        newUser.setLastName(String.format("Last_Name_%s", createRandomValueWithInteger()));
        newUser.setMobile("123456789");
        newUser.setStreet(String.format("ul. %s", createRandomValueWithInteger()));
        newUser.setPostCode(String.format("00-%d", 100 + Integer.parseInt(createRandomValueWithInteger())));
        newUser.setUserType("retail");
        newUser.setCity(String.format("New_city_%s", createRandomValueWithInteger()));

        System.out.println(newUser.toString());

        CustomerDetailsPage customerDetailsPage = customersPage.clickDetailsButtonByTableRow(tableRow, CustomerDetailsPage.class);
        tableRow++;

        customerDetailsPage.setEmailTextField(newUser.getEmail())
                .setFirstNameTextField(newUser.getFirstName())
                .setLastNameTextField(newUser.getLastName())
                .setMobilePhoneTextField(newUser.getMobile())
                .setStreetTextField(newUser.getStreet())
                .setPostcodeTextField(newUser.getPostCode())
                .setCityTextField(newUser.getCity())
                .clickSaveButton();
        adminBasePage.getCustomersPage();

        Assert.assertTrue(customersPage.isElementPresentInTheTable(String.format("%s %s",
                newUser.getFirstName(), newUser.getLastName())));

        Assert.assertTrue(dbHelper.isObjectPresentInUsersTable(newUser));

        //clean up
        deleteUser(customersPage, String.format("%s %s", newUser.getFirstName(),
                newUser.getLastName()));
        //log out
        customersPage.clickLogOutWebElement();
    }

    @Test(dataProvider = "registerCorporateUserProvider")
    public void verifyCorporateUserCreation(RegisterCorporateUserModel registerCorporateUserModel) {
        slf4JLogger.info("@Test verifyCorporateUserCreation");

        registerCorporateUser(registerCorporateUserModel);

        UserBasePage userBasePage = loadApplication("User");
        Assert.assertTrue(userBasePage.getLoggedAsWebElement().contains(registerCorporateUserModel.getUsername()),
                "Username is different from " + registerCorporateUserModel.getUsername());

        //log out
        userBasePage.clickLogOutWebElement();

        AdminBasePage adminBasePage = loadApplication("Admin");
        CustomersPage customersPage = adminBasePage.getCustomersPage();
        Assert.assertTrue(customersPage.isElementPresentInTheTable(String.format("%s %s",
                registerCorporateUserModel.getContactPersonFirstName(),
                registerCorporateUserModel.getContactPersonLastName())));
        Assert.assertTrue(customersPage.isElementPresentInTheTable(registerCorporateUserModel.getEmail()));

        Assert.assertTrue(dbHelper.isObjectPresentInUsersTable(registerCorporateUserModel));

        //clean up
        deleteUser(customersPage, String.format("%s %s", registerCorporateUserModel.getContactPersonFirstName(),
                registerCorporateUserModel.getContactPersonLastName()));
        //log out
        userBasePage.clickLogOutWebElement();
    }

    @Test(dataProvider = "registerCorporateUserProvider")
    public void verifyCorporateUserDeletionByAdmin(RegisterCorporateUserModel registerCorporateUserModel) {
        slf4JLogger.info("@Test verifyCorporateUserDeletionByAdmin");

        registerCorporateUser(registerCorporateUserModel);

        AdminBasePage adminBasePage = loadApplication("Admin");
        CustomersPage customersPage = adminBasePage.getCustomersPage();
        deleteUser(customersPage, String.format("%s %s", registerCorporateUserModel.getContactPersonFirstName(),
                registerCorporateUserModel.getContactPersonLastName()));

        Assert.assertFalse(customersPage.isElementPresentInTheTable(String.format("%s %s",
                registerCorporateUserModel.getContactPersonFirstName(),
                registerCorporateUserModel.getContactPersonLastName())));
        Assert.assertFalse(customersPage.isElementPresentInTheTable(registerCorporateUserModel.getEmail()));

        Assert.assertFalse(dbHelper.isObjectPresentInUsersTable(registerCorporateUserModel));

        //log out
        customersPage.clickLogOutWebElement();
    }

    @Test(dataProvider = "registerCorporateUserProvider")
    public void verifyCorporateUserModifyingByAdmin(RegisterCorporateUserModel registerCorporateUserModel) {
        slf4JLogger.info("@Test verifyCorporateUserModifyingByAdmin");

        tableRow = 2;
        registerCorporateUser(registerCorporateUserModel);

        AdminBasePage adminBasePage = loadApplication("Admin");
        CustomersPage customersPage = adminBasePage.getCustomersPage();

        RegisterCorporateUserModel registerCorporateUserModel1 = new RegisterCorporateUserModel(
                String.format("user%s", createRandomValueWithInteger()), "qwerty123",
                String.format("Some_company_%s", createRandomValueWithInteger()),
                "0123456789", String.format("Username_%s", createRandomValueWithInteger()),
                String.format("Lastname_%s", createRandomValueWithInteger()),
                String.format("%s@gmail.com", createRandomValueWithInteger()), "123456777",
                String.format("ul. Example %sa", createRandomValueWithInteger()),
                String.format("00-%d", 100 + Integer.parseInt(createRandomValueWithInteger())),
                String.format("Example_city_%s", createRandomValueWithInteger()), "corporate");

        CorporateCustomerDetailsPage corporateCustomerDetailsPage = customersPage.clickDetailsButtonByTableRow(tableRow, CorporateCustomerDetailsPage.class);
        tableRow++;

        corporateCustomerDetailsPage.setEmailTextField(registerCorporateUserModel1.getEmail())
                .setCompanyNameTextField(registerCorporateUserModel1.getCompanyName())
                .setVatNumberTextField(registerCorporateUserModel1.getVatNumber())
                .setFirstNameTextField(registerCorporateUserModel1.getContactPersonFirstName())
                .setLastNameTextField(registerCorporateUserModel1.getContactPersonLastName())
                .setMobileTextField(registerCorporateUserModel1.getMobile())
                .setStreetTextField(registerCorporateUserModel1.getStreet())
                .setCityTextField(registerCorporateUserModel1.getCity())
                .setPostcodeTextField(registerCorporateUserModel1.getPostCode())
                .clickSaveButton();

        Assert.assertTrue(customersPage.isElementPresentInTheTable(String.format("%s %s",
                registerCorporateUserModel1.getContactPersonFirstName(),
                registerCorporateUserModel1.getContactPersonLastName())));
        Assert.assertTrue(customersPage.isElementPresentInTheTable(registerCorporateUserModel1.getEmail()));
        Assert.assertTrue(dbHelper.isObjectPresentInUsersTable(registerCorporateUserModel1));

        //log out
        corporateCustomerDetailsPage.clickLogOutWebElement();
    }

    private void registerRetailUser(RegisterUserModel registerUserModel) {
        loadApplication().clickRetailLink()
                .setUsernameTextField(registerUserModel.getUserName())
                .setPasswordTextField(registerUserModel.getPassword())
                .setRepeatPasswordTextField(registerUserModel.getPassword())
                .setEmailTextField(registerUserModel.getEmail())
                .setFirstNameTextField(registerUserModel.getFirstName())
                .setLastNameTextField(registerUserModel.getLastName())
                .setMobileTextField(registerUserModel.getMobile())
                .setStreetTextField(registerUserModel.getStreet())
                .setCityTextField(registerUserModel.getCity())
                .setPostCodeTextField(registerUserModel.getPostCode())
                .clickRegisterButton();

        propertiesUtils.setProperty("user.username", registerUserModel.getUserName());
    }

    private void registerCorporateUser(RegisterCorporateUserModel registerCorporateUserModel) {
        loadApplication().clickCorporateLink()
                .setUsernameTextField(registerCorporateUserModel.getUsername())
                .setPasswordTextField(registerCorporateUserModel.getPassword())
                .setRepeatPasswordTextField(registerCorporateUserModel.getPassword())
                .setCompanyNameTextField(registerCorporateUserModel.getCompanyName())
                .setVatNumberTextField(registerCorporateUserModel.getVatNumber())
                .setFirstNameTextField(registerCorporateUserModel.getContactPersonFirstName())
                .setLastNameTextField(registerCorporateUserModel.getContactPersonLastName())
                .setEmailTextField(registerCorporateUserModel.getEmail())
                .setMobileTextField(registerCorporateUserModel.getMobile())
                .setStreetTextField(registerCorporateUserModel.getStreet())
                .setPostcodeTextField(registerCorporateUserModel.getPostCode())
                .setCityTextField(registerCorporateUserModel.getCity())
                .clickRegisterButton();

        propertiesUtils.setProperty("user.username", registerCorporateUserModel.getUsername());
    }

    private void deleteUser(CustomersPage customersPage, String userName) {
        customersPage.clickDeleteButtonByTableRowName(userName);
    }
}
