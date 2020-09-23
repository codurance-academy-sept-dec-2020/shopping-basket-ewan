package repositories;

import entities.Product;
import entities.ProductID;
import exceptions.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository {
    private final List<Product> products;

    public InMemoryProductRepository(List<Product> products) {
        this.products = products;
    }

    public Product getByID(ProductID productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = products.stream().filter(p -> p.getID().equals(productId)).findAny();
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }

        throw new ProductNotFoundException();
    }
}
