import io.dronefleet.mavlink.MavlinkConnection;
import io.dronefleet.mavlink.MavlinkMessage;
import io.dronefleet.mavlink.minimal.Heartbeat;
import io.dronefleet.mavlink.minimal.MavAutopilot;
import io.dronefleet.mavlink.minimal.MavState;
import io.dronefleet.mavlink.minimal.MavType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class Drone implements Runnable {

    private final String NAME;
    private final String HOST;
    private final int PORT;
    private final Socket socket;


    public Drone(String name, String host, int port) {
        this.NAME = name;
        this.HOST = host;
        this.PORT = port;
        try {
            this.socket = new Socket(host, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            int SOCKET_TIMEOUT_MS = 2500;
            socket.setSoTimeout(SOCKET_TIMEOUT_MS);
            MavlinkConnection drone = MavlinkConnection.create(inputStream, outputStream);
            sendHeartbeat(drone);

            while (!Thread.currentThread().isInterrupted()) {
                MavlinkMessage<?> message = drone.next();
                if (message != null) {
                    String sb =
                            "Дрон " + NAME +
                            " отримав повідомлення від " + message.getPayload().getClass().getSimpleName() +
                            " : " + message;
                    System.out.println(sb);
                }
            }
            socket.close();
        } catch (IOException e) {
            System.err.println(
                    "Помилка мережі або I/O під час з’єднання з " + HOST + ':' + PORT + " -> " + e.getMessage()
            );
        }

    }

    private void sendHeartbeat(MavlinkConnection drone) throws IOException {
        Heartbeat heartbeat = Heartbeat.builder()
                .type(MavType.MAV_TYPE_QUADROTOR)
                .autopilot(MavAutopilot.MAV_AUTOPILOT_ARDUPILOTMEGA)
                .baseMode()
                .customMode(0)
                .systemStatus(MavState.MAV_STATE_ACTIVE)
                .build();

        int HEARTBEAT_COMPONENT_ID = 0;
        int HEARTBEAT_SENDER_ID = 255;
        drone.send2(HEARTBEAT_SENDER_ID, HEARTBEAT_COMPONENT_ID, heartbeat);
    }
}
