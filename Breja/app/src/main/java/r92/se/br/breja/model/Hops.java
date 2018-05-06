package r92.se.br.breja.model;

public class Hops {

    private String name;
    private Amount amount;
    private String add;
    private String attribute;

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

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "\t" + "Name: " + name + "\n\t" + "Amount: " + amount + "\n\t" + "Add: " + add + "\n\n";
    }
}
