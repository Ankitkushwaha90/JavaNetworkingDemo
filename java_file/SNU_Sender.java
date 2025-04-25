import java.io.*;
import java.net.*;

public class SNU_Sender {
    Socket sender;
    ObjectOutputStream out;
    ObjectInputStream in;
    String packet, ack, str, msg;
    int n, i = 0, sequence = 0;

    public SNU_Sender() {}

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Waiting for Connection...");
            sender = new Socket("localhost", 2004);
            sequence = 0;
            out = new ObjectOutputStream(sender.getOutputStream());
            out.flush();
            in = new ObjectInputStream(sender.getInputStream());
            
            // Connection establishment
            str = (String)in.readObject();
            System.out.println("receiver > " + str);
            System.out.println("Enter the data to send: ");
            packet = br.readLine();
            n = packet.length();
            
            do {
                try {
                    // Prepare message with sequence number
                    msg = String.valueOf(sequence);
                    if (i < n) {
                        msg = msg.concat(packet.substring(i, i+1));
                    } else {
                        msg = "end";
                        out.writeObject(msg);
                        break;
                    }

                    out.writeObject(msg);
                    sequence = (sequence == 0) ? 1 : 0;
                    out.flush();
                    System.out.println("data sent > " + msg);
                    
                    System.out.println("waiting for ack ...\n");
                    ack = (String)in.readObject();

                    if (ack.equals(String.valueOf(sequence))) {
                        i++;
                        System.out.println("receiver > packet received\n");
                    } else {
                        System.out.println("time out resending data ...\n");
                        sequence = (sequence == 0) ? 1 : 0;
                    }
                } catch(Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } while (i < n + 1);

            System.out.println("All data sent successfully.");
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                in.close();
                out.close();
                sender.close();
            } catch(Exception e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    public static void main(String args[]) {
        SNU_Sender s = new SNU_Sender();
        s.run();
    }
}
