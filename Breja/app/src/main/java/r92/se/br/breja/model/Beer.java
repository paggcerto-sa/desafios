package r92.se.br.breja.model;

import com.google.gson.annotations.SerializedName;

public class Beer {

    private Integer id;
    private String name;
    private String tagline;
    @SerializedName("first_brewed")
    private String firstBrewed;
    private String description;
    @SerializedName("image_url")
    private String imageUrl;
    private Float abv;
    private Float ibu;
    @SerializedName("target_fg")
    private Float targetFg;
    @SerializedName("target_og")
    private Float targetOg;
    private Float ebc;
    private Float srm;
    private Float ph;
    @SerializedName("attenuation_level")
    private Float attenuationLevel;


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
}
