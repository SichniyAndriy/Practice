import io.dronefleet.mavlink.MavlinkConnection;
import io.dronefleet.mavlink.ardupilotmega.ArdupilotmegaDialect;
import io.dronefleet.mavlink.common.CommonDialect;
import io.dronefleet.mavlink.minimal.Heartbeat;
import io.dronefleet.mavlink.minimal.MavAutopilot;
import io.dronefleet.mavlink.minimal.MavType;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.TimeZone;


public class AppMain {

    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final int DEFAULT_PORT = 5760;
    private static final int DEFAULT_TIMEOUT = 10_000;


    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        try (Socket socket = new Socket(DEFAULT_HOST, DEFAULT_PORT)) {
            socket.setSoTimeout(DEFAULT_TIMEOUT);
            InputStream inStream = socket.getInputStream();
            OutputStream outStream = socket.getOutputStream();

            MavlinkConnection connection = MavlinkConnection.builder(inStream, outStream)
                    .defaultDialect(new CommonDialect())
                    .dialect(MavAutopilot.MAV_AUTOPILOT_AUTOQUAD, new ArdupilotmegaDialect())
                    .build();

            Heartbeat heartbeat = Heartbeat.builder()
                    .mavlinkVersion(3)
                    .type(MavType.MAV_TYPE_GCS)
                    .autopilot(MavAutopilot.MAV_AUTOPILOT_ARDUPILOTMEGA)
                    .customMode(0)
                    .build();

            connection.send2(255, 0, heartbeat);
            System.out.println("Heartbeat sent to " + DEFAULT_HOST + ':' + DEFAULT_PORT);
        } catch (EOFException e) {
            System.err.println("Connection closed by remote host: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

}
