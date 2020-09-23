package repositories;

import entities.DateCreator;
import entities.ShoppingBasket;
import entities.UserID;
import exceptions.NoBasketException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class InMemoryShoppingBasketRepository implements ShoppingBasketRepository {

    private List<ShoppingBasket> baskets;
    private DateCreator dateCreator;

    public InMemoryShoppingBasketRepository(DateCreator dateCreator) {
        this.dateCreator = dateCreator;
        baskets = new ArrayList<>();
    }

    public ShoppingBasket getBasketFor(UserID userId) throws NoBasketException {
        Optional<ShoppingBasket> optionalBasket = baskets.stream().filter(basketBy(userId)).findAny();
        if (optionalBasket.isPresent()) {
            return optionalBasket.get();
        }

        throw new NoBasketException();
    }

    public ShoppingBasket findOrCreateFor(UserID userId) {
        Optional<ShoppingBasket> optionalBasket = baskets.stream().filter(basketBy(userId)).findAny();
        if (optionalBasket.isPresent()) {
            return optionalBasket.get();
        } else {
            ShoppingBasket basket = new ShoppingBasket(userId, dateCreator.now());
            baskets.add(basket);
            return basket;
        }
    }

    private Predicate<ShoppingBasket> basketBy(UserID userId) {
        return b -> b.getUserId().equals(userId);
    }
}
