package com.elder.cervejeirossa.models;

import java.io.Serializable;
import java.util.List;

public class Ingredient implements Serializable {

    private List<Malt> malt;

    private List<Hops> hops;

    private String yeast;

    public Ingredient(List<Malt> malt, List<Hops> hops, String yeast) {
        this.malt = malt;
        this.hops = hops;
        this.yeast = yeast;
    }

    public List<Malt> getMalt() {
        return malt;
    }

    public void setMalt(List<Malt> malt) {
        this.malt = malt;
    }

    public List<Hops> getHops() {
        return hops;
    }

    public void setHops(List<Hops> hops) {
        this.hops = hops;
    }

    public String getYeast() {
        return yeast;
    }

    public void setYeast(String yeast) {
        this.yeast = yeast;
    }

    @Override
    public String toString(){

      StringBuffer sb = new StringBuffer();

      sb.append("Malt: \n");
        for (Malt maltItem: malt) {

            sb.append(maltItem.getName());
            sb.append(" - ");
            sb.append(maltItem.getAmount().getValue());
            sb.append(" ");
            sb.append(maltItem.getAmount().getUnit());
            sb.append("\n");
        }

        sb.append("\n");
        sb.append("Hops: \n");
        for (Hops hopsItem: hops) {

            sb.append(hopsItem.getName());
            sb.append(" - ");
            sb.append(hopsItem.getAmount().getValue());
            sb.append(" ");
            sb.append(hopsItem.getAmount().getUnit());
            sb.append("\n");
            sb.append(hopsItem.getAdd());
            sb.append("\n");
            sb.append(hopsItem.getAttribute());
            sb.append("\n");
        }

        sb.append("Yeast: ");
        sb.append(yeast);

        return sb.toString();
    }
}
