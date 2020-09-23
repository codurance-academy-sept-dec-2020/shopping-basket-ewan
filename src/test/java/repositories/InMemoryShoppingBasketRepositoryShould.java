package repositories;

import entities.DateCreator;
import entities.ShoppingBasket;
import entities.UserID;
import exceptions.NoBasketException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryShoppingBasketRepositoryShould {
    private static final UserID USER_ID = new UserID();
    private static final UserID OTHER_USER_ID = new UserID();
    private InMemoryShoppingBasketRepository repo;

    @BeforeEach
    void setUp() {
        repo = new InMemoryShoppingBasketRepository(new DateCreator());
    }

    @Test
    void throws_exception_if_no_basket_for_user() {
        assertThrows(NoBasketException.class, () -> repo.getBasketFor(USER_ID));
    }

    @Test
    void returns_user_basket_if_exists() throws NoBasketException {
        repo.findOrCreateFor(USER_ID);
        ShoppingBasket basket = repo.getBasketFor(USER_ID);
        assertNotNull(basket);
    }

    @Test
    void creates_new_basket_if_does_not_exist_yet() {
        ShoppingBasket basket = repo.findOrCreateFor(USER_ID);
        assertNotNull(basket);
    }

    @Test
    void returns_same_basket_if_already_exists_for_user() {
        ShoppingBasket basket = repo.findOrCreateFor(USER_ID);
        ShoppingBasket secondRequestedBasket = repo.findOrCreateFor(USER_ID);
        assertEquals(basket, secondRequestedBasket);
    }

    @Test
    void returns_different_basket_if_different_users_with_baskets() {
        ShoppingBasket basket = repo.findOrCreateFor(USER_ID);
        ShoppingBasket otherUsersBasket = repo.findOrCreateFor(OTHER_USER_ID);
        assertNotEquals(basket, otherUsersBasket);
    }
}
