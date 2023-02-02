import java.io.*;
import java.net.*;

class TCPClient {

    public static void main(String argv[]) throws Exception {
        int num1, num2, sum;
        Socket clientSocket = new Socket("127.0.0.1", 6789);
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        boolean loop = true;

        while (loop) {
            // input number 1
            System.out.println("Enter number 1 (to end just press enter): ");
            String input1 = inFromUser.readLine();
            if (input1.isEmpty()) {
                System.out.println("Stop");
                loop = false;
            } else {
                num1 = Integer.parseInt(input1);
                outToServer.writeByte(num1);

                // input number 2
                System.out.println("Enter number 2 (to end just press enter): ");
                String input2 = inFromUser.readLine();
                if (input2.isEmpty()) {
                    System.out.println("Stop");
                    loop = false;
                } else {
                    num2 = Integer.parseInt(input2);
                    outToServer.writeByte(num2);
                    sum = inFromServer.read();
                    System.out.println("FINAL SUM: " + sum);
                }
            }
        }
        clientSocket.close();
    }
}