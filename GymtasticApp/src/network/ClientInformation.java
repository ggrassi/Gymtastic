package network;

import network.prototype.RMIClientInterfacePT;

public class ClientInformation {

	private RMIClientInterfacePT stub;
	private String ipAddress;

	public ClientInformation(RMIClientInterfacePT stub, String host) {
		this.stub = stub;
		this.ipAddress = host;
	}

	public String getHost() {
		return ipAddress;
	}

	public RMIClientInterfacePT getStub() {
		return stub;
	}

}
