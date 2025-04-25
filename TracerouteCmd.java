import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TracerouteCmd {

    public static void runSystemCommand(String command) {
        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader inputStream = new BufferedReader(
                new InputStreamReader(p.getInputStream())
            );

            String s = "";
            while ((s = inputStream.readLine()) != null) {
                System.out.println(s);
            }
        } catch (Exception e) {
            // Exception handling (you can print or log the error)
        }
    }

    public static void main(String[] args) {
        String ip = "www.google.co.in";
        runSystemCommand("tracert " + ip);
        System.out.println("My ipaddress: " + ip);
    }
}
