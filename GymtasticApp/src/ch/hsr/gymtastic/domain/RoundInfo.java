package ch.hsr.gymtastic.domain;

import java.io.Serializable;

public class RoundInfo implements Serializable {
    private int round;
    private boolean competitionIsRunning;
    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public boolean isCompetitionIsRunning() {
        return competitionIsRunning;
    }

    public void setCompetitionIsRunning(boolean competitionIsRunning) {
        this.competitionIsRunning = competitionIsRunning;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    private String competition;
    
    public RoundInfo(int round, boolean competitionIsRunning, String competition){
	this.round = round;
	this.competitionIsRunning = competitionIsRunning;
	this.competition = competition;
    }
    
    
}
