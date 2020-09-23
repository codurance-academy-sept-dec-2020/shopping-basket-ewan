package respositories;

import entities.ShoppingBasket;
import entities.UserID;
import exceptions.NoBasketException;

public class InMemoryShoppingBasketRepository implements ShoppingBasketRepository {

    public ShoppingBasket getBasketFor(UserID userId) throws NoBasketException {
        throw new NoBasketException();
    }

    public ShoppingBasket findOrCreateFor(UserID userId) {
        throw new UnsupportedOperationException();
    }
}
