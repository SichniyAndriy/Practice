package org.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.example.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    ProductService() {
        int len = new Random().nextInt(15, 26);
        for (int i = 0; i < len; i++) {
            products.add(new Product());
        }
    }

    public boolean addProduct(Product product) {
        return products.add(product);
    }

    public Product findByNumber(int number) {
        int index = number - 1;
        if (index < products.size() && index >= 0) {
            return products.get(index);
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return products;
    }
}
