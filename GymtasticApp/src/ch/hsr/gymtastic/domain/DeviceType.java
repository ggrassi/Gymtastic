package ch.hsr.gymtastic.domain;

import java.io.Serializable;

public enum DeviceType implements Serializable {

	FLOOR_EXCERCISE(0), POMMEL_HORSE(1), STILL_RINGS(2), VAULT(3), PARALLEL_BARS(
			4), HIGH_BAR(5);

	public int index;

	DeviceType(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public DeviceType getDevice(int index) {
		switch (index) {
		case 0:
			return FLOOR_EXCERCISE;
		case 1:
			return POMMEL_HORSE;
		case 2:
			return STILL_RINGS;
		case 3:
			return VAULT;
		case 4:
			return PARALLEL_BARS;
		case 5:
			return HIGH_BAR;
		default:
			return null;
		}
	}

	public String toString() {
		switch (index) {
		case 0:
			return "Boden";
		case 1:
			return "Pferd";
		case 2:
			return "Ringe";
		case 3:
			return "Sprung";
		case 4:
			return "Barren";
		case 5:
			return "Reck";
		default:
			return "";

		}

	}
}