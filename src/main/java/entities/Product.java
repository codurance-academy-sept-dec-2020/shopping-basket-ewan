package entities;

public class Product {
    public final int unitCost;
    private ProductID iD;
    private String name;

    public Product(String name, ProductID iD, int unitCost) {
        this.unitCost = unitCost;
        this.iD = iD;
        this.name = name;
    }

    public ProductID getID() {
        return iD;
    }

    public String getName() {
        return name;
    }
}
