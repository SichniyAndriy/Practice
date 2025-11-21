package config;

import classes.MessageProviderImpl;
import classes.MessageRenderImpl;
import interfaces.MessageProvider;
import interfaces.MessageRender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public MessageProvider provider() {
        return new MessageProviderImpl();
    }

    @Bean
    public MessageRender renderer() {
        MessageRender messageRender = new MessageRenderImpl();
        messageRender.setMessageProvider(provider());
        return messageRender;
    }
}
