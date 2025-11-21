import interfaces.MessageProvider;
import interfaces.MessageRender;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App_02_SpringXml {
    private final static String PATH = "spring/app-context.xml";

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(PATH);

        applicationContext.getBean("provider", MessageProvider.class).setMessage("Ку kУ мій хлопчик");

        MessageRender messageRender =
                applicationContext.getBean("renderer", MessageRender.class);
        messageRender.render();
    }
}
