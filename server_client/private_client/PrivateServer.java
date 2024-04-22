import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PrivateServer {
    public static void main(String[] args) {
        int port = 8000;

        try {
            // Create a server socket bound to a private IP address and port
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            // Listen for incoming connections
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept connection from client

                // Open input and output streams for communication with the client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read data from the client and echo it back
                String inputLine;
                if ((inputLine = in.readLine()) != null) {
                    System.out.println("Received from client: " + inputLine);
                    out.println("Server received: " + inputLine); // Echo the received data back to the client
                }

                // Close streams and socket
                in.close();
                out.close();
                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
