package main.java.org.example.configuration;

import java.util.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Logger paymentLogger() {
        return Logger.getLogger("PaymentLogger");
    }
}
