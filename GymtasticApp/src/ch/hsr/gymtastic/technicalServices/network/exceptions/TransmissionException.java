package ch.hsr.gymtastic.technicalServices.network.exceptions;

public class TransmissionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Network Transmission failed!";
	}
	

}
