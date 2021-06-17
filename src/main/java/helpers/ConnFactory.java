package helpers;

import java.sql.*;
import java.util.Properties;

public class ConnFactory {

    private String dbName;
    private String user;
    private String password;
    private PropertiesUtils propertiesUtils;

    private ConnFactory() {
        propertiesUtils = new PropertiesUtils("src/main/resources/config.properties");
        dbName = propertiesUtils.getPropertyByName("db.name");
        user = propertiesUtils.getPropertyByName("db.user");
        password = propertiesUtils.getPropertyByName("db.password");
    }

    private Connection create() {
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection(String.format("jdbc:mysql://localhost/%s?useUnicode=true&serverTimezone=UTC", dbName), user, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection() {
        return new ConnFactory().create();
    }
}
