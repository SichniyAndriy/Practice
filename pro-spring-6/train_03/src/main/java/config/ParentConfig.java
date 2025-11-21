package config;

import model.TitleProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParentConfig {
    @Bean
    public TitleProvider parentProvider() {
        return TitleProvider.instance(null);
    }

    @Bean
    public TitleProvider childProvider() {
        return TitleProvider.instance("Child title from parent context");
    }
}
