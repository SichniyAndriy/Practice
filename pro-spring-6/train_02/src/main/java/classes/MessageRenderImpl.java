package classes;

import interfaces.MessageProvider;
import interfaces.MessageRender;

public class MessageRenderImpl implements MessageRender {
    private MessageProvider messageProvider;

    @Override
    public void render() {
        System.out.println(messageProvider.getMessage());
    }

    @Override
    public void setMessageProvider(MessageProvider messageProvider) {
        if (this.messageProvider == null) {
            this.messageProvider = messageProvider;
        } else {
        }
    }

    @Override
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }
}
