package ch.hsr.gymtastic.technicalServices.network;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.technicalServices.network.exceptions.TransmissionException;

public class RMITest {
    private static class TestableRMIServer extends RMIServer {

	private List<RMIClientInterface> clients;
	private Object received;

	public TestableRMIServer() throws RemoteException {
	    clients = new ArrayList<RMIClientInterface>();
	}

	@Override
	public void addClient(RMIClientInterface client, Serializable deviceType) throws RemoteException,
		ServerNotActiveException {
	    clients.add(client);

	}

	@Override
	public void removeClient(RMIClientInterface client) throws RemoteException {
	    clients.remove(client);

	}

	@Override
	public void uploadObjectToServer(Serializable object) throws RemoteException {
	    received = object;
	}

    }

    private static class TestableRMIClient extends RMIClient {

	private Object received;

	public TestableRMIClient(RMIServerInterface rmiServer) throws Exception {
	    setRMIServerInterface(rmiServer);
	}

	@Override
	public void uploadObjectToClient(Serializable object) throws RemoteException {
	    received = object;

	}

	public void connect() throws RemoteException, ServerNotActiveException {
	    rmiServer.addClient(this, DeviceType.FLOOR_EXCERCISE);
	}

    }

    private static TestableRMIServer rmiServer;
    private static TestableRMIClient rmiClient;
    private DeviceType deviceType;
    private Athlete athlete;

    @Before
    public void setUp() throws Exception {
	athlete = new Athlete("Jules", "Weder", "Irgendwo");
	rmiServer = new TestableRMIServer();
	rmiClient = new TestableRMIClient(rmiServer);
	deviceType = DeviceType.FLOOR_EXCERCISE;
    }

    @Test
    public void testAddClient() throws Exception {
	rmiClient.connect();
	assertTrue(rmiServer.clients.contains(rmiClient));
    }

    @Test
    public void testSendObjectToServer() throws TransmissionException {
	rmiClient.sendObjectToServer(athlete);
	assertEquals(athlete, (Athlete) rmiServer.received);

    }

    @Test
    public void testSendObjectToClient() throws RemoteException {
	rmiClient.uploadObjectToClient(athlete);
	assertEquals(athlete, (Athlete) rmiClient.received);
    }
}
