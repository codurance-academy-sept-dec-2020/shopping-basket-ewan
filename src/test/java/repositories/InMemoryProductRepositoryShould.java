package repositories;

import entities.Product;
import entities.ProductID;
import exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryProductRepositoryShould {
    private static final ProductID PRODUCT_ID = new ProductID();

    @Test
    void returns_product_by_id() throws ProductNotFoundException {
        List<Product> productList = List.of(new Product("product name", PRODUCT_ID, 5));
        InMemoryProductRepository repo = new InMemoryProductRepository(productList);
        Product product = repo.getByID(PRODUCT_ID);
        assertEquals(PRODUCT_ID, product.getID());
    }
}
