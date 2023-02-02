import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        int num1, num2, sum;
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
            while (true) {
                num1 = inFromClient.read();
                if(num1 == -1) {
                    System.out.printf("Connection close at port %d\n", clientSocket.getPort());
                    return;
                }
                System.out.printf(" Sent from the client1[%d]: %d\n", clientSocket.getPort(), num1);

                num2 = inFromClient.read();
                if(num2 == -1) {
                    System.out.printf("Connection close at port %d\n", clientSocket.getPort());
                    return;
                }
                System.out.printf(" Sent from the client2[%d]: %d\n", clientSocket.getPort(), num2);
                
                sum = num1 + num2;
                outToClient.writeByte(sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
