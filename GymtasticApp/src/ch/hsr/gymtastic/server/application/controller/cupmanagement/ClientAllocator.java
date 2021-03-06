package ch.hsr.gymtastic.server.application.controller.cupmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.technicalServices.network.ClientInformation;
import ch.hsr.gymtastic.technicalServices.network.RMIClientInterface;

/**
 * The Class ClientAllocator maps the DeviceType with the appropriate
 * ClientInformations
 */
public class ClientAllocator {

	private Map<DeviceType, ClientInformation> alloc;

	/**
	 * Instantiates a new client allocator.
	 */
	public ClientAllocator() {
		alloc = new HashMap<DeviceType, ClientInformation>();
	}

	/**
	 * Adds the client in combination with the DeviceType into the allocation map
	 * 
	 * @param deviceType
	 *            the device type
	 * @param client
	 *            the client
	 */
	public void addAllocation(DeviceType deviceType, ClientInformation client) {
		alloc.put(deviceType, client);
	}

	/**
	 * Removes the allocation.
	 * 
	 * @param deviceType
	 *            the device type
	 */
	public void removeAllocation(DeviceType deviceType) {
		alloc.remove(deviceType);
	}

	/**
	 * Checks if the allocation map is empty.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return alloc.isEmpty();
	}

	/**
	 * returns the size of the allocation map
	 * 
	 * @return the int
	 */
	public int size() {
		return alloc.size();
	}

	/**
	 * Adds a vektor which contains Clientinformations to the allocation map.
	 * 
	 * @param clients
	 *            the clients
	 */
	public void addAll(Vector<ClientInformation> clients) {
		for (ClientInformation c : clients) {
			alloc.put((DeviceType) c.getDeviceType(), c);

		}

	}

	public ClientInformation getClientInformation(DeviceType deviceType) {
		return alloc.get(deviceType);
	}

	/**
	 * Clears the allocation map
	 */
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
