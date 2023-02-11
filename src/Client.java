import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    public static void main (String args[]) {
        Socket s = null;
        PrintStream flux = null;
        try {
            s = new Socket("127.0.0.1", 2000);
            flux = new PrintStream(s.getOutputStream(), true);
            flux.println("/home/chergui/Documents/2A/java2A/tp1_socket");
            BufferedReader response = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String res = "";
            String line = null;
            while((line = response.readLine()) != null) {
                res += line;
                res += " ";
            }
            System.out.println(res);
        } catch (IOException e) {
            System.out.println("Erreur :" + e.getMessage());
            System.exit(1);
        }
    }
}
