package network;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.Vector;

import domain.ClientAllocation;
import domain.DeviceType;
import domain.Squad;

public class RMIServer implements RMIServerInterface {

    Vector<ClientInformation> clientsWaitingForAllocation = new Vector<ClientInformation>();
    ClientAllocation clientsAllocated = new ClientAllocation();
    
    @Override
    public void addClient(RMIClientInterface client, DeviceType deviceType) throws RemoteException, ServerNotActiveException {
	clientsWaitingForAllocation.add(new ClientInformation(client, RemoteServer.getClientHost()));

    }

    @Override
    public void removeClient(RMIClientInterface client) throws RemoteException {

    }

    @Override
    public void uploadSquadToServer(Squad squad) throws RemoteException {

    }

    public Vector<ClientInformation> getClientsWaitingForAllocation() {
	return clientsWaitingForAllocation;
    }
    
    

   

  

}
