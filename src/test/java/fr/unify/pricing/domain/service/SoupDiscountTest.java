package fr.unify.pricing.domain.service;

import fr.unify.pricing.domain.model.Basket;
import fr.unify.pricing.domain.model.Product;
import fr.unify.pricing.domain.service.AppleDiscount;
import fr.unify.pricing.domain.service.SoupDiscount;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoupDiscountTest {

    @Test
    void testApplyDiscount() {

        Product soup = Product.builder().name("Soup").price(0.65).build();
        Product soup2 = Product.builder().name("Soup").price(0.65).build();
        Product bread = Product.builder().name("Bread").price(0.80).build();

        SoupDiscount discount = new SoupDiscount();

        double discountValue =  discount.applyDiscount(Basket.builder().products(Arrays.asList(soup, soup2 ,bread)).build());
        assertEquals(discountValue, 0.4);

    }

    @Test
    void testApplyDiscountNotOK() {

        Product soup = Product.builder().name("Soup").price(0.65).build();
        Product bread = Product.builder().name("Bread").price(0.80).build();

        SoupDiscount discount = new SoupDiscount();

        double discountValue =  discount.applyDiscount(Basket.builder().products(Arrays.asList(soup ,bread)).build());
        assertEquals(discountValue, 0);

    }
}
