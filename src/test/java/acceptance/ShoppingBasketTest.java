package acceptance;

import entities.OrderProduct;
import entities.ShoppingBasket;
import entities.UserID;
import exceptions.NoBasketException;
import org.junit.jupiter.api.Test;
import respositories.InMemoryShoppingBasketRepository;
import respositories.ShoppingBasketRepository;
import services.ShoppingBasketService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShoppingBasketTest {
    private static final UserID USER_ID = new UserID();

    @Test
    void should_not_create_a_shopping_basket_before_user_has_added_an_item() {
        ShoppingBasketRepository shoppingBasketRepository = new InMemoryShoppingBasketRepository();
        ShoppingBasketService shoppingBasketService = new ShoppingBasketService(shoppingBasketRepository);
        assertThrows(NoBasketException.class, () -> shoppingBasketService.basketFor(USER_ID));
    }

    @Test
    void returns_created_date_products_subtotals_and_total_for_users_basket() throws NoBasketException {
        ShoppingBasketRepository shoppingBasketRepository = new InMemoryShoppingBasketRepository();
        ShoppingBasketService shoppingBasketService = new ShoppingBasketService(shoppingBasketRepository);
        LocalDate createdDate = LocalDate.of(2020, 9, 22);

        ShoppingBasket basket = shoppingBasketService.basketFor(USER_ID);

        assertEquals(2, basket.getProducts().size());

        OrderProduct hobbit = basket.getProducts().get(0);
        assertEquals(2, hobbit.getQuantity());
        assertEquals(5, hobbit.getProductUnitCost());
        assertEquals(10, hobbit.getOrderProductTotal());

        OrderProduct breakingBad = basket.getProducts().get(1);
        assertEquals(5, breakingBad.getQuantity());
        assertEquals(7, breakingBad.getProductUnitCost());
        assertEquals(14, breakingBad.getOrderProductTotal());

        assertEquals(createdDate, basket.getCreatedAt());
        assertEquals(45, basket.getTotal());
    }
}
