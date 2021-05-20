package helpers;

import models.DetailsPageModel;
import models.RegisterUserModel;
import pages.works.WorksDetailsPage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    private Connection connection;
    private Statement statement;

    private DBHelper() {
        connection = ConnFactory.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static DBHelper getInstance() {
        return new DBHelper();
    }
    public boolean isObjectPresentInWorksTable(DetailsPageModel detailsPageModel) {
        System.out.println(detailsPageModel.toString());
        String query = "SELECT * FROM works";
        List<DetailsPageModel> detailsPageModels = new ArrayList<>();
        boolean isObjectPresent = false;
        try {
            ResultSet resultSet = executeQuery(query, statement);
            while (resultSet.next()) {
                DetailsPageModel worksDetailsPage = new DetailsPageModel();
                worksDetailsPage.setName(resultSet.getString("name"));
                worksDetailsPage.setPrice(resultSet.getString("price"));
                worksDetailsPage.setDuration(resultSet.getString("duration"));
                worksDetailsPage.setDescription(resultSet.getString("description"));
                detailsPageModels.add(worksDetailsPage);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        for (DetailsPageModel pageModel: detailsPageModels) {
            System.out.println(pageModel.toString());
            if (pageModel.getName().equals(detailsPageModel.getName()) &&
                    pageModel.getPrice().equals(detailsPageModel.getPrice()) &&
                    pageModel.getDuration().equals(detailsPageModel.getDuration()) &&
                    pageModel.getDescription().equals(detailsPageModel.getDescription())) {
                isObjectPresent = true;
            }
        }

        return isObjectPresent;
    }

    public boolean isObjectPresentInUsersTable(RegisterUserModel registerModel) {
        String query = "SELECT * FROM users";
        List<RegisterUserModel> registerUserModels = new ArrayList<>();
        boolean isObjectPresent = false;
        try {
            ResultSet resultSet = executeQuery(query, statement);
            while (resultSet.next()) {
                RegisterUserModel registerUserModel = new RegisterUserModel();
                registerUserModel.setUserName(resultSet.getString("username"));
                registerUserModel.setFirstName(resultSet.getString("first_name"));
                registerUserModel.setLastName(resultSet.getString("last_name"));
                registerUserModel.setEmail(resultSet.getString("email"));
                registerUserModel.setMobile(resultSet.getString("mobile"));
                registerUserModel.setStreet(resultSet.getString("street"));
                registerUserModel.setCity(resultSet.getString("city"));
                registerUserModel.setPostCode(resultSet.getString("postcode"));
                registerUserModels.add(registerUserModel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        for (RegisterUserModel registerUserModel: registerUserModels) {
//            System.out.println(pageModel.toString());
            if (registerModel.getUserName().equals(registerUserModel.getUserName()) &&
                    registerModel.getFirstName().equals(registerUserModel.getFirstName()) &&
                    registerModel.getLastName().equals(registerUserModel.getLastName()) &&
                    registerModel.getEmail().equals(registerUserModel.getEmail()) &&
                    registerModel.getMobile().equals(registerUserModel.getMobile()) &&
                    registerModel.getStreet().equals(registerUserModel.getStreet()) &&
                    registerModel.getCity().equals(registerUserModel.getCity()) &&
                    registerModel.getPostCode().equals(registerUserModel.getPostCode())) {
                isObjectPresent = true;
            }
        }

        return isObjectPresent;
    }

    private ResultSet executeQuery(String query, Statement statement) throws SQLException {
        return statement.executeQuery(query);
    }
}
