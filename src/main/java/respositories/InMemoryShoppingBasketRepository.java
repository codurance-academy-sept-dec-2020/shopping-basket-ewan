package respositories;

import entities.ShoppingBasket;
import entities.UserID;
import exceptions.NoBasketException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryShoppingBasketRepository implements ShoppingBasketRepository {

    private List<ShoppingBasket> baskets;

    public InMemoryShoppingBasketRepository() {
        baskets = new ArrayList<>();
    }

    public ShoppingBasket getBasketFor(UserID userId) throws NoBasketException {
        throw new NoBasketException();
    }

    public ShoppingBasket findOrCreateFor(UserID userId) {
        Optional<ShoppingBasket> optionalBasket = baskets.stream().filter(b -> b.getUserId().equals(userId)).findAny();
        if (optionalBasket.isEmpty()) {
            ShoppingBasket basket = new ShoppingBasket(userId);
            baskets.add(basket);
            return basket;
        } else {
            return optionalBasket.get();
        }
    }
}
