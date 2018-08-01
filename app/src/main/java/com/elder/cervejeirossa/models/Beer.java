package com.elder.cervejeirossa.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Beer extends AbstractBeer implements Serializable{

    private int id;

    private String name;

    private String tagline;

    @SerializedName("first_brewed")
    private String firstBrewed;

    private String description;

    @SerializedName("food_pairing")
    private List<String> foodPairing;

    @SerializedName("brewers_tips")
    private String brewersTips;

    @SerializedName("contributed_by")
    private String contributedBy;

    private Ingredient ingredients;

    @SerializedName("image_url")
    private String imageUrl;

    public Beer(int id, String name, String tagline, String firstBrewed, String description, List<String> foodPairing, String brewersTips, String contributedBy, Ingredient ingredients, String imageUrl) {
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

    public List<String> getFoodPairing() {
        return foodPairing;
    }

    public String getFoodPairingAsUniqueString() {

        StringBuffer sb = new StringBuffer();

        for (String food: foodPairing) {

            sb.append(food);
            sb.append("\n");

        }

        return sb.toString();
    }

    public void setFoodPairing(List<String> foodPairing) {
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

    public Ingredient getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient ingredients) {
        this.ingredients = ingredients;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
