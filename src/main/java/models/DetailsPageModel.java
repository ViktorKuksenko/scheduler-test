package models;

import java.util.Objects;

public class DetailsPageModel {
    private String name;
    private String price;
    private String duration;
    private String description;
    private boolean isCorporate;
    private boolean isCancelableByCustomer;

    public DetailsPageModel(String name, String price, String duration, String description, boolean isCorporate, boolean isCancelableByCustomer) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.description = description;
        this.isCorporate = isCorporate;
        this.isCancelableByCustomer = isCancelableByCustomer;
    }

    public DetailsPageModel() {}

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCorporate() {
        return isCorporate;
    }

    public boolean isCancelableByCustomer() {
        return isCancelableByCustomer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCorporate(boolean corporate) {
        isCorporate = corporate;
    }

    public void setCancelableByCustomer(boolean cancelableByCustomer) {
        isCancelableByCustomer = cancelableByCustomer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetailsPageModel)) return false;
        DetailsPageModel pageModel = (DetailsPageModel) o;
        return isCorporate == pageModel.isCorporate &&
                isCancelableByCustomer == pageModel.isCancelableByCustomer &&
                Objects.equals(name, pageModel.name) &&
                Objects.equals(price, pageModel.price) &&
                Objects.equals(duration, pageModel.duration) &&
                Objects.equals(description, pageModel.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, duration, description, isCorporate, isCancelableByCustomer);
    }

    @Override
    public String toString() {
        return "DetailsPageModel{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", duration='" + duration + '\'' +
                ", description='" + description + '\'' +
                ", isCorporate=" + isCorporate +
                ", isCancelableByCustomer=" + isCancelableByCustomer +
                '}';
    }
}
