package services;

import entities.ProductID;
import entities.ShoppingBasket;
import entities.UserID;
import exceptions.NoBasketException;
import respositories.ShoppingBasketRepository;

public class ShoppingBasketService {
    private ShoppingBasketRepository shoppingBasketRepository;

    public ShoppingBasketService(ShoppingBasketRepository shoppingBasketRepository) {
        this.shoppingBasketRepository = shoppingBasketRepository;
    }

    public ShoppingBasket basketFor(UserID userId) throws NoBasketException {
        return shoppingBasketRepository.getBasketFor(userId);
    }

    public void addItem(UserID userId, ProductID productID, int quanity) {
        throw new UnsupportedOperationException();
    }
}
