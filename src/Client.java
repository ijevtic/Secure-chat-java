import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private int finalKey;
    private Thread tRead;
    private Thread tWrite;
    private Socket socket;
    private ClientRead clientRead;

    @SuppressWarnings("resource")
    public Client () throws Exception {

        System.out.println("Unesite tajni kljuc:");
        Scanner tastatura = new Scanner(System.in);

        int secretKey = tastatura.nextInt();

        socket = new Socket("localhost",2020);
        System.out.println("Uspesno povezivanje na port " + socket.getPort());

        BufferedReader in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out_socket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);

        String start = in_socket.readLine();
        if(start.equalsIgnoreCase("ok")) {
            ClientRead clientRead = new ClientRead(in_socket, secretKey, this);
            tRead = new Thread(clientRead);
            tRead.start();

            ClientWrite clientWrite = new ClientWrite(out_socket, tastatura, secretKey, this);
            tWrite = new Thread(clientWrite);
            tWrite.start();
        }
    }

    public void finish() throws IOException {
        clientRead.endThread();
        System.out.println("kraj threada");
        tWrite.interrupt();
        socket.close();
    }

    public void setFinalKey(int key) {
        this.finalKey = key;
    }

    public int getFinalKey() {
        return finalKey;
    }



    public static void main(String[] args) {

        try {
            new Client();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }}

