package ch.hsr.gymtastic.application.controller;

import ch.hsr.gymtastic.domain.GymCupClientInfo;

public class GymCupInfoController {
    private GymCupClientInfo gymCupInfo;

    public void setGymCupInfo(Object arg) {
	this.gymCupInfo = (GymCupClientInfo) arg;
    }

}
