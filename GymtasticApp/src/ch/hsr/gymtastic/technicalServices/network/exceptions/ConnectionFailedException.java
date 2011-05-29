package ch.hsr.gymtastic.technicalServices.network.exceptions;

/**
 * The Class ConnectionFailedException occurs if 
 * sth goes wrong with the Connection between Server and Client
 */
public class ConnectionFailedException extends Exception {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Connection to Gymtastic-Server failed!";
	}
	

}
