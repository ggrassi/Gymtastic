package ch.hsr.gymtastic.technicalServices.network.exceptions;

public class ConnectionFailedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Connection to Gymtastic-Server failed!";
	}
	

}