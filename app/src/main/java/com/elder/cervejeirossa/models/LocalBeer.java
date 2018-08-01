package com.elder.cervejeirossa.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LocalBeer extends AbstractBeer implements Serializable{

    private int id;

    private String name;

    private String tagline;

    private String firstBrewed;

    private String description;

    private String foodPairing;

    private String brewersTips;

    private String contributedBy;

    private String ingredients;

    private String imageUrl;

    public LocalBeer(){}

    public LocalBeer(int id, String name, String tagline, String firstBrewed, String description, String foodPairing, String brewersTips, String contributedBy, String ingredients, String imageUrl) {
        this.id = id;
        this.name = name;
        this.tagline = tagline;
        this.firstBrewed = firstBrewed;
        this.description = description;
        this.foodPairing = foodPairing;
        this.brewersTips = brewersTips;
        this.contributedBy = contributedBy;
        this.ingredients = ingredients;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFoodPairing() {
        return foodPairing;
    }

    public void setFoodPairing(String foodPairing) {
        this.foodPairing = foodPairing;
    }

    public String getBrewersTips() {
        return brewersTips;
    }

    public void setBrewersTips(String brewersTips) {
        this.brewersTips = brewersTips;
    }

    public String getContributedBy() {
        return contributedBy;
    }

    public void setContributedBy(String contributedBy) {
        this.contributedBy = contributedBy;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
