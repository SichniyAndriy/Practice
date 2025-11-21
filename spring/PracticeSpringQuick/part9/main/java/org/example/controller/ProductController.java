package org.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Logger;
import net.datafaker.Faker;
import org.example.model.Product;
import org.example.processor.LoggedUserProcessor;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    private static final String PRODUCTS_ENDPOINT = "products";
    private static final String PRODUCTS_PAGE_HTML = "products.html";
    private static final String ADD_PRODUCTS_PAGE_HTML = "addProductForm.html";
    private final ProductService productService;
    private LoggedUserProcessor loggedUserProcessor;

    @Autowired
    public void loggedUserProcessor(LoggedUserProcessor loggedUserProcessor) {
        this.loggedUserProcessor = loggedUserProcessor;
    }

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(PRODUCTS_ENDPOINT + "/add")
    public String addProduct() {
        return ADD_PRODUCTS_PAGE_HTML;
    }

    @PostMapping(PRODUCTS_ENDPOINT + "/add")
    public String addProduct(
            Model model,
            Product product
    ) {
        Faker faker = new Faker(Locale.getDefault());
        if (product.getName().isBlank()) {
            product.setName(faker.name().firstName() + " " + faker.name().lastName());
        }
        if (product.getBrand().isBlank()) {
            product.setBrand(faker.brand().car());
        }
        if (product.getPrice().isBlank()) {
            product.setPrice(faker.commerce().price());
        }
        if (productService.addProduct(product)) {
            Logger.getLogger("Product logger").info("Element added successful");
        }
        List<Product> products = productService.getAllProducts();
        model.addAttribute(PRODUCTS_ENDPOINT, products);
        return PRODUCTS_PAGE_HTML;
    }

    @GetMapping(PRODUCTS_ENDPOINT)
    public String products(Model model) {
        if (loggedUserProcessor.isLogged()) {
            List<Product> products = productService.getAllProducts();
            model.addAttribute(PRODUCTS_ENDPOINT, products);
            return PRODUCTS_PAGE_HTML;
        }
        return "redirect:/";
    }

    @GetMapping(PRODUCTS_ENDPOINT + "/{number}")
    public String productByNumber(
            Model model,
            @PathVariable Integer number
    ) {
        List<Product> products = new ArrayList<>();
        Optional<Product> optionalProduct = Optional.ofNullable(productService.findByNumber(number));
        optionalProduct.ifPresent(products::add);
        model.addAttribute(PRODUCTS_ENDPOINT, products);
        return PRODUCTS_PAGE_HTML;
    }

}
