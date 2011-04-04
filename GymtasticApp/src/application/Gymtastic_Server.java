package application;

import java.rmi.RemoteException;

import views.Server;

import network.RMIServer;

public class Gymtastic_Server {

    /**
     * @param args
     */
    public static void main(String[] args) {
	
	Server.emulateCup();
	views.Server.newServerFrame();		

    }
    
    

}
