package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundAllocation {

    private List<Map<DeviceType, Squad>> roundlist = new ArrayList<Map<DeviceType, Squad>>();
    private int counter = 0;

    public RoundAllocation() {

    }

    public Map<DeviceType, Squad> getRoundAllocation(int roundNr) {
	return roundlist.get(roundNr);
    }

    public RoundAllocation(Map<Integer, Squad> squads) {
	Map<DeviceType, Squad> map = new HashMap<DeviceType, Squad>();
	int j = 1;
	for (DeviceType device : DeviceType.values()) {
	    map.put(device, squads.get(j));
	    j++;
	}
	roundlist.add(map);
	counter++;

    }

    public Map<DeviceType, Squad> roundChange(Map<DeviceType, Squad> origin) {

	Map<DeviceType, Squad> changed = origin;
	for (DeviceType device : DeviceType.values()) {
	    int temp = device.getIndex();
	    System.out.println(device.getDevice(++temp%6));
	    
	    changed.put(device, origin.get(device.getDevice(temp % 6)));
	}

	return changed;
    }

}
