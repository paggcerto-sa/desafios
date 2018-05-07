package r92.se.br.breja.model;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import r92.se.br.breja.bean.ItemDetail;
import r92.se.br.breja.interfaces.HideField;
import r92.se.br.breja.interfaces.ViewLabel;
import r92.se.br.breja.util.Util;

public class Beer {

    @HideField
    private Integer id;

    @ViewLabel(value = "Name")
    private String name;

    @ViewLabel(value = "Tagline")
    private String tagline;

    @ViewLabel(value = "First Brewed")
    @SerializedName("first_brewed")
    private String firstBrewed;

    @ViewLabel(value = "Description")
    private String description;

    @HideField
    @SerializedName("image_url")
    private String imageUrl;

    @ViewLabel(value = "ABV")
    private Float abv;

    @ViewLabel(value = "IBU")
    private Float ibu;

    @HideField
    @SerializedName("target_fg")
    private Float targetFg;

    @HideField
    @SerializedName("target_og")
    private Float targetOg;

    @ViewLabel(value = "EBC")
    private Float ebc;

    @ViewLabel(value = "SRM")
    private Float srm;

    @ViewLabel(value = "PH")
    private Float ph;

    @HideField
    @SerializedName("attenuation_level")
    private Float attenuationLevel;

    @ViewLabel(value = "Ingredients")
    @SerializedName("ingredients")
    private Ingredients ingredients;

    public List<ItemDetail> getListForDetail(){
        List<ItemDetail> list = new ArrayList<>();

        for (Field field: Beer.class.getDeclaredFields()) {
            field.setAccessible(true);
            if(!field.isAnnotationPresent(HideField.class) && field.isAnnotationPresent(ViewLabel.class)){
                try {
                    ItemDetail itemDetail = new ItemDetail(field.getAnnotation(ViewLabel.class).value(), field.get(this).toString());
                    list.add(itemDetail);
                } catch (IllegalAccessException e) {
                    Util.log("error: " + field.getName());
                }
            }
        }

        return list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Float getAbv() {
        return abv;
    }

    public void setAbv(Float abv) {
        this.abv = abv;
    }

    public Float getSrm() {
        return srm;
    }

    public void setSrm(Float srm) {
        this.srm = srm;
    }

    public Float getPh() {
        return ph;
    }

    public void setPh(Float ph) {
        this.ph = ph;
    }

    public Float getAttenuationLevel() {
        return attenuationLevel;
    }

    public void setAttenuationLevel(Float attenuationLevel) {
        this.attenuationLevel = attenuationLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getIbu() {
        return ibu;
    }

    public void setIbu(Float ibu) {
        this.ibu = ibu;
    }

    public Float getTargetFg() {
        return targetFg;
    }

    public void setTargetFg(Float targetFg) {
        this.targetFg = targetFg;
    }

    public Float getTargetOg() {
        return targetOg;
    }

    public void setTargetOg(Float targetOg) {
        this.targetOg = targetOg;
    }

    public Float getEbc() {
        return ebc;
    }

    public void setEbc(Float ebc) {
        this.ebc = ebc;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Beer beer = (Beer) o;

        return getId().equals(beer.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
