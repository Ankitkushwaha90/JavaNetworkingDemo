import java.net.*;
import java.io.*;

class Alekseechen {
    public static void main(String a[]) throws Exception {
        Socket s = new Socket(InetAddress.getLocalHost(), 10);
        DataInputStream in = new DataInputStream(s.getInputStream());
        PrintStream p = new PrintStream(s.getOutputStream());
        int i = 0, rptr = -1, nf, nrf = 0;
        String row[] = new String[8];
        String ck;

        System.out.println();
        do {
            nf = Integer.parseInt(in.readLine());
            if (nf <= 8-nrf) {
                for (i=1; i<=nf; i++) {
                    rptr = ++rptr % 8;
                    row[rptr] = in.readLine();
                    System.out.println("The received frame "+rptr+" is: "+row[rptr]);
                }
                nrf += nf;

                System.out.println("In Acknowledgement next in");
                p.println(nrf); 
                nrf = 0;
            } else {
                break;
            }
            ck = in.readLine();
        } while (ck.equals("yes"));
        s.close();
    }
}
