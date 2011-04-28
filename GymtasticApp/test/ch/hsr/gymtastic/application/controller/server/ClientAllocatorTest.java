package ch.hsr.gymtastic.application.controller.server;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.technicalServices.network.ClientInformation;
import ch.hsr.gymtastic.technicalServices.network.RMIClientInterface;

public class ClientAllocatorTest {
	private static class TestableRMIClient implements RMIClientInterface {

		@Override
		public void uploadGymCupInfoToClient(Serializable object)
				throws RemoteException {

		}

		@Override
		public void uploadRoundInfoToClient(Serializable object)
				throws RemoteException {

		}

		@Override
		public void uploadSquadToClient(Serializable object)
				throws RemoteException {

		}

	}

	ClientAllocator clientAllocator;
	Vector<ClientInformation> allClients;
	RMIClientInterface clientPommelHorse;
	RMIClientInterface clientStillRings;
	RMIClientInterface clientFloorExcercise;
	RMIClientInterface clientVault;
	ClientInformation clientInformationPommelHorse;
	ClientInformation clientInformationStillRings;
	ClientInformation clientInformationFloorExcercise;
	ClientInformation clientInformationVault;

	@Before
	public void setUp() {
		clientAllocator = new ClientAllocator();
		clientPommelHorse = new TestableRMIClient();
		clientStillRings = new TestableRMIClient();
		clientFloorExcercise = new TestableRMIClient();
		clientVault = new TestableRMIClient();
		clientInformationPommelHorse = new ClientInformation(clientPommelHorse,
				"Pommel_Horse", DeviceType.POMMEL_HORSE);
		clientInformationStillRings = new ClientInformation(clientStillRings,
				"Still_Rings", DeviceType.STILL_RINGS);
		clientInformationFloorExcercise = new ClientInformation(
				clientFloorExcercise, "Floor_Excercise",
				DeviceType.FLOOR_EXCERCISE);
		clientInformationVault = new ClientInformation(clientVault, "Vault",
				DeviceType.VAULT);
		fillVector();
	}

	@After
	public void tearDown() {
		clientAllocator.clear();
	}

	@Test
	public void testAddAll() {
		clientAllocator.addAll(allClients);
		assertEquals(allClients.size(), clientAllocator.size());
	}

	@Test
	public void testSimpleAdd() {
		clientAllocator.addAllocation(DeviceType.VAULT, clientInformationVault);
		assertEquals(clientInformationVault.getStub(), clientAllocator
				.getClientStub(DeviceType.VAULT));
	}

	private void fillVector() {
		allClients = new Vector<ClientInformation>();
		allClients.add(clientInformationPommelHorse);
		allClients.add(clientInformationStillRings);
		allClients.add(clientInformationFloorExcercise);
		allClients.add(clientInformationVault);
	}

}
