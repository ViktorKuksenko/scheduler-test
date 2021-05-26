package models;

public class RegisterCorporateUserModel {
    
    private String username;
    private String password;
    private String companyName;
    private String vatNumber;
    private String contactPersonFirstName;
    private String contactPersonLastName;
    private String email;
    private String mobile;
    private String street;
    private String postCode;
    private String city;
    private String type;

    public RegisterCorporateUserModel(String username, String password, String companyName, String vatNumber, String contactPersonFirstName, String contactPersonLastName, String email, String mobile, String street, String postCode, String city, String type) {
        this.username = username;
        this.password = password;
        this.companyName = companyName;
        this.vatNumber = vatNumber;
        this.contactPersonFirstName = contactPersonFirstName;
        this.contactPersonLastName = contactPersonLastName;
        this.email = email;
        this.mobile = mobile;
        this.street = street;
        this.postCode = postCode;
        this.city = city;
        this.type = type;
    }
    
    public RegisterCorporateUserModel() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public String getContactPersonFirstName() {
        return contactPersonFirstName;
    }

    public String getContactPersonLastName() {
        return contactPersonLastName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getStreet() {
        return street;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }

    public String getType() {
        return type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public void setContactPersonFirstName(String contactPersonFirstName) {
        this.contactPersonFirstName = contactPersonFirstName;
    }

    public void setContactPersonLastName(String contactPersonLastName) {
        this.contactPersonLastName = contactPersonLastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RegisterCorporateUserModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", companyName='" + companyName + '\'' +
                ", vatNumber='" + vatNumber + '\'' +
                ", contactPersonFirstName='" + contactPersonFirstName + '\'' +
                ", contactPersonLastName='" + contactPersonLastName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", street='" + street + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
