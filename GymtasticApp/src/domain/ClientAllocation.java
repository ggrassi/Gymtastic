package domain;

import java.util.HashMap;
import java.util.Map;

import network.ClientInformation;

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

}
