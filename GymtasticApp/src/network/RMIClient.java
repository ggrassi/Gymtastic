package network;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {

	private static final String HOST="localhost";
	
public RMIClient() throws Exception{
		
		Registry registry = LocateRegistry.getRegistry(HOST);
		
		
		
		
		
		
//		trader = (Trader) registry.lookup("trader");
//		
//		// read stock
//		aktien = trader.getAktien();
//		
//		// generate proxy
//		AktienUpdater stub = (AktienUpdater ) UnicastRemoteObject.exportObject(this, 0);
//		
//		// register client at server
//		trader.addClient(stub);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
