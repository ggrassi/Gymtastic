package ch.hsr.gymtastic.application.controller.server;

import java.net.ConnectException;

import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.CompetitionInfo;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.RoundInfo;
import ch.hsr.gymtastic.technicalServices.network.ClientInformation;

public class CompetitionController {
	private RoundAllocator roundAllocator;
	private Competition competition;
	private NetworkServerController networkController;
	private ClientAllocator clientAllocator;

	public CompetitionController(NetworkServerController networkController) {
		this.networkController = networkController;
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
		for (DeviceType deviceType : DeviceType.values()) {
			RoundInfo roundInfo = new RoundInfo(roundAllocator.getSquad(
					deviceType, roundNr), roundNr);
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

}
