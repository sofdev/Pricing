package fr.unify.pricing.domain.service;

import fr.unify.pricing.domain.model.Basket;
import fr.unify.pricing.domain.model.Product;
import fr.unify.pricing.domain.service.AppleDiscount;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppleDiscountTest {

    @Test
    void testApplyDiscount() {

        Product apples = Product.builder().name("Apples").price(1.00).build();
        Product milk = Product.builder().name("Milk").price(1.30).build();

        AppleDiscount discount = new AppleDiscount();

        double discountValue =  discount.applyDiscount(Basket.builder().products(Arrays.asList(apples,milk)).build());
        assertEquals(discountValue, 0.1);

    }

    @Test
    void testApplyDiscountTwoApples() {

        Product apples = Product.builder().name("Apples").price(1.00).build();
        Product apples2 = Product.builder().name("Apples").price(1.00).build();
        AppleDiscount discount = new AppleDiscount();

        double discountValue =  discount.applyDiscount(Basket.builder().products(Arrays.asList(apples,apples2)).build());
        assertEquals(discountValue, 0.2);

    }

    @Test
    void testApplyDiscountNoApples() {

        Product milk = Product.builder().name("Milk").price(1.30).build();

        AppleDiscount discount = new AppleDiscount();

        double discountValue =  discount.applyDiscount(Basket.builder().products(Arrays.asList(milk)).build());
        assertEquals(discountValue, 0);
    }

}
