package ch.hsr.gymtastic.server.application.controller;

import java.net.ConnectException;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import ch.hsr.gymtastic.domain.CompetitionInfo;
import ch.hsr.gymtastic.domain.GymCupClientInfo;
import ch.hsr.gymtastic.domain.RoundInfo;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.technicalServices.network.ClientInformation;
import ch.hsr.gymtastic.technicalServices.network.RMIClientInterface;
import ch.hsr.gymtastic.technicalServices.network.RMIServer;

public class NetworkServerController extends Observable implements Observer {

	private RMIServer rmiServer;
	private Squad squad;
	private ClientAllocator clientsAllocater = new ClientAllocator();

	public NetworkServerController() throws ConnectException {
		try {
			rmiServer = new RMIServer();
		} catch (RemoteException e) {
			throw new ConnectException();
		}
		rmiServer.addObserver(this);
	}

	public void sendRoundInfoToClient(RMIClientInterface client,
			RoundInfo roundInfo) throws ConnectException {
		if (client != null) {
			try {
				client.uploadSquadToClient(roundInfo);
			} catch (RemoteException e) {
				throw new ConnectException();
			}
		}
	}

	public void sendCompetitionInfoToClients(CompetitionInfo competitionInfo)
			throws ConnectException {
		try {
			for (RMIClientInterface client : clientsAllocater
					.getAllocatedClients()) {
				if (client != null) {
					client.uploadCompetitionInfoToClient(competitionInfo);
				}
			}
		} catch (RemoteException e) {
			throw new ConnectException();
		}
	}

	public Vector<ClientInformation> getClientsWaitingForAllocation() {
		return rmiServer.getClientsWaitingForAllocation();
	}

	@Override
	public void update(Observable o, Object arg) {
		this.squad = (Squad) arg;
		updateObservers();
	}

	private void updateObservers() {
		setChanged();
		notifyObservers();

	}

	public void sendGymCupInfo(RMIClientInterface client,
			GymCupClientInfo gymCupInfo) throws ConnectException {
		if (client != null) {
			try {
				client.uploadGymCupInfoToClient(gymCupInfo);

			} catch (RemoteException e) {
				throw new ConnectException();
			}
		}

	}

	public void setClientsAllocated(ClientAllocator clientsAllocated) {
		this.clientsAllocater = clientsAllocated;
	}

	public ClientAllocator getClientAllocater() {
		return clientsAllocater;
	}

}
