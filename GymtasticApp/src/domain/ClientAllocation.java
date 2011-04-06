package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;
import java.util.Set;

import network.ClientInformation;
import network.RMIClientInterface;

public class ClientAllocation {

	private Map<DeviceType, ClientInformation> alloc;

	public ClientAllocation() {
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
	
	public RMIClientInterface getClientStub( DeviceType deviceType){
	    return alloc.get(deviceType).getStub();
	}

}
