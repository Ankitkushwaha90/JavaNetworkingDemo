import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PrivateClient {
    public static void main(String[] args) {
        String serverAddress = "192.168.77.28"; // Private IP address of the server
        int serverPort = 8000;

        try {
            // Connect to the server
            Socket socket = new Socket(serverAddress, serverPort);

            // Open input and output streams for communication with the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send data to the server
            out.println("Hello from the client!");

            // Read the server's response
            String response = in.readLine();
            System.out.println("Server response: " + response);

            // Close streams and socket
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
