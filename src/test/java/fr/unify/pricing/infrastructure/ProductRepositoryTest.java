package fr.unify.pricing.infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unify.pricing.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductRepositoryTest {


    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoadProductsFromJson() throws Exception {
        // mock JSON data
        String jsonData = "[{\"name\":\"Apples\",\"price\":1.00},{\"name\":\"Milk\",\"price\":1.30}]";
        InputStream inputStream = new ByteArrayInputStream(jsonData.getBytes());

        ObjectMapper mapper = new ObjectMapper();
        List<Product> products = mapper.readValue(inputStream, new TypeReference<List<Product>>() {});

        when(objectMapper.readValue(inputStream, new TypeReference<List<Product>>() {})).thenReturn(products);

        // Load products into the repository
        productRepository.loadProductsFromJson();

        // Test if the products loaded correctly
        Product apple = productRepository.findByName("Apples");
        assertNotNull(apple);
        assertEquals("Apples", apple.getName());
        assertEquals(1.00, apple.getPrice());

        Product milk = productRepository.findByName("Milk");
        assertNotNull(milk);
        assertEquals("Milk", milk.getName());
        assertEquals(1.30, milk.getPrice());
    }
}
