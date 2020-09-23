package services;

import entities.ProductID;
import entities.ShoppingBasket;
import entities.UserID;
import exceptions.NoBasketException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import respositories.ShoppingBasketRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShoppingBasketServiceShould {

    private static final UserID USER_ID = new UserID();
    private static final ProductID PRODUCT_ID = new ProductID();
    private static final int QUANTITY = 1;

    @Mock
    private ShoppingBasketRepository shoppingBasketRepository;
    @Mock
    private OrderProductService orderProductService;

    @InjectMocks
    private ShoppingBasketService service;

    @Test
    void requests_users_basket_from_repository() throws NoBasketException {
        UserID userId = new UserID();
        service.basketFor(userId);
        verify(shoppingBasketRepository).getBasketFor(userId);
    }

    @Test
    void adds_order_item_through_product_service_to_given_basket() {
        ShoppingBasket basket = new ShoppingBasket(USER_ID);
        when(shoppingBasketRepository.findOrCreateFor(USER_ID)).thenReturn(basket);
        service.addItem(USER_ID, PRODUCT_ID, QUANTITY);

        verify(shoppingBasketRepository).findOrCreateFor(USER_ID);
        verify(orderProductService).addProductsToBasket(basket.getBasketId(), PRODUCT_ID, QUANTITY);
    }
}
