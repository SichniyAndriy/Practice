package org.example.controller;

import java.util.List;
import java.util.Locale;
import net.datafaker.Faker;
import org.example.model.Country;
import org.example.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> countries() {
        Faker faker = new Faker(Locale.getDefault());
        if (faker.random().nextInt(1, 5) % 4 == 0) {
            throw new RuntimeException("Server error");
        }
        List<Country> countries = countryService.getCountries();
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Request","Countries")
                .header("Status", Series.SUCCESSFUL.name())
                .body(countries);
    }
}
