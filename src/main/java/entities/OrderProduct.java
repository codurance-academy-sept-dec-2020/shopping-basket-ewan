package entities;

public class OrderProduct {
    private final Product product;
    private int quantity;
    private BasketID basketID;

    public OrderProduct(Product product, BasketID basketID, int quantity) {
        this.product = product;
        this.basketID = basketID;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getProductUnitCost() {
        return product.unitCost;
    }

    public int getOrderProductTotal() {
        return quantity * product.unitCost;
    }

    public BasketID getBasketID() {
        return basketID;
    }

    public String getProductName() {
        return product.getName();
    }
}
