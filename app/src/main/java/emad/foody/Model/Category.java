package emad.foody.Model;

import java.io.Serializable;

public class Category implements Serializable {

    public String categoryName;
    public String categoryImage;
    public String categoryID;

    public Category() {
    }

    public Category(String categoryName, String categoryImage, String categoryID) {
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }
}
