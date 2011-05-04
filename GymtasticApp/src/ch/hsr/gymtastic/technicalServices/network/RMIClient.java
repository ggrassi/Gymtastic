package ch.hsr.gymtastic.technicalServices.network;

import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;

public class RMIClient extends Observable implements RMIClientInterface {

    private String serverIP = "localhost";
    private RMIServerInterface rmiServerInterface;

    @Override
    public void uploadSquadToClient(Serializable object) throws RemoteException {
	updateObservers(object);
    }

    public RMIClient() throws Exception {
    }

    public RMIClient(String host) {
	this.serverIP = host;
    }

    public RMIServerInterface connect(Serializable deviceType) throws RemoteException, NotBoundException,
	    AccessException, ServerNotActiveException {
	Registry registry = LocateRegistry.getRegistry(getServerIP());
	rmiServerInterface = (RMIServerInterface) registry.lookup("Gymtastic");
	RMIClientInterface stub = (RMIClientInterface) UnicastRemoteObject.exportObject(this, 0);
	rmiServerInterface.addClient(stub, deviceType);
	return rmiServerInterface;

    }

    public void disconnect() throws RemoteException {
	rmiServerInterface.removeClient(this);
    }

    public void setServerIP(String serverIP) {
	this.serverIP = serverIP;
    }

    public String getServerIP() {
	return serverIP;
    }

    public RMIServerInterface getRmiServerInterface() {
	return rmiServerInterface;
    }

    private void updateObservers(Serializable object) {
	setChanged();
	notifyObservers(object);
    }

    @Override
    public void uploadGymCupInfoToClient(Serializable object) {
	updateObservers(object);
    }

    @Override
    public void uploadRoundInfoToClient(Serializable object) {
	updateObservers(object);
    }

}
