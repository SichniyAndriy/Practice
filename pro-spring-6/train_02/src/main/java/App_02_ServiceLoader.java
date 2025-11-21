import interfaces.MessageProvider;
import interfaces.MessageRender;
import java.util.ServiceLoader;

public class App_02_ServiceLoader {
    public static void main(String[] args) {
        ServiceLoader<MessageRender> slmr = ServiceLoader.load(MessageRender.class);
        ServiceLoader<MessageProvider> slmp = ServiceLoader.load(MessageProvider.class);

        MessageRender messageRender = slmr.findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Service 'MessageRender' was not found"));
        MessageProvider messageProvider = slmp.findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Service 'MessageProvider' was not found"));

        messageRender.setMessageProvider(messageProvider);
        messageRender.render();
    }
}
