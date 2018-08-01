package r92.se.br.breja.model;

public class Malt {

    private String name;
    private Amount amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "\t" + "Name: " + name + "\n\t" + "Amount: " + amount + "\n\n";
    }
}
