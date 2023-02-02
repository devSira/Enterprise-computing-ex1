import java.net.*;

class TCPServer {
    public static void main(String argv[]) throws Exception {
        //Create welcoming socket at port 6789
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while (true) {
            //Wait, on welcoming socket for contact by client
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("New client connected "+ connectionSocket.getInetAddress().getHostAddress() + " " + connectionSocket.getPort());
           
            // create a new thread object
            ClientHandler clientSocket = new ClientHandler(connectionSocket);
            new Thread(clientSocket).start();
        }
    }
}