package config;

import model.Song;
import model.TitleProvider;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChildConfig implements ApplicationContextAware {
    public ApplicationContext applicationContext;

    @Bean
    public TitleProvider childProvider() {
        return TitleProvider.instance("Child title from child config");
    }
    @Bean
    public Song song1(
            @Value("#{ parentProvider.title }")
            String title) {
        return new Song(title);
    }

    @Bean
    public Song song2(
            @Value("#{ childConfig.applicationContext.parent.getBean(\"childProvider\").title }")
            String title) {
        return new Song(title);
    }

    @Bean
    public Song song3(
            @Value("#{ childProvider.title }")
            String title) {
        return new Song(title);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println(this.applicationContext.getDisplayName());
    }
}
