package network;

import domain.DeviceType;

public class ClientInformation {

	private RMIClientInterface stub;
	private String ipAddress;
	private DeviceType deviceType;

	public ClientInformation(RMIClientInterface stub, String host,
			DeviceType deviceType) {
		this.stub = stub;
		this.ipAddress = host;
		this.deviceType = deviceType;
	}

	public String getHost() {
		return ipAddress;
	}

	public RMIClientInterface getStub() {
		return stub;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

}
