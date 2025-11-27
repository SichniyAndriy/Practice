package main.java;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import net.datafaker.Faker;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker(Locale.getDefault());

        List<String> names = faker.collection(
                () -> faker.name().femaleFirstName() + " " + faker.name().lastName(),
                () -> faker.name().malefirstName() + " " + faker.name().lastName()
        ).len(10, 20).generate();

        for (var name: names) {
            System.out.println(name);
        }
        System.out.println(faker.date().past(1, TimeUnit.HOURS));
        System.out.println(faker.date().birthday(1, 99));
    }
}
