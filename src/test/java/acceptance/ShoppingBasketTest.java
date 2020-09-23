package acceptance;

import entities.UserID;
import exceptions.NoBasketException;
import org.junit.jupiter.api.Test;
import respositories.InMemoryShoppingBasketRepository;
import respositories.ShoppingBasketRepository;
import services.ShoppingBasketService;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShoppingBasketTest {
    private static final UserID USER_ID = new UserID();

    @Test
    void should_not_create_a_shopping_basket_before_user_has_added_an_item() {
        ShoppingBasketRepository shoppingBasketRepository = new InMemoryShoppingBasketRepository();
        final ShoppingBasketService shoppingBasketService = new ShoppingBasketService(shoppingBasketRepository);
        assertThrows(NoBasketException.class, () -> shoppingBasketService.basketFor(USER_ID));
    }
}
