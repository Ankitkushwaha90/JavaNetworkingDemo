import java.net.*;
import java.io.*;

class DateTimeClient {
    public static void main(String args[]) {
        Socket soc;
        BufferedReader dis;
        String serverDate;
        PrintStream ps;
        
        try {
            InetAddress ia = InetAddress.getLocalHost();
            
            if (args.length == 0) {
                soc = new Socket(InetAddress.getLocalHost(), 4444);
            } else {
                soc = new Socket(InetAddress.getByName(args[0]), 4444);
            }
            
            dis = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            serverDate = dis.readLine();
            System.out.println("The date/time on server is: " + serverDate);
            
            // Send client IP to server
            ps = new PrintStream(soc.getOutputStream());
            ps.println(ia.getHostAddress());
            
            // Close resources
            ps.close();
            dis.close();
            soc.close();
        } catch(Exception e) {
            System.out.println("The exception is: " + e);
        }
    }
}
