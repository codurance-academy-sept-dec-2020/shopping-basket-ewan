package repositories;

import entities.BasketID;
import entities.OrderProduct;
import entities.Product;
import entities.ProductID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryOrderProductRepositoryShould {
    private static final BasketID BASKET_ID = new BasketID();
    private static final int QUANTITY = 1;
    private static final Product PRODUCT = new Product("product", new ProductID(), 5);
    private static final BasketID OTHER_BASKET_ID = new BasketID();
    private InMemoryOrderProductRepository repo;

    @BeforeEach
    void setUp() {
        repo = new InMemoryOrderProductRepository();
    }

    @Test
    void returns_no_order_products_for_basket_when_none_added() {
        List<OrderProduct> orderProducts = repo.getByBasketID(BASKET_ID);
        assertTrue(orderProducts.isEmpty());
    }

    @Test
    void returns_order_products_for_given_basket() {
        repo.create(BASKET_ID, PRODUCT, QUANTITY);
        List<OrderProduct> orderProducts = repo.getByBasketID(BASKET_ID);
        assertEquals(1, orderProducts.size());
        OrderProduct orderProduct = orderProducts.get(0);
        assertEquals(BASKET_ID, orderProduct.getBasketID());
        assertEquals(QUANTITY, orderProduct.getQuantity());
        assertEquals(PRODUCT.getName(), orderProduct.getProductName());
    }

    @Test
    void does_not_return_order_products_for_different_basket() {
        repo.create(BASKET_ID, PRODUCT, QUANTITY);
        List<OrderProduct> orderProducts = repo.getByBasketID(OTHER_BASKET_ID);
        assertTrue(orderProducts.isEmpty());
    }
}
