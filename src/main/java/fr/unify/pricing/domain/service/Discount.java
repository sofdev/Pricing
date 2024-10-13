package fr.unify.pricing.domain.service;


import fr.unify.pricing.domain.model.Basket;

public interface Discount {
    double applyDiscount(Basket basket);
    String getDescription();
}
