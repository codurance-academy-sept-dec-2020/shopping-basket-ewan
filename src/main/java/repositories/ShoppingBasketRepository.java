package repositories;

import entities.ShoppingBasket;
import entities.UserID;
import exceptions.NoBasketException;

public interface ShoppingBasketRepository {
    ShoppingBasket getBasketFor(UserID userId) throws NoBasketException;

    ShoppingBasket findOrCreateFor(UserID userId);
}
