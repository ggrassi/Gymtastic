package network;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.Vector;

import network.prototype.RMIClientInterfacePT;

import domain.ClientAllocation;
import domain.DeviceType;
import domain.Dummy;
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
	// TODO Auto-generated method stub

    }

    @Override
    public void uploadSquadToServer(Squad squad) throws RemoteException {
	// TODO Auto-generated method stub

    }

    public Vector<ClientInformation> getClientsWaitingForAllocation() {
	return clientsWaitingForAllocation;
    }
    
    

   

  

}
