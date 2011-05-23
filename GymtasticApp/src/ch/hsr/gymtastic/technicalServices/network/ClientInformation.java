package ch.hsr.gymtastic.technicalServices.network;

import ch.hsr.gymtastic.domain.DeviceType;

/**
 * The Class ClientInformation holds the Information for the Client to connect to the Server.
 */
public class ClientInformation {

	private RMIClientInterface stub;
	private String ipAddress;
	private DeviceType deviceType;

	/**
	 * Instantiates a new client information.
	 * 
	 * @param stub
	 *            the stub
	 * @param host
	 *            the host
	 * @param deviceType
	 *            the device type
	 */
	public ClientInformation(RMIClientInterface stub, String host,
			DeviceType deviceType) {
		this.stub = stub;
		this.ipAddress = host;
		this.setDeviceType(deviceType);
	}

	/**
	 * Gets the host.
	 * 
	 * @return the host
	 */

	public String getHost() {
		return ipAddress;
	}

	/**
	 * Gets the stub.
	 * 
	 * @return the stub
	 */
	public RMIClientInterface getStub() {
		return stub;
	}

	/**
	 * Sets the device type.
	 * 
	 * @param deviceType
	 *            the new device type
	 */
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * Gets the device type.
	 * 
	 * @return the device type
	 */
	public DeviceType getDeviceType() {
		return deviceType;
	}

}
