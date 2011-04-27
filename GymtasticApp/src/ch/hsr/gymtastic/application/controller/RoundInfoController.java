package ch.hsr.gymtastic.application.controller;

import ch.hsr.gymtastic.domain.RoundInfo;

public class RoundInfoController {
    private RoundInfo roundInfo;

    public void setRoundInfo(Object arg) {
	this.roundInfo = (RoundInfo) arg;
    }

}
