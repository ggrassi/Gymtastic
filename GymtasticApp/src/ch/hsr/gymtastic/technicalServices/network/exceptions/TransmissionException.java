package ch.hsr.gymtastic.technicalServices.network.exceptions;

/**
 * The Class TransmissionException occurs if sth goes wrong with the Connection
 * between Server and Client
 */
public class TransmissionException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Network Transmission failed!";
	}

}
