package repositories;

import entities.BasketID;
import entities.OrderProduct;
import entities.Product;

import java.util.List;

public interface OrderProductRepository {
    void create(BasketID basketId, Product product, int quantity);

    List<OrderProduct> getByBasketID(BasketID basketId);
}
