package fr.unify.pricing.domain.service;

import fr.unify.pricing.domain.model.Basket;

public class SoupDiscount implements Discount {

    @Override
    public double applyDiscount(Basket basket) {
        long soupCount = basket.getProducts().stream()
                .filter(product -> product.getName().equalsIgnoreCase("Soup"))
                .count();
        long breadCount = basket.getProducts().stream()
                .filter(product -> product.getName().equalsIgnoreCase("Bread"))
                .count();

        return (soupCount >= 2 && breadCount >= 1) ? 0.40 : 0;
    }

    @Override
    public String getDescription() {
        return "Bread half price with 2 soups";
    }
}
