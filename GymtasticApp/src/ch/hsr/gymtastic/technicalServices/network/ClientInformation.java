package ch.hsr.gymtastic.technicalServices.network;

import java.io.Serializable;

/**
 * The Class ClientInformation holds the Information for the Client to connect
 * to the Server.
 */
public class ClientInformation {

	private RMIClientInterface stub;
	private String ipAddress;
	private Serializable deviceType;

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
			Serializable deviceType) {
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

	public void setDeviceType(Serializable deviceType) {
		this.deviceType = deviceType;
	}

	public Serializable getDeviceType() {
		return deviceType;
	}

}
