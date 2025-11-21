package factory;

import interfaces.MessageProvider;
import interfaces.MessageRender;
import java.util.Optional;
import java.util.Properties;

public class MessageSupportFactory {
    final private static String PATH_PROPERTIES = "/msf.properties";
    final private static MessageSupportFactory instance;
    static {
        instance = new MessageSupportFactory();
    }
    private MessageRender render;
    private MessageProvider provider;

    private MessageSupportFactory() {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(PATH_PROPERTIES));
            String renderClass = properties.getProperty("renderer.class");
            String providerClass = properties.getProperty("provider.class");
            render = (MessageRender) Class.forName(renderClass).getDeclaredConstructor().newInstance();
            provider = (MessageProvider) Class.forName(providerClass).getDeclaredConstructor().newInstance();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static MessageSupportFactory getInstance() {
        return instance;
    }

    public Optional<MessageRender> getRender() {
        return  render != null ? Optional.of(render) : Optional.empty();
    }

    public Optional<MessageProvider> getProvider() {
        return provider != null ? Optional.of(provider) : Optional.empty();
    }
}
