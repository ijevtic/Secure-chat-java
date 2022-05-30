import java.io.*;
import java.net.Socket;

//prima sa socketa sa indeksom index, salje na drugi socket
public class ServerThread implements Runnable {
    private Socket[] nizSoketa;
    private int index;

    public ServerThread(Socket[] nizSoketa, int index) {
        this.nizSoketa = nizSoketa;
        this.index = index;
    }

    @Override
    public void run() {
        try {
            BufferedReader in_socket = new BufferedReader(new InputStreamReader(nizSoketa[index].getInputStream()));
            PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(nizSoketa[(index+1)%2].getOutputStream()),true);
            String publicKey = in_socket.readLine();
            System.out.println("ovo je javni kljuc: " + publicKey);
            out_socket.println(publicKey);
            while(true) {
                String poruka = in_socket.readLine();
                System.out.println("poruka " + poruka);
                out_socket.println(poruka);
                if(poruka.equalsIgnoreCase("exit")) {
                    break;
                }
            }
//            for(int i = 0; i < nizSoketa.length; i++)
//                nizSoketa[i].close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
