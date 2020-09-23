package respositories;

import entities.DateCreator;
import entities.ShoppingBasket;
import entities.UserID;
import exceptions.NoBasketException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryShoppingBasketRepository implements ShoppingBasketRepository {

    private List<ShoppingBasket> baskets;
    private DateCreator dateCreator;

    public InMemoryShoppingBasketRepository(DateCreator dateCreator) {
        this.dateCreator = dateCreator;
        baskets = new ArrayList<>();
    }

    public ShoppingBasket getBasketFor(UserID userId) throws NoBasketException {
        Optional<ShoppingBasket> optionalBasket = baskets.stream().filter(b -> b.getUserId().equals(userId)).findAny();
        if (optionalBasket.isPresent()) {
            return optionalBasket.get();
        }

        throw new NoBasketException();
    }

    public ShoppingBasket findOrCreateFor(UserID userId) {
        Optional<ShoppingBasket> optionalBasket = baskets.stream().filter(b -> b.getUserId().equals(userId)).findAny();
        if (optionalBasket.isEmpty()) {
            ShoppingBasket basket = new ShoppingBasket(userId, dateCreator.now());
            baskets.add(basket);
            return basket;
        } else {
            return optionalBasket.get();
        }
    }
}
