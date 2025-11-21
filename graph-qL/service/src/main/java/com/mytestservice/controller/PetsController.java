package com.mytestservice.controller;

import com.mytestservice.model.Person;
import com.mytestservice.model.Pet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.datafaker.Faker;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PetsController {
    private final static String LANGUAGE = "uk";

    @QueryMapping
    public List<Pet> pets() {
        Faker faker = new Faker(Locale.of(LANGUAGE));
        List<Pet> pets = new ArrayList<>();
        int len = faker.random().nextInt(30, 51);
        for (int i = 0; i < len; ++i) {
            pets.add(new Pet(
                    faker.random().nextBoolean() ? faker.dog().name() : faker.cat().name(),
                    faker.color().name(),
                    new Person(faker.name().fullName(), faker.random().nextInt(18, 61)),
                    faker.random().nextBoolean() ? new Person(faker.name().fullName(), faker.random().nextInt(18, 61)) : null,
                    faker.text().text(50, 100, true)
            ));
        }
        return pets;
    }

    @QueryMapping
    public Person owner(Pet pet) {
        return pet.owner();
    }

    @QueryMapping
    public Person previousOwner(Pet pet) {
        return pet.previousOwner();
    }
}
