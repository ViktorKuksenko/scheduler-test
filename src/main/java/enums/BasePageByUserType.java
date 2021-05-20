package enums;

public enum BasePageByUserType {

    ADMIN_PAGE("Admin"),
    PROVIDER_PAGE("Provider"),
    USER_PAGE("User");

    BasePageByUserType(String user) {
        this.user = user;
    }

    private String user;

    @Override
    public String toString() {
        return user;
    }
}
