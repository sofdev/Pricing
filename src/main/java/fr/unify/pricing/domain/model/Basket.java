package fr.unify.pricing.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
public class Basket {

    @Builder.Default
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public double calculateSubtotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

}
