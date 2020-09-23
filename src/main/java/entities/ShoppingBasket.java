package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private BasketID basketID;
    private UserID userId;
    private List<OrderProduct> orderProducts;
    private LocalDate createdAt;

    public ShoppingBasket(UserID userId, LocalDate createdAt) {
        this.userId = userId;
        this.createdAt = createdAt;
        basketID = new BasketID();
        orderProducts = new ArrayList<>();
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public int getTotal() {
        int total = 0;
        for (OrderProduct orderProduct : orderProducts) {
            total += orderProduct.getOrderProductTotal();
        }
        return total;
    }

    public BasketID getID() {
        return basketID;
    }

    public UserID getUserId() {
        return userId;
    }

    public void addProducts(List<OrderProduct> productsForBasket) {
        orderProducts.addAll(productsForBasket);
    }
}
