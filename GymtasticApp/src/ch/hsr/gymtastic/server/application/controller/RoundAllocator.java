package ch.hsr.gymtastic.server.application.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Squad;

public class RoundAllocator {

	private List<Map<DeviceType, Squad>> roundList = new ArrayList<Map<DeviceType, Squad>>();

	public RoundAllocator(List<Squad> squads) {
		Map<DeviceType, Squad> map = new HashMap<DeviceType, Squad>();
		int j = 0;
		for (DeviceType device : DeviceType.values()) {
			map.put(device, squads.get(j));
			j++;
		}
		roundList.add(map);
	}

	public Map<DeviceType, Squad> getRoundAllocation(int roundNr) {
		return roundList.get(roundNr - 1);
	}

	public Squad getSquad(DeviceType deviceType, int round) {
		return roundList.get(round - 1).get(deviceType);
	}

	public DeviceType getDeviceType(Squad squad, int round) {
		for (Entry<DeviceType, Squad> e : roundList.get(round - 1).entrySet()) {
			if (e.getValue().getSquadId() == squad.getSquadId()) {
				return e.getKey();
			}
		}
		return null;
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
