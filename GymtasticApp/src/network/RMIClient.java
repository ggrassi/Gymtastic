package network;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

import domain.DeviceType;
import domain.Squad;

public class RMIClient implements RMIClientInterface {

	
	private String serverIP = "localhost";
	private RMIServerInterface rmiServerInterface;
	private Squad squad;


	@Override
	public void uploadSquadToClient(Squad squad) throws RemoteException {

	}
	public RMIClient() throws Exception {
	}

	public RMIClient(String host) throws Exception {
		this.serverIP = host;
	}

	public void connect(DeviceType deviceType) throws RemoteException, NotBoundException,
			AccessException {
		Registry registry = LocateRegistry.getRegistry(getServerIP());
		rmiServerInterface = (RMIServerInterface) registry.lookup("Gymtastic");
		RMIClientInterface stub = (RMIClientInterface) UnicastRemoteObject
				.exportObject(this, 0);
		try {
<<<<<<< HEAD
			rmiServerInterface.addClient(stub, deviceType);
		} catch (ServerNotActiveException e) {
			e.printStackTrace();
=======
		    rmiServerInterface.addClient(stub, deviceType);
		} catch (ServerNotActiveException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
>>>>>>> 68a2cbe122ac2d8e7a0560f2db259b485fae2be6
		}
	}

	public void disconnect() throws RemoteException {
		rmiServerInterface.removeClient(this);

	}

	public void updateServer() throws RemoteException {
		rmiServerInterface.uploadSquadToServer(squad);
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public String getServerIP() {
		return serverIP;
	}

	public Squad getSquad() {
		return squad;
	}

	public RMIServerInterface getRmiServerInterface() {
		return rmiServerInterface;
	}


}
