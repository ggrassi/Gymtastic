package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

<<<<<<< HEAD
public class ClientAllocation {
    
    private Map<DeviceType, RMIClientInterface> alloc;
=======
import network.ClientInformation;

public class ClientAllocation{

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

	

	
	
	

	

>>>>>>> a91882a3d347308dc17a165cb91e38ca5844371e
}
