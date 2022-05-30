import java.io.PrintWriter;
import java.util.Scanner;

public class ClientWrite extends EncryptTools implements Runnable {
    private PrintWriter out_socket;
    private Scanner tastatura;
    private Client client;
    private int secretKey;

    public ClientWrite(PrintWriter out_socket, Scanner tastatura, int secretKey, Client client) {
        this.out_socket = out_socket;
        this.tastatura = tastatura;
        this.client = client;
        this.secretKey = secretKey;
    }

    @Override
    public void run() {
        try {
            int publicKey = Utils.brzoStepenovanjeMod(Utils.g, secretKey);
            out_socket.println(publicKey);
            while (true) {
                String poruka = tastatura.nextLine();
                if(poruka.equalsIgnoreCase("")) continue;
                if(poruka.equalsIgnoreCase("exit")) {
                    out_socket.println(poruka);
                    break;
                }
                int finalKey = client.getFinalKey();

                String enkriptovanaPoruka = encrypt(poruka, String.valueOf(finalKey));

                out_socket.println(enkriptovanaPoruka);
            }
            client.finish();
        }
        catch (Exception e) {

        }
    }

}
