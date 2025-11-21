import config.AppConfiguration;
import model.AbstractLockOpener;
import model.KeyHelper;
import model.LockOpener;
import model.StandardLockOpener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StopWatch;

public class App_03_Lmi {
    public static void main(String[] args) {
        var appContext = new AnnotationConfigApplicationContext(AppConfiguration.class);

        var standardLockOpener = appContext.getBean("standardLockOpener", StandardLockOpener.class);
        var abstractLockOpener = appContext.getBean("abstractLockOpener", AbstractLockOpener.class);

        displayWork(standardLockOpener);
        displayWork(abstractLockOpener);
    }

    public static void displayWork(LockOpener lockOpener) {
        KeyHelper key1 = lockOpener.getKey();
        KeyHelper key2 = lockOpener.getKey();
        System.out.println("Opener type is " + lockOpener.getClass());
        System.out.println("Two keys is equal: " + (key1 == key2));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start(lockOpener.getClass().getName());
        int n = 1_000_000;
        for (int i = 0; i < n; ++i) {
            KeyHelper key = lockOpener.getKey();
            key.open();
        }
        stopWatch.stop();
        System.out.println("Time " + n + " takes " + stopWatch.getLastTaskTimeNanos() / 1_000_000. + " ms");
        System.out.println(stopWatch.prettyPrint());
    }
}
