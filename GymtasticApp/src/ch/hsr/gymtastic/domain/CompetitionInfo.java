package ch.hsr.gymtastic.domain;

import java.io.Serializable;

/**
 * The Class CompetitionInfo holds the Name of the Competition
 */
public class CompetitionInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String competitionName;

	/**
	 * Instantiates a new competition info.
	 * 
	 * @param name
	 *            the name
	 */
	public CompetitionInfo(String name) {
		this.competitionName = name;
	}

	public String getCompetitionName() {
		return competitionName;
	}
	
	public void setCompetition(String name) {
		this.competitionName = name;
	}

}
