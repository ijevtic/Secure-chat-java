import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    @SuppressWarnings("resource")
    public ServerMain() throws IOException {

        int port = 2020;

        ServerSocket ss = new ServerSocket(port);
        Socket [] nizSoketa = new Socket[2];
        int br = 0;

        while (true) {
            Socket socket = ss.accept();
            nizSoketa[br++] = socket;
            if(br == 2) {
                System.out.println("uso");
                for (int i = 0; i < br; i++) {
                    PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(nizSoketa[i].getOutputStream()), true);
                    out_socket.println("ok");
                    ServerThread st = new ServerThread(nizSoketa, i);
                    Thread thread = new Thread(st);
                    thread.start();
                }
                break;
            }

        }

    }


    public static void main(String[] args) {

        try {
            new ServerMain();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
