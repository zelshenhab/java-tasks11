import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServer {
    private String host;
    private int port;

    public TimeServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) {
        TimeServer timeServer = new TimeServer("0.0.0.0", 1303);
        timeServer.startServer();
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port + "...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                ) {
                    handleClient(out);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleClient(PrintWriter out) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String currentDateTime = dateFormat.format(new Date());
        out.println(currentDateTime);
    }
}
