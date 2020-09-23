package acceptance;

import entities.*;
import exceptions.NoBasketException;
import exceptions.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.InMemoryOrderProductRepository;
import repositories.InMemoryProductRepository;
import repositories.InMemoryShoppingBasketRepository;
import repositories.ShoppingBasketRepository;
import services.OrderProductService;
import services.ShoppingBasketService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShoppingBasketTest {
    private static final UserID USER_ID = new UserID();
    private static final Product HOBBIT = new Product("The Hobbit", new ProductID(), 5);
    private static final Product BREAKING_BAD = new Product("Breaking Bad", new ProductID(), 7);
    private ShoppingBasketService shoppingBasketService;

    @Mock
    private DateCreator dateCreator;

    @BeforeEach
    void setUp() {
        InMemoryProductRepository productRepository = new InMemoryProductRepository(List.of(HOBBIT, BREAKING_BAD));
        InMemoryOrderProductRepository orderProductRepository = new InMemoryOrderProductRepository();
        OrderProductService orderProductService = new OrderProductService(productRepository, orderProductRepository);
        ShoppingBasketRepository shoppingBasketRepository = new InMemoryShoppingBasketRepository(dateCreator);
        shoppingBasketService = new ShoppingBasketService(shoppingBasketRepository, orderProductService);
    }

    @Test
    void should_not_create_a_shopping_basket_before_user_has_added_an_item() {
        assertThrows(NoBasketException.class, () -> shoppingBasketService.basketFor(USER_ID));
    }

    @Test
    void returns_created_date_products_subtotals_and_total_for_users_basket() throws NoBasketException, ProductNotFoundException {
        LocalDate createdDate = LocalDate.of(2020, 9, 22);
        when(dateCreator.now()).thenReturn(createdDate);

        int hobbitQuantity = 2;
        int breakingBadQuantity = 5;
        shoppingBasketService.addItem(USER_ID, HOBBIT.getID(), hobbitQuantity);
        shoppingBasketService.addItem(USER_ID, BREAKING_BAD.getID(), breakingBadQuantity);
        ShoppingBasket basket = shoppingBasketService.basketFor(USER_ID);

        assertEquals(2, basket.getOrderProducts().size());

        OrderProduct hobbit = basket.getOrderProducts().get(0);
        assertEquals(hobbitQuantity, hobbit.getQuantity());
        assertEquals(5, hobbit.getProductUnitCost());
        assertEquals(10, hobbit.getOrderProductTotal());

        OrderProduct breakingBad = basket.getOrderProducts().get(1);
        assertEquals(breakingBadQuantity, breakingBad.getQuantity());
        assertEquals(7, breakingBad.getProductUnitCost());
        assertEquals(35, breakingBad.getOrderProductTotal());

        assertEquals(createdDate, basket.getCreatedAt());
        assertEquals(45, basket.getTotal());
    }
}
