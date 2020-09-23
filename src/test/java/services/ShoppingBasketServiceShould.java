package services;

import entities.UserID;
import exceptions.NoBasketException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import respositories.ShoppingBasketRepository;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ShoppingBasketServiceShould {

    @Mock
    ShoppingBasketRepository shoppingBasketRepository;

    @InjectMocks
    ShoppingBasketService service;

    @Test
    void requests_users_basket_from_repository() throws NoBasketException {
        UserID userId = new UserID();
        service.basketFor(userId);
        verify(shoppingBasketRepository).getBasketFor(userId);
    }
}
