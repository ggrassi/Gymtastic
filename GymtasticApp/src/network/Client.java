package network;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

    /**
     * @param args
     */
    private final static int serverPort = 7777;
    private final static String serverName = "localhost";

    public static void main(String[] args) throws UnknownHostException, IOException {
	
	Socket connection = new Socket(serverName, serverPort);
	InetAddress serverAdress = connection.getInetAddress();
	System.out.println("[GymTastic] connected to - " + serverAdress.getHostAddress());
	

    }

}
