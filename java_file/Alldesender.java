import java.net.*;
import java.io.*;

public class Alldesender {
    public static void main(String a[]) throws Exception {
        ServerSocket SEN = new ServerSocket(10);
        Socket S = SEN.accept();
        DataInputStream in = new DataInputStream(System.in);
        DataInputStream in1 = new DataInputStream(S.getInputStream());
        String slutf[] = new String[8];
        PrintStream p;
        int sph = 0, subs = 0, nf, ack, i;
        String ch;

        do {
            p = new PrintStream(S.getOutputStream());
            System.out.print("Enter the no. of frames: ");
            nf = Integer.parseInt(in.readLine());
            p.println(nf);

            if (nf <= 8-subs) {
                System.out.println("Enter "+nf+" messages to be sent");
                for (i=1; i<= nf; i++) {
                    slutf[sph] = in.readLine();
                    p.println(slutf[sph]);
                    sph = ++sph % 8;
                }
                subs += nf;

                System.out.println("Acknowledgement received successfully");
                ack = Integer.parseInt(in1.readLine());
                System.out.println("for "+ack+" frames");
                subs -= ack;
            } else {
                System.out.println("The no. of frames exceeds window size");
                break;
            }
            System.out.print("\nDo you want to send some more frames? ");
            ch = in.readLine(); 
            p.println(ch);
        } while (ch.equals("yes"));
        S.close();
    }
}
