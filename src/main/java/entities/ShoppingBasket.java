package entities;

import java.time.LocalDate;
import java.util.List;

public class ShoppingBasket {
    private BasketID basketID;
    private UserID userId;

    public ShoppingBasket(UserID userId) {
        this.userId = userId;
        basketID = new BasketID();
    }

    public LocalDate getCreatedAt() {
        throw new UnsupportedOperationException();
    }

    public List<OrderProduct> getProducts() {
        throw new UnsupportedOperationException();
    }

    public int getTotal() {
        throw new UnsupportedOperationException();
    }

    public BasketID getBasketId() {
        return basketID;
    }

    public UserID getUserId() {
        return userId;
    }
}
