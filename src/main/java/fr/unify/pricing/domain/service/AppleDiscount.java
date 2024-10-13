package fr.unify.pricing.domain.service;

import fr.unify.pricing.domain.model.Basket;
import fr.unify.pricing.domain.model.Product;

public class AppleDiscount implements Discount{

    @Override
    public double applyDiscount(Basket basket) {
        double discount = 0;
        for (Product product : basket.getProducts()) {
            if (product.getName().equals("Apples")) {
                discount += product.getPrice() * 0.10;
            }
        }
        return discount;
    }

    @Override
    public String getDescription() {
        return "Apples 10% off";
    }
}
