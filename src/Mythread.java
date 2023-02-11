import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Mythread extends Thread {

    private ServerSocket _listen;
    private PrintStream _flux;
    Socket _service;

    public Mythread(ServerSocket listen, PrintStream flux, Socket service) {
        _listen = listen;
        _flux = flux;
        _service = service;
    }


    public void run() {
        while (true) {
            try {
                _service = _listen.accept();
                BufferedReader requete = new BufferedReader(new InputStreamReader(_service.getInputStream()));
                File directorie = new File(requete.readLine());
                _flux = new PrintStream(_service.getOutputStream(), true);
                int  number_of_files = 0;
                File[] doc = directorie.listFiles();
                List<String> files = new ArrayList<String>();
                for (int i = 0; i < doc.length; i++) {
                    File item = doc[i];
                    if (item.isFile()) {
                        number_of_files++;
                        files.add(item.getName());
                    }
                }
                _flux.println("The number of files is : "+number_of_files);
                _flux.println("\n");
                _flux.println("the liste of files : "+files);
                _service.close();

            } catch (IOException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }
    }
}
