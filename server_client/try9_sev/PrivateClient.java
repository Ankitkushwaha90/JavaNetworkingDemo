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

            // Create a thread to read messages from the server
            Thread serverThread = new Thread(() -> {
                try {
                    // Read messages from the server indefinitely
                    while (true) {
                        String serverResponse = in.readLine();
                        if (serverResponse != null) {
                            System.out.println("Server response: " + serverResponse);
                        } else {
                            break; // Exit the loop if the server closes the connection
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            serverThread.start(); // Start the server thread

            // Read messages from the console and send them to the server
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            while ((userInput = consoleReader.readLine()) != null) {
                out.println(userInput); // Send the user input to the server
            }

            // Close streams and socket
            consoleReader.close();
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
