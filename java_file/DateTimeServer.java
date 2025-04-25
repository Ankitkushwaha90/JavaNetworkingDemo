import java.net.*;
import java.io.*;
import java.util.*;

class DateTimeServer {
    public static void main(String args[]) {
        ServerSocket ss = null;
        Socket cs;
        PrintStream ps;
        BufferedReader dis;
        String clientIP;
        
        try {
            ss = new ServerSocket(4444);
            System.out.println("Server started. Press Ctrl+C to quit");
            
            while(true) {
                cs = ss.accept();
                ps = new PrintStream(cs.getOutputStream());
                dis = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                
                // Get and send current date/time
                Date d = new Date();
                ps.println(d);
                
                // Get client IP address
                clientIP = dis.readLine();
                System.out.println("Client System's IP address is: " + clientIP);
                
                // Close streams and socket
                ps.close();
                dis.close();
                cs.close();
            }
        } catch (Exception e) {
            System.out.println("The exception is: " + e);
        } finally {
            try {
                if (ss != null) ss.close();
            } catch (IOException e) {
                System.out.println("Error closing server: " + e);
            }
        }
    }
}
