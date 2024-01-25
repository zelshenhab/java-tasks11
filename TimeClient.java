import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {
    private static final int SERVER_PORT = 1303;

    public static void main(String[] args) {
        TimeClient timeClient = new TimeClient();
        timeClient.startClient();
    }

    public void startClient() {
        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the server's IP address (Ex: 127.0.0.1): ");
            String serverIP = userInput.readLine();

            try (Socket clientSocket = new Socket(serverIP, SERVER_PORT);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {
                String data = in.readLine();
                System.out.println("Received from server: " + data);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
