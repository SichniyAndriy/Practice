import io.dronefleet.mavlink.MavlinkConnection;

public class Drone implements Runnable {

    private final MavlinkConnection connection;

    public Drone(MavlinkConnection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {

    }
}
