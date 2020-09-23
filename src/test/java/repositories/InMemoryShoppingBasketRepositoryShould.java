package repositories;

import entities.UserID;
import exceptions.NoBasketException;
import org.junit.jupiter.api.Test;
import respositories.InMemoryShoppingBasketRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InMemoryShoppingBasketRepositoryShould {
    @Test
    void throws_exception_if_no_basket_for_user() {
        InMemoryShoppingBasketRepository repo = new InMemoryShoppingBasketRepository();
        UserID userID = new UserID();
        assertThrows(NoBasketException.class, () -> repo.getBasketFor(userID));
    }
}
