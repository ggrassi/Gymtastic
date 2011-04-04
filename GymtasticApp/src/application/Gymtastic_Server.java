package application;

import java.rmi.RemoteException;

import network.RMIServer;

public class Gymtastic_Server {

    /**
     * @param args
     */
    public static void main(String[] args) {
	
	GymtasticApp.emulateCup();
	views.Server.newServerFrame();
	
	

    }
    
    

}
