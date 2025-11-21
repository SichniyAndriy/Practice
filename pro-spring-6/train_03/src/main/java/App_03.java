import config.ChildConfig;
import config.ParentConfig;
import model.Song;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App_03 {

    public static void main(String[] args) {
        var parentContext = new AnnotationConfigApplicationContext();
        parentContext.register(ParentConfig.class);
        parentContext.setDisplayName("ParentConfig class");
        parentContext.refresh();

        var childContext = new AnnotationConfigApplicationContext();
        childContext.register(ChildConfig.class);
        childContext.setDisplayName("ChildConfig class");
        childContext.setParent(parentContext);
        childContext.refresh();

        Song song1 = childContext.getBean("song1", Song.class);
        Song song2 = childContext.getBean("song2", Song.class);
        Song song3 = childContext.getBean("song3", Song.class);

        System.out.println(song1);
        System.out.println(song2);
        System.out.println(song3);
    }
}
