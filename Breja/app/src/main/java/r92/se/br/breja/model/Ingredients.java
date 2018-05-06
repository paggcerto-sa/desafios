package r92.se.br.breja.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ingredients {

    @SerializedName("malt")
    private List<Malt> maltList;

    @SerializedName("hops")
    private List<Hops> hopsList;

    private String yeast;

    public List<Malt> getMaltList() {
        return maltList;
    }

    public void setMaltList(List<Malt> maltList) {
        this.maltList = maltList;
    }

    public List<Hops> getHopsList() {
        return hopsList;
    }

    public void setHopsList(List<Hops> hopsList) {
        this.hopsList = hopsList;
    }

    public String getYeast() {
        return yeast;
    }

    public void setYeast(String yeast) {
        this.yeast = yeast;
    }

    @Override
    public String toString() {
        String retorno = "Malt:\n";
        for (Malt malt : maltList) {
            retorno += malt.toString();
        }
        retorno += "Hops:\n";
        for (Hops hops : hopsList) {
            retorno += hops.toString();
        }
        retorno += "Yeast: " + this.yeast + "\n";
        return retorno;
    }
}
