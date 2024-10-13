package fr.unify.pricing;

import fr.unify.pricing.application.PriceCalculatorService;
import fr.unify.pricing.domain.model.Basket;
import fr.unify.pricing.domain.model.Product;
import fr.unify.pricing.domain.service.AppleDiscount;
import fr.unify.pricing.domain.service.Discount;
import fr.unify.pricing.domain.service.SoupDiscount;
import fr.unify.pricing.infrastructure.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class PricingApplication  implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(PricingApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PricingApplication.class, args);
    }


    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if (args.length == 0) {
            logger.info("Please add items in the basket.");
            return;
        }

        //get products catalog
        Map<String, Product> productCatalog = productRepository.getAllProducts();

        //add products to basket
        Basket basket =  Basket.builder().build();
        Arrays.stream(args).forEach(arg->{
            Product product = productCatalog.get(arg);

            if (product != null) {
                basket.addProduct(product);
            } else {
                logger.info("Product not found: " + arg);
            }
        });

        List<Discount> discounts = Arrays.asList(new AppleDiscount(), new SoupDiscount());
        PriceCalculatorService calculator = new PriceCalculatorService(discounts);
        String receipt = calculator.calculatePrice(basket);
        logger.info(receipt);
    }
}
