import java.io.BufferedReader;

public class ClientRead extends EncryptTools implements Runnable {
    private BufferedReader in_socket;
    private Client client;
    private int secretKey;
    public ClientRead(BufferedReader in_socket, int secretKey, Client client) {
        this.in_socket = in_socket;
        this.client = client;
        this.secretKey = secretKey;
    }

    public void endThread() {
        Thread.currentThread().interrupt();
    }

    @Override
    public void run() {
        try {
            int publicKey = Integer.parseInt(in_socket.readLine());
            int finalKey = Utils.brzoStepenovanjeMod(publicKey, secretKey);
            client.setFinalKey(finalKey);


            while(true) {
                String enkriptovanaPoruka = in_socket.readLine();
                String poruka = decrypt(enkriptovanaPoruka, String.valueOf(finalKey));
                System.out.println(poruka);

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
