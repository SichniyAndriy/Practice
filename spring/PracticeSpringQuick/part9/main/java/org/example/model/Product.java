package org.example.model;

import java.util.Locale;
import lombok.Data;
import net.datafaker.Faker;

@Data
public class Product {
    private String name;
    private String brand;
    private String price;

    public Product() {
        Faker faker = new Faker(Locale.getDefault());
        this.name = faker.name().firstName() + " " + faker.name().lastName();
        this.brand = faker.brand().car();
        this.price = faker.commerce().price(10000, 50000);
    }

    public Product(String name, String brand, String price) {
        this.name = name;
        this.brand = brand;
        this.price = price;
    }
}
