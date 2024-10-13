package fr.unify.pricing.infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unify.pricing.domain.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductRepository {

    private final Map<String, Product> productCatalog = new HashMap<>();

    @PostConstruct
    public void loadProductsFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>() {};
        InputStream inputStream = getClass().getResourceAsStream("/products.json");

        try {
            List<Product> products = objectMapper.readValue(inputStream, typeReference);
            for (Product product : products) {
                productCatalog.put(product.getName(), product);
            }
            System.out.println("Products loaded successfully from JSON.");
        } catch (IOException e) {
            System.out.println("Failed to load products from JSON: " + e.getMessage());
        }
    }

    public Map<String, Product> getAllProducts() {
        return productCatalog;
    }

    public Product findByName(String name) {
        return productCatalog.get(name);
    }
}
