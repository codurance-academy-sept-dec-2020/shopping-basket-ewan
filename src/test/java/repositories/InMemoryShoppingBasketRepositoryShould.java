package repositories;

import entities.ShoppingBasket;
import entities.UserID;
import exceptions.NoBasketException;
import org.junit.jupiter.api.Test;
import respositories.InMemoryShoppingBasketRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InMemoryShoppingBasketRepositoryShould {
    private static final UserID USER_ID = new UserID();

    @Test
    void throws_exception_if_no_basket_for_user() {
        InMemoryShoppingBasketRepository repo = new InMemoryShoppingBasketRepository();
        UserID userID = new UserID();
        assertThrows(NoBasketException.class, () -> repo.getBasketFor(userID));
    }

    @Test
    void creates_new_basket_if_does_not_exist_yet() {
        InMemoryShoppingBasketRepository repo = new InMemoryShoppingBasketRepository();
        ShoppingBasket basket = repo.findOrCreateFor(USER_ID);
        assertNotNull(basket);
    }
}
