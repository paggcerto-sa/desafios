package r92.se.br.breja.model;

public class Amount {

    private String unit;
    private Float value;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "\t" + value + " " + unit;
    }
}
