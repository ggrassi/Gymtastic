package ch.hsr.gymtastic.network;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

import org.junit.Test;

import network.RMIClient;
import network.RMIServerInterface;
import domain.DeviceType;
import domain.Squad;

public class RMIClientTest {

	private class TestableRMIClient extends RMIClient {

		public TestableRMIClient() {
			super();
		}

		@Override
		public void connect(DeviceType deviceType) throws RemoteException,
				NotBoundException, AccessException, ServerNotActiveException {
		}

		@Override
		public void disconnect() throws RemoteException {
			super.disconnect();
		}

		@Override
		public RMIServerInterface getRmiServerInterface() {
			return super.getRmiServerInterface();
		}

		@Override
		public String getServerIP() {
			return super.getServerIP();
		}

		@Override
		public Squad getSquad() {
			return super.getSquad();
		}

		@Override
		public int hashCode() {
			return super.hashCode();
		}

		@Override
		public void updateServer() throws RemoteException {
			super.updateServer();
		}

		@Override
		public void uploadSquadToClient(Squad squad) throws RemoteException {
			super.uploadSquadToClient(squad);
		}

	}

	@Test(expected = Exception.class)
	public void testClient() {
		TestableRMIClient rmiClient = new TestableRMIClient();

	}

}
