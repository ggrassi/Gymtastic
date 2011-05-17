package ch.hsr.gymtastic.server.application.controller;

import java.net.ConnectException;
import java.util.Observable;
import java.util.Observer;

import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.CompetitionInfo;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.domain.RoundInfo;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.technicalServices.network.ClientInformation;

public class CompetitionController implements Observer {
	private RoundAllocator roundAllocator;
	private Competition competition;
	private NetworkServerController networkController;
	private ClientAllocator clientAllocator;
	private GymCup gymCup;
	private int actualRoundNr;

	public CompetitionController(NetworkServerController networkController,
			GymCup gymCup) {
		this.networkController = networkController;
		this.networkController.addObserver(this);
		this.clientAllocator = this.networkController.getClientAllocater();
	}

	public RoundAllocator getRoundAllocator() {
		return roundAllocator;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
		roundAllocator = new RoundAllocator(this.competition.getSquads());
	}

	public Competition getCompetition() {
		return competition;
	}

	public void notifyClientsCompetitionStarted() {
		CompetitionInfo competitionInfo = new CompetitionInfo(
				competition.getDescription());
		try {
			networkController.sendCompetitionInfoToClients(competitionInfo);
		} catch (ConnectException e) {
			e.printStackTrace();
		}
	}

	public void enableRound(Integer roundNr) {
		setActualRoundNr(roundNr);
		for (DeviceType deviceType : DeviceType.values()) {
			RoundInfo roundInfo = new RoundInfo(roundAllocator.getSquad(
					deviceType, actualRoundNr), actualRoundNr);
			ClientInformation clientInformation = clientAllocator
					.getClientInformation(deviceType);
			if (clientInformation != null) {
				try {
					networkController.sendRoundInfoToClient(
							clientInformation.getStub(), roundInfo);
				} catch (ConnectException e) {
					e.printStackTrace();
				}
			}

		}

	}

	@Override
	public void update(Observable o, Object obj) {
		if (obj != null) {
			Squad s = (Squad) obj;
<<<<<<< HEAD
			getCompetition().updateSquad(s);
			DBController.saveSquad(s, DeviceType.FLOOR_EXCERCISE);
=======
			updateSquad(s);
>>>>>>> 5f9b391df3f6439ef0cf6234b7d21e9e2534b148
		}

	}

	private void updateSquad(Squad squad) {
		System.out.println("Squad received!");
		getCompetition().updateSquad(squad);
		DeviceType deviceType = roundAllocator.getDeviceType(squad, actualRoundNr);
		DBController.saveSquad(squad, gymCup);
	}

	public void setActualRoundNr(Integer roundNr) {
		actualRoundNr = roundNr;
	}

	public int getActualRoundNr() {
		return actualRoundNr;
	}

}
