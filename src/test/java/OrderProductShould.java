import entities.BasketID;
import entities.OrderProduct;
import entities.Product;
import entities.ProductID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderProductShould {
    @Test
    void return_total_for_order_product() {
        int productUnitCost = 10;
        int orderProductQuantity = 3;
        int expectedOrderProductTotal = orderProductQuantity * productUnitCost;
        Product product = new Product("product name", new ProductID(), productUnitCost);
        OrderProduct orderProduct = new OrderProduct(product, new BasketID(), orderProductQuantity);
        assertEquals(expectedOrderProductTotal, orderProduct.getOrderProductTotal());
    }
}
