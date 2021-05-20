package tests;

import helpers.DBHelper;
import helpers.PropertiesUtils;
import models.RegisterUserModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AdminBasePage;
import pages.UserBasePage;
import pages.customers.CustomersPage;

public class UserCreationTest extends TestRunner {

    @DataProvider
    public Object[][] registerRetailUserProvider() {
        return new Object[][] {
                {new RegisterUserModel("user13", "qwerty123", "John", "Smith", "example@lol.com", "123456789", "ul. Szara 10a", "55-888", "Lviv", "retail")}
        };
    }

    @Test(dataProvider = "registerRetailUserProvider")
    public void verifyRetailUserCreation(RegisterUserModel registerUserModel) {
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

        PropertiesUtils propertiesUtils = PropertiesUtils.getInstance("C:\\Users\\volod\\OneDrive\\Рабочий стол\\BK\\ui-scheduler\\src\\main\\resources\\users.properties");
        propertiesUtils.setProperty("user.username", registerUserModel.getUserName());

        UserBasePage userBasePage = loadApplication("User");
        Assert.assertTrue(userBasePage.getLoggedAsWebElement().contains(registerUserModel.getUserName()),
                "Username is different from " + registerUserModel.getUserName());

        //log out
        userBasePage.clickLogOutWebElement();

        AdminBasePage adminBasePage = loadApplication("Admin");
        CustomersPage customersPage = adminBasePage.getCustomersPage();
        Assert.assertTrue(customersPage.isElementPresentInTheTable(String.format("%s %s",
                registerUserModel.getFirstName(), registerUserModel.getLastName())));
        Assert.assertTrue(customersPage.isElementPresentInTheTable(registerUserModel.getUserType()));
        Assert.assertTrue(customersPage.isElementPresentInTheTable(registerUserModel.getEmail()));

        DBHelper dbHelper = DBHelper.getInstance();
        Assert.assertTrue(dbHelper.isObjectPresentInUsersTable(registerUserModel));

    }
}
