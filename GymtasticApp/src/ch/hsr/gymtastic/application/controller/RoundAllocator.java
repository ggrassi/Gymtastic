package ch.hsr.gymtastic.application.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Squad;

public class RoundAllocator {

    private List<Map<DeviceType, Squad>> roundlist = new ArrayList<Map<DeviceType, Squad>>();

    public Map<DeviceType, Squad> getRoundAllocation(int roundNr) {
	return roundlist.get(roundNr);
    }

    public RoundAllocator(List<Squad> squads) {
	Map<DeviceType, Squad> map = new HashMap<DeviceType, Squad>();
	int j = 0;
	for (DeviceType device : DeviceType.values()) {
	    map.put(device, squads.get(j));
	    j++;
	}
	roundlist.add(map);
    }

    public Squad getSquad(DeviceType deviceType, int round) {
	return roundlist.get(round - 1).get(deviceType);
    }

    public Map<DeviceType, Squad> roundChange(Map<DeviceType, Squad> origin) {

	Map<DeviceType, Squad> changed = new HashMap<DeviceType, Squad>();
	origin.putAll(changed);

	for (DeviceType device : DeviceType.values()) {
	    int temp = device.getIndex();
	    changed.put(device, origin.get(device.getDevice(++temp % 6)));
	}
	return changed;
    }

}
