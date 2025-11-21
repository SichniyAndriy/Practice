import factory.MessageSupportFactory;
import interfaces.MessageProvider;
import interfaces.MessageRender;

public class App_02_Factory {
    public static void main(String[] args) {

        MessageProvider messageProvider = MessageSupportFactory.getInstance()
                .getProvider()
                .orElseThrow( () -> new IllegalArgumentException("Service 'MessageProvide' was not found") );
        messageProvider.setMessage(args.length > 0 ? args[0] : null);

        MessageRender messageRender = MessageSupportFactory.getInstance()
                .getRender()
                .orElseThrow( () -> new IllegalArgumentException("Service 'MessageRender' was not found") );
        messageRender.setMessageProvider(messageProvider);

        messageRender.render();
    }
}
