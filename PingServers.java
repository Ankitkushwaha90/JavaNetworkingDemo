import java.io.*;
import java.net.*;

public class PingServers {

    public static void main(String[] args) {
        try {
            String line;
            System.out.print("Enter the IP Address to be pinged: ");
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String ip = reader.readLine();

            // Execute ping command
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("ping " + ip);

            // Read the output of the command
            InputStream inputStream = process.getInputStream();
            BufferedReader outputReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = outputReader.readLine()) != null) {
                System.out.println(" " + line);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
