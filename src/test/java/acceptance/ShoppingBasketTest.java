package acceptance;

import entities.OrderProduct;
import entities.ProductID;
import entities.ShoppingBasket;
import entities.UserID;
import exceptions.NoBasketException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import respositories.InMemoryShoppingBasketRepository;
import respositories.ShoppingBasketRepository;
import services.OrderProductService;
import services.ShoppingBasketService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShoppingBasketTest {
    private static final UserID USER_ID = new UserID();
    private static final ProductID HOBBIT_PRODUCT_ID = new ProductID();
    private static final ProductID BREAKING_BAD_PRODUCT_ID = new ProductID();
    private ShoppingBasketService shoppingBasketService;

    @BeforeEach
    void setUp() {
        OrderProductService orderProductService = new OrderProductService();
        ShoppingBasketRepository shoppingBasketRepository = new InMemoryShoppingBasketRepository();
        shoppingBasketService = new ShoppingBasketService(shoppingBasketRepository, orderProductService);
    }

    @Test
    void should_not_create_a_shopping_basket_before_user_has_added_an_item() {
        assertThrows(NoBasketException.class, () -> shoppingBasketService.basketFor(USER_ID));
    }

    @Test
    void returns_created_date_products_subtotals_and_total_for_users_basket() throws NoBasketException {
        LocalDate createdDate = LocalDate.of(2020, 9, 22);

        int hobbitQuantity = 2;
        int breakingBadQuantity = 5;
        shoppingBasketService.addItem(USER_ID, HOBBIT_PRODUCT_ID, hobbitQuantity);
        shoppingBasketService.addItem(USER_ID, BREAKING_BAD_PRODUCT_ID, breakingBadQuantity);
        ShoppingBasket basket = shoppingBasketService.basketFor(USER_ID);

        assertEquals(2, basket.getProducts().size());

        OrderProduct hobbit = basket.getProducts().get(0);
        assertEquals(hobbitQuantity, hobbit.getQuantity());
        assertEquals(5, hobbit.getProductUnitCost());
        assertEquals(10, hobbit.getOrderProductTotal());

        OrderProduct breakingBad = basket.getProducts().get(1);
        assertEquals(breakingBadQuantity, breakingBad.getQuantity());
        assertEquals(7, breakingBad.getProductUnitCost());
        assertEquals(14, breakingBad.getOrderProductTotal());

        assertEquals(createdDate, basket.getCreatedAt());
        assertEquals(45, basket.getTotal());
    }
}
