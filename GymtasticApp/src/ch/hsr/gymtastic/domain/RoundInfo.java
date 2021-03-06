package ch.hsr.gymtastic.domain;

import java.io.Serializable;

/**
 * The Class RoundInfo  holds the squad and the actual roundNr for the client
 */
public class RoundInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Squad squad;
	private int roundNr;
	
	
	/**
	 * Instantiates a new round info.
	 *
	 * @param squad the squad
	 * @param roundNr the round nr
	 */
	public RoundInfo(Squad squad, int roundNr) {
		this.squad = squad;
		this.roundNr = roundNr;
		
	}

	public Squad getSquad() {
		return squad;
	}

	public int getRoundNr() {
		return roundNr;
	}

}
