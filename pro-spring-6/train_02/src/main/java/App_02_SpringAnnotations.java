import config.AppConfiguration;
import interfaces.MessageProvider;
import interfaces.MessageRender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App_02_SpringAnnotations {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfiguration.class);
        applicationContext.getBean(MessageProvider.class).setMessage("Аляулю");
        applicationContext.getBean("renderer", MessageRender.class).render();
    }
}
