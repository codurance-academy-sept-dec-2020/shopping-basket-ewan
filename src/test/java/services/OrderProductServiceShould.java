package services;

import entities.BasketID;
import entities.OrderProduct;
import entities.Product;
import entities.ProductID;
import exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.OrderProductRepository;
import repositories.ProductRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderProductServiceShould {

    private static final BasketID BASKET_ID = new BasketID();
    private static final ProductID PRODUCT_ID = new ProductID();
    private static final Product PRODUCT = new Product("product name", PRODUCT_ID, 5);
    private static final int QUANTITY = 1;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderProductRepository orderProductRepository;

    @InjectMocks
    OrderProductService service;

    @Test
    void add_requested_order_products_to_order_product_repository() throws ProductNotFoundException {
        when(productRepository.getByID(PRODUCT_ID)).thenReturn(PRODUCT);
        service.addProductsToBasket(BASKET_ID, PRODUCT_ID, QUANTITY);

        verify(productRepository).getByID(PRODUCT_ID);
        verify(orderProductRepository).create(BASKET_ID, PRODUCT, QUANTITY);
    }

    @Test
    void gets_order_products_for_given_basket_from_repo() {
        List<OrderProduct> productOrders = List.of(new OrderProduct(PRODUCT, BASKET_ID, QUANTITY));
        when(orderProductRepository.getByBasketID(BASKET_ID)).thenReturn(productOrders);
        List<OrderProduct> orderProducts = service.getProductsForBasket(BASKET_ID);

        verify(orderProductRepository).getByBasketID(BASKET_ID);
        assertEquals(1, orderProducts.size());
    }
}
