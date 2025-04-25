import java.io.*;
import java.net.*;

public class SNU_Receiver {
    ServerSocket receiver;
    Socket connection = null;
    ObjectOutputStream out;
    ObjectInputStream in;
    String packet, ack, data = "";
    int i = 0, sequence = 0;

    public SNU_Receiver() {}

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            receiver = new ServerSocket(2004, 10);
            System.out.println("waiting for connection ...");
            connection = receiver.accept();
            sequence = 0;
            System.out.println("connection established : ");
            
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
            
            out.writeObject("connected.");
            
            do {
                try {
                    packet = (String)in.readObject();
                    if (packet.equals("end")) {
                        break;
                    }
                    
                    System.out.println("received > " + packet);
                    String receivedSeq = packet.substring(0, 1);
                    String receivedData = packet.substring(1);
                    
                    if (receivedSeq.equals(String.valueOf(sequence))) {
                        data += receivedData;
                        ack = String.valueOf(sequence);
                        sequence = (sequence == 0) ? 1 : 0;
                        System.out.println("sending ack > " + ack);
                    } else {
                        ack = String.valueOf((sequence == 0) ? 1 : 0);
                        System.out.println("sending duplicate ack > " + ack);
                    }
                    
                    out.writeObject(ack);
                    out.flush();
                } catch(Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } while (!packet.equals("end"));
            
            System.out.println("Final data received: " + data);
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                in.close();
                out.close();
                receiver.close();
            } catch(Exception e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    public static void main(String args[]) {
        SNU_Receiver r = new SNU_Receiver();
        r.run();
    }
}
