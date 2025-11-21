package org.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import lombok.Getter;
import net.datafaker.Faker;
import org.example.model.Country;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Getter
@Service
public class CountryService {
    private final List<Country> countries = new ArrayList<>();

    {
        Faker faker = new Faker(Locale.getDefault());
        int len = 100;//faker.random().nextInt(100, 151);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("Create 100 countries");
        for (int i = 0; i < len; i++) {
            net.datafaker.providers.base.Country countryFaker = faker.country();
            Country country = getCountryClass();
            country.setName(countryFaker.name());
            country.setCapital(countryFaker.capital());
            country.setCode(countryFaker.countryCode2());
            country.setCode2(countryFaker.countryCode3());
            country.setFlag(countryFaker.flag());
            countries.add(country);
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    @Lookup("country")
    private Country getCountryClass() {
        return new Country();
    }
}
