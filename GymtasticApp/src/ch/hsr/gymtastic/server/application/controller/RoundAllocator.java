package ch.hsr.gymtastic.server.application.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Squad;

/**
 * The Class RoundAllocator holds an List which contains per round a mapping for
 * every Client to his DeviceType.
 */
public class RoundAllocator {

	private List<Map<DeviceType, Squad>> roundList = new ArrayList<Map<DeviceType, Squad>>();

	/**
	 * Instantiates a new round allocator.
	 * 
	 * @param squads
	 *            the squads
	 */
	public RoundAllocator(List<Squad> squads) {
		Map<DeviceType, Squad> firstAlloc = new HashMap<DeviceType, Squad>();
		int j = 0;
		for (DeviceType device : DeviceType.values()) {
			firstAlloc.put(device, squads.get(j));
			j++;
		}
		roundList.add(firstAlloc);
		for (int i = 1; i < 6; i++) {
			roundList.add(roundChange(getRoundAllocation(i)));
		}
	}

	/**
	 * Gets the actual round allocation, which tells us for every DeviceType his
	 * client.
	 * 
	 * @param roundNr
	 *            the round nr
	 * @return the round allocation
	 */
	private Map<DeviceType, Squad> getRoundAllocation(int roundNr) {
		return roundList.get(roundNr - 1);
	}

	/**
	 * Returns the appropriate squad for a certain round in combination with the
	 * DeviceType.
	 * 
	 * @param deviceType
	 *            the device type
	 * @param round
	 *            the round
	 * @return the squad
	 */
	public Squad getSquad(DeviceType deviceType, int round) {
		return roundList.get(round - 1).get(deviceType);
	}

	/**
	 * Returns the appropriate DeviceType for a certain squad in combination
	 * with the round.
	 * 
	 * @param squad
	 *            the squad
	 * @param round
	 *            the round
	 * @return the device type
	 */
	public DeviceType getDeviceType(Squad squad, int round) {
		DeviceType deviceType = null;
		for (Entry<DeviceType, Squad> e : roundList.get(round - 1).entrySet()) {
			if (e.getValue().getSquadId() == squad.getSquadId()) {
				deviceType = e.getKey();
				break;
			}
		}
		return deviceType;
	}

	/**
	 * Rotates in an actual round the squads to different DeviceTypes.
	 * 
	 * @param origin
	 *            the origin
	 * @return the map
	 */
	private Map<DeviceType, Squad> roundChange(Map<DeviceType, Squad> origin) {
		Map<DeviceType, Squad> changed = new HashMap<DeviceType, Squad>();
		origin.putAll(changed);
		for (DeviceType device : DeviceType.values()) {
			int temp = device.getIndex();
			changed.put(device, origin.get(device.getDevice(++temp % 6)));
		}
		return changed;
	}

}
