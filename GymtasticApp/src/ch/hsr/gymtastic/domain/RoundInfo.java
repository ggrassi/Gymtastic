package ch.hsr.gymtastic.domain;

import java.io.Serializable;

public class RoundInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Squad squad;
	private int roundNr;
	
	
	public RoundInfo(Squad squad, int roundNr){
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
