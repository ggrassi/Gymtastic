package ch.hsr.gymtastic.server.application.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.technicalServices.network.ClientInformation;
import ch.hsr.gymtastic.technicalServices.network.RMIClientInterface;

public class ClientAllocator {

	private Map<DeviceType, ClientInformation> alloc;

	public ClientAllocator() {
		alloc = new HashMap<DeviceType, ClientInformation>();
	}

	public void addAllocation(DeviceType deviceType, ClientInformation client) {
		alloc.put(deviceType, client);
	}

	public void removeAllocation(DeviceType deviceType) {
		alloc.remove(deviceType);
	}

	public boolean isEmpty() {
		return alloc.isEmpty();
	}

	public int size() {
		return alloc.size();
	}

	public Set<Entry<DeviceType, ClientInformation>> entrySet() {
		return alloc.entrySet();
	}

	public void addAll(Vector<ClientInformation> clients) {
		for (ClientInformation c : clients) {
			System.out.println("Allocating Client: " + c.getHost() + " to "
					+ c.getDeviceType());
			alloc.put(c.getDeviceType(), c);

		}

	}

	public ClientInformation getClientInformation(DeviceType deviceType) {
		return alloc.get(deviceType);
	}

	public void clear() {
		alloc.clear();

	}

	public List<RMIClientInterface> getAllocatedClients() {
		List<RMIClientInterface> clients = new ArrayList<RMIClientInterface>();
		for (DeviceType deviceType : DeviceType.values()) {
			ClientInformation clientInformation = getClientInformation(deviceType);
			if (clientInformation != null) {
				clients.add(clientInformation.getStub());
			}
		}
		return clients;

	}

}
