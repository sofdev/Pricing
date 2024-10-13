package fr.unify.pricing.domain.model;

import fr.unify.pricing.domain.model.Basket;
import fr.unify.pricing.domain.model.Product;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasketTest {



    @Test
    void testAddProduct() {

        Basket basket = Basket.builder().build();
        basket.addProduct(new Product("Apples", 1.00));
        basket.addProduct(new Product("Milk", 1.30));

        assertEquals(2, basket.getProducts().size());
    }

    @Test
    void testSubtotal() {
        Basket basket = Basket.builder().build();
        basket.addProduct(new Product("Apples", 1.00));
        basket.addProduct(new Product("Milk", 1.30));

        double subtotal = basket.calculateSubtotal();
        assertEquals(2.30, subtotal, 0.01);
    }

}
