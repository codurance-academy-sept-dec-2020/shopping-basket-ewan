package services;

import entities.BasketID;
import entities.OrderProduct;
import entities.Product;
import entities.ProductID;
import exceptions.ProductNotFoundException;
import repositories.OrderProductRepository;
import repositories.ProductRepository;

import java.util.List;

public class OrderProductService {
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;

    public OrderProductService(ProductRepository productRepository, OrderProductRepository orderProductRepository) {
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
    }

    public void addProductsToBasket(BasketID basketId, ProductID productId, int quantity) throws ProductNotFoundException {
        Product product = productRepository.getByID(productId);
        orderProductRepository.create(basketId, product, quantity);
    }

    public List<OrderProduct> getProductsForBasket(BasketID basketId) {
        return orderProductRepository.getByBasketID(basketId);
    }
}
