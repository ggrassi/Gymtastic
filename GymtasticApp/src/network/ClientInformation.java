package network;

public class ClientInformation {

	private RMIClientInterface stub;
	private String ipAddress;

	public ClientInformation(RMIClientInterface stub, String host) {
		this.stub = stub;
		this.ipAddress = host;
	}

	public String getHost() {
		return ipAddress;
	}

	public RMIClientInterface getStub() {
		return stub;
	}

}
