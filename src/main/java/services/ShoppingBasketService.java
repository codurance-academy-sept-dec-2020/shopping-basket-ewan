package services;

import entities.ProductID;
import entities.ShoppingBasket;
import entities.UserID;
import exceptions.NoBasketException;
import exceptions.ProductNotFoundException;
import repositories.ShoppingBasketRepository;

public class ShoppingBasketService {
    private ShoppingBasketRepository shoppingBasketRepository;
    private OrderProductService orderProductService;

    public ShoppingBasketService(ShoppingBasketRepository shoppingBasketRepository, OrderProductService orderProductService) {
        this.shoppingBasketRepository = shoppingBasketRepository;
        this.orderProductService = orderProductService;
    }

    public ShoppingBasket basketFor(UserID userId) throws NoBasketException {
        ShoppingBasket basket = shoppingBasketRepository.getBasketFor(userId);
        basket.addProducts(orderProductService.getProductsForBasket(basket.getID()));
        return basket;
    }

    public void addItem(UserID userId, ProductID productID, int quantity) throws ProductNotFoundException {
        ShoppingBasket basket = shoppingBasketRepository.findOrCreateFor(userId);
        orderProductService.addProductsToBasket(basket.getID(), productID, quantity);
    }
}
