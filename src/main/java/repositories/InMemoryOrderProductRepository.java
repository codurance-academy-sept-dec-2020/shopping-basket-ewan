package repositories;

import entities.BasketID;
import entities.OrderProduct;
import entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryOrderProductRepository implements OrderProductRepository {
    private List<OrderProduct> orderProducts;

    public InMemoryOrderProductRepository() {
        orderProducts = new ArrayList<>();
    }

    public void create(BasketID basketID, Product product, int quantity) {
        orderProducts.add(new OrderProduct(product, basketID, quantity));
    }

    public List<OrderProduct> getByBasketID(BasketID basketId) {
        return orderProducts.stream().filter(op -> op.getBasketID().equals(basketId)).collect(Collectors.toList());
    }
}
