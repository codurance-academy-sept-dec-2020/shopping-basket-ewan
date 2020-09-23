package respositories;

import entities.ShoppingBasket;
import entities.UserID;
import exceptions.NoBasketException;

public interface ShoppingBasketRepository {
    ShoppingBasket getBasketFor(UserID userId) throws NoBasketException;
}
