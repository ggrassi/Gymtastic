package ch.hsr.gymtastic.domain;

import java.io.Serializable;

public class CompetitionInfo implements Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private String competitionName;

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
