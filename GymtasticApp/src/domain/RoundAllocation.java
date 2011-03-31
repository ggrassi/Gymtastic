package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundAllocation {

    private List<Map<DeviceType, Squad>> roundlist = new ArrayList<Map<DeviceType, Squad>>();

    public Map<DeviceType, Squad> getMap(int index) {
	return roundlist.get(index);
    }

    public RoundAllocation(Map<Integer, Squad> squads) {
	Map<DeviceType, Squad> map = new HashMap<DeviceType, Squad>();
	int j = 1;
	for (DeviceType device : DeviceType.values()) {
	    map.put(device, squads.get(j));
	    j++;
	}
	roundlist.add(map);
    }

    public Map<DeviceType, Squad> roundChange(Map<DeviceType, Squad> origin) {

	Map<DeviceType, Squad> changed = new HashMap<DeviceType,Squad>();
	origin.putAll(changed);
	
	for (DeviceType device : DeviceType.values()) {
	    int temp = device.getIndex();	    
	    changed.put(device, origin.get(device.getDevice(++temp%6)));
	}
	return changed;
    }

}
