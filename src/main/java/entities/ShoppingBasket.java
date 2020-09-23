package entities;

import java.time.LocalDate;
import java.util.List;

public class ShoppingBasket {
    private BasketID basketID;

    public ShoppingBasket() {
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
}
