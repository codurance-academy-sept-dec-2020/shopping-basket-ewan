package repositories;

import entities.Product;
import entities.ProductID;
import exceptions.ProductNotFoundException;

public interface ProductRepository {
    Product getByID(ProductID productId) throws ProductNotFoundException;
}
