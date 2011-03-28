package network;

import java.rmi.RemoteException;
import java.util.Vector;

public class RMIServer implements RMIServerInterface {

    /**
     * @param args
     */
    Vector<Object> clients = new Vector<Object>();
    
    
    
    public RMIServer() {
	super();
    }

    @Override
    public void addClient(RMIClientInterface client) throws RemoteException {
	
    }

    @Override
    public void removeClient() throws RemoteException {
	
    }
    
    
    public static void main(String[] args) {

    }

}
