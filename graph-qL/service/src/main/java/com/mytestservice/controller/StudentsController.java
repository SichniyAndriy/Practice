package com.mytestservice.controller;

import com.mytestservice.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import net.datafaker.Faker;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class StudentsController {

    @QueryMapping
    List<Student> students() {
        List<Student> students = new ArrayList<>();
        Faker faker = new Faker(Locale.of("uk", "UA"));
        Random random = new Random();
        int len = random.nextInt(10, 21);
        for (int i = 0; i < len; i++) {
            students.add(new Student(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.address().cityName(),
                    faker.address().streetAddress() + faker.random().nextInt(500),
                    random.nextInt(17, 19)
            ));
        }
        return students;
    }
}
