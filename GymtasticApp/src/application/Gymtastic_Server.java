package application;

import java.rmi.RemoteException;

import network.RMIServer;

public class Gymtastic_Server {

    /**
     * @param args
     */
    public static void main(String[] args) {
	
	views.Server.newServerFrame();
	try {
	    RMIServer rmiServer = new RMIServer();
	} catch (RemoteException e) {
	    e.printStackTrace();
	}
	

    }

}
