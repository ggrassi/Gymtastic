package ch.hsr.gymtastic.server.application.controller.cupmanagement;

import java.net.ConnectException;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.CompetitionInfo;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.domain.RoundInfo;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.server.application.controller.persistence.DBController;
import ch.hsr.gymtastic.technicalServices.network.ClientInformation;

/**
 * The Class CompetitionController holds all the informations of the actual
 * competition for the server and the state of all the clients.
 */
public class CompetitionController extends Observable implements Observer {

	private RoundAllocator roundAllocator;
	private Competition competition;
	private NetworkServerController networkController;
	private ClientAllocator clientAllocator;
	private int actualRoundNr;
	private Set<DeviceType> finishedClients;
	private GymCup gymCup;

	/**
	 * Instantiates a new competition controller.
	 * 
	 * @param networkController
	 *            the network controller
	 */
	public CompetitionController(NetworkServerController networkController) {
		this.networkController = networkController;
		this.networkController.addObserver(this);
		this.clientAllocator = this.networkController.getClientAllocater();
		finishedClients = new HashSet<DeviceType>();
	}

	/**
	 * Gets the round allocator
	 * 
	 * @return the round allocator
	 */
	public RoundAllocator getRoundAllocator() {
		return roundAllocator;
	}

	/**
	 * Sets the competition.
	 * 
	 * @param competition
	 *            the new competition
	 */
	public void setCompetition(Competition competition) {
		this.competition = competition;
		roundAllocator = new RoundAllocator(this.competition.getSquads());
	}

	/**
	 * Gets the actual competition.
	 * 
	 * @return the actual competition
	 */
	public Competition getActualCompetition() {
		return competition;
	}

	/**
	 * Notify the clients because the competition started
	 * 
	 * @throws ConnectException
	 *             the connect exception
	 */
	public void notifyClientsCompetitionStarted() throws ConnectException {
		CompetitionInfo competitionInfo = new CompetitionInfo(competition
				.getDescription());
		networkController.sendObjectToAllClients(competitionInfo);
	}

	/**
	 * Enables a certain round number
	 * 
	 * @param roundNr
	 *            the round nr
	 * @throws ConnectException
	 *             the connect exception
	 */
	public void enableRound(Integer roundNr) throws ConnectException {
		setActualRoundNr(roundNr);
		for (DeviceType deviceType : DeviceType.values()) {
			ClientInformation clientInformation = clientAllocator
					.getClientInformation(deviceType);
			if (clientInformation != null) {
				networkController.sendObjectToClient(clientInformation
						.getStub(), getRoundInfoFor(deviceType));
			}

		}

	}

	/**
	 * Gets the round information for a certain DeviceType
	 * 
	 * @param deviceType
	 *            the device type
	 * @return the round info for
	 */
	private RoundInfo getRoundInfoFor(DeviceType deviceType) {
		return new RoundInfo(
				roundAllocator.getSquad(deviceType, actualRoundNr),
				actualRoundNr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object obj) {
		if (obj != null) {
			Squad s = (Squad) obj;
			updateSquad(s);
		}
	}

	/**
	 * Update squad.
	 * 
	 * @param squad
	 *            the squad
	 */
	private void updateSquad(Squad squad) {
		gymCup.updateSquad(squad);
		DeviceType deviceType = roundAllocator.getDeviceType(squad,
				actualRoundNr);
		DBController.saveReceivedSquad(squad, deviceType);
		setDeviceTypeFinished(squad);
		updateObservers();
	}

	/**
	 * Sets the device type finished.
	 * 
	 * @param squad
	 *            the new device type finished
	 */
	private void setDeviceTypeFinished(Squad squad) {
		finishedClients.add(roundAllocator.getDeviceType(squad, actualRoundNr));
		updateObservers();
	}

	/**
	 * Update observers.
	 */
	private void updateObservers() {
		setChanged();
		notifyObservers();
	}

	/**
	 * Sets the actual round nr.
	 * 
	 * @param roundNr
	 *            the new actual round nr
	 */
	public void setActualRoundNr(Integer roundNr) {
		actualRoundNr = roundNr;
		finishedClients.clear();
		updateObservers();
	}

	/**
	 * Gets the actual round nr.
	 * 
	 * @return the actual round nr
	 */
	public int getActualRoundNr() {
		return actualRoundNr;
	}

	/**
	 * Gets the finished clients.
	 * 
	 * @return the finished clients
	 */
	public Set<DeviceType> getFinishedClients() {
		return finishedClients;
	}

	/**
	 * Sets the gym cup.
	 * 
	 * @param gymCup
	 *            the new gym cup
	 */
	public void setGymCup(GymCup gymCup) {
		this.gymCup = gymCup;
	}

}
