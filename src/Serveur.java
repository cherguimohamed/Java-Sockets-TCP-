import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    public static void main(String args[]) {
        ServerSocket listen = null;
        PrintStream flux = null;
        Socket service = null;
        try {
            listen = new ServerSocket(2000);
        } catch (IOException e) {
            System.err.println("Erreur socket " + e);
            System.exit(1);
        }
        Mythread mythread = new Mythread(listen, flux, service);
        mythread.run();
    }
}

