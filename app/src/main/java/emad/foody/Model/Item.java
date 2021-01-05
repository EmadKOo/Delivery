package emad.foody.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable {
    String itemID;
    String category;
    String categoryID;
    String name;
    String images;
    String price;
    String description;
    String datePosted;
    String discount;
    String numOfLikes;
    String numOfDisLikes;
    String shopName;
    String reactionBefore;

    public Item() {
    }

    public Item(String itemID, String category,String categoryID, String name, String images, String price, String description, String datePosted, String discount, String numOfLikes, String numOfDisLikes, String shopName, String reactionBefore) {
        this.itemID = itemID;
        this.category = category;
        this.name = name;
        this.images = images;
        this.price = price;
        this.description = description;
        this.datePosted = datePosted;
        this.discount = discount;
        this.numOfLikes = numOfLikes;
        this.numOfDisLikes = numOfDisLikes;
        this.shopName = shopName;
        this.reactionBefore = reactionBefore;
        this.categoryID = categoryID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getNumOfLikes() {
        return numOfLikes;
    }

    public void setNumOfLikes(String numOfLikes) {
        this.numOfLikes = numOfLikes;
    }

    public String getNumOfDisLikes() {
        return numOfDisLikes;
    }

    public void setNumOfDisLikes(String numOfDisLikes) {
        this.numOfDisLikes = numOfDisLikes;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getReactionBefore() {
        return reactionBefore;
    }

    public void setReactionBefore(String reactionBefore) {
        this.reactionBefore = reactionBefore;
    }
}
