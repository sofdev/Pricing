package fr.unify.pricing.application;

import fr.unify.pricing.domain.model.Basket;
import fr.unify.pricing.domain.service.Discount;

import java.util.List;

public class PriceCalculatorService {

    private List<Discount> discounts;

    public PriceCalculatorService(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public String calculatePrice(Basket basket) {
        double subtotal = basket.calculateSubtotal();

        StringBuilder receipt = new StringBuilder();
        receipt.append(String.format("\nSubtotal: £%.2f\n", subtotal));

        // calculate discount
        double totalDiscount = 0;
        for (Discount discount : discounts) {
            double discountValue = discount.applyDiscount(basket);
            if(discountValue > 0){
                receipt.append(discount.getDescription() + ": -£" + String.format("%.2f \n", discountValue));
                totalDiscount += discountValue;
            }

        }

        // final price
        double total = subtotal - totalDiscount;

        if(totalDiscount == 0){
            receipt.append("(No offers available)\n");
        }

        receipt.append(String.format("Total: £%.2f\n", total));

        return receipt.toString();
    }
}
