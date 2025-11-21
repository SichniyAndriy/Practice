package classes;

import interfaces.MessageProvider;

public class MessageProviderImpl implements MessageProvider {
    private String message;

    @Override
    public String getMessage() {
        return message == null || message.isBlank() ? "Hello world" : message;
    }

    @Override
    public void setMessage(String line) {
        message = line;
    }
}
