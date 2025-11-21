import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class AppMain {

    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final int DEFAULT_PORT = 5760;
    private static final int DEFAULT_TIMEOUT = 10_000;
    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();


    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        runDrone("Alpha");
        runDrone("Beta");
        runDrone("Gamma");

        EXECUTOR.shutdownNow();
    }

    private static void runDrone(String name) {
        Future<?> future = EXECUTOR.submit(new Drone(name, DEFAULT_HOST, DEFAULT_PORT));
        try {
            Thread.sleep(DEFAULT_TIMEOUT);
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted while running drone " + name + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
        future.cancel(true);
    }

}
