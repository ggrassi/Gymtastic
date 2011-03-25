package network;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    /**
     * @param args
     */
    private final static int serverPort = 7777;

    public static void main(String[] args) throws IOException {
	ServerSocket listenSocket = new ServerSocket(serverPort);
	System.out.println("[GymTastic-Server started]");
	// Acception Client Socket
	Socket clientSocket = listenSocket.accept();
	InetAddress clientAddress = clientSocket.getInetAddress();
	System.out.println("[GymTastic] Client connected - " + clientAddress.getHostAddress());
	try {
	    Thread.currentThread();
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	clientSocket.close();
	System.out.println("[GymTastic] Client " + clientAddress.getHostAddress() +" disconnected.");
	System.out.println("[GymTastic-Server stopped]");
	
    }

}
