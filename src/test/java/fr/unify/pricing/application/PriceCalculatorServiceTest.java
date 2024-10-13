package fr.unify.pricing.application;

import fr.unify.pricing.domain.model.Basket;
import fr.unify.pricing.domain.model.Product;
import fr.unify.pricing.domain.service.AppleDiscount;
import fr.unify.pricing.domain.service.Discount;
import fr.unify.pricing.domain.service.SoupDiscount;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceCalculatorServiceTest {

    List<Discount> discounts = Arrays.asList(new AppleDiscount(), new SoupDiscount());
    private final PriceCalculatorService priceCalculatorService = new PriceCalculatorService(discounts);


    @Test
    void testcalculatePriceWithAppleDiscount() {

        Product apples = Product.builder().name("Apples").price(1.00).build();
        Product milk = Product.builder().name("Milk").price(1.30).build();
        Basket basket = Basket.builder().products(Arrays.asList(apples, milk)).build();

        //required retrun value:
        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append(String.format("\nSubtotal: £%.2f\n", 2.30));
        receiptBuilder.append("Apples 10% off: -£" + String.format("%.2f \n", 0.1));
        receiptBuilder.append(String.format("Total: £%.2f\n", 2.20));

        String receiptString = receiptBuilder.toString();
        String receipt = priceCalculatorService.calculatePrice(basket);

        assertEquals(receipt, receiptString);

    }

    @Test
    void testcalculatePriceWithSoupBreadDiscount() {

        Product soup = Product.builder().name("Soup").price(0.65).build();
        Product soup2 = Product.builder().name("Soup").price(0.65).build();
        Product bread = Product.builder().name("Bread").price(0.80).build();
        Basket basket = Basket.builder().products(Arrays.asList(soup, soup2, bread)).build();

        //required retrun value:
        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append(String.format("\nSubtotal: £%.2f\n", 2.10));
        receiptBuilder.append("Bread half price with 2 soups: -£" + String.format("%.2f \n", 0.4));
        receiptBuilder.append(String.format("Total: £%.2f\n", 1.70));

        String receiptString = receiptBuilder.toString();
        String receipt = priceCalculatorService.calculatePrice(basket);

        assertEquals(receipt, receiptString);

    }

    @Test
    void testcalculatePriceNoDiscount() {

        Product soup = Product.builder().name("Soup").price(0.65).build();
        Product bread = Product.builder().name("Bread").price(0.80).build();
        Basket basket = Basket.builder().products(Arrays.asList(soup, bread)).build();

        //required retrun value:
        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append(String.format("\nSubtotal: £%.2f\n", 1.45));
        receiptBuilder.append("(No offers available)\n");
        receiptBuilder.append(String.format("Total: £%.2f\n", 1.45));

        String receiptString = receiptBuilder.toString();
        String receipt = priceCalculatorService.calculatePrice(basket);

        assertEquals(receipt, receiptString);

    }
}
