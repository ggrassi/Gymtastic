package ch.hsr.gymtastic.server.application.controller.persistence;

import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.domain.Mark;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.technicalServices.database.DBConnection;

/**
 * The Class DBController manages the persistance of the entire Cup
 */
public final class DBController {

	private static DBConnection dbConnection;

	private DBController() {
	}

	/**
	 * Import an gym cup to the db.
	 * 
	 * @param gymCup
	 *            the gym cup
	 */
	public static void importGymCupToDB(GymCup gymCup) {
		dbConnection = new DBConnection();
		dbConnection.persist(gymCup);
		commitAndClose();
	}

	/**
	 * Imports all squads to a cup
	 * 
	 * @param gymCup
	 *            the gym cup
	 */
	public static void importAllSquads(GymCup gymCup) {
		dbConnection = new DBConnection();
		TypedQuery<Squad> query = dbConnection.getEm().createQuery(
				"SELECT p FROM Squad p", Squad.class);
		List<Squad> results = query.getResultList();
		addSquadsToCup(gymCup, results);
		commitAndClose();
	}

	private static void addSquadsToCup(GymCup gymCup, List<Squad> results) {
		GymCup tmpCup;
		Squad tmpSquad;
		for (Squad squad : results) {
			tmpCup = dbConnection.getEm().find(GymCup.class, gymCup.getId());
			tmpSquad = dbConnection.getEm().find(Squad.class, squad.getId());
			tmpCup.addSquad(tmpSquad.getSquadId(), tmpSquad);
			tmpCup.addSquadUnallocated(tmpSquad);
			dbConnection.persist(tmpCup);
			gymCup.addSquad(squad.getId(), squad);
			gymCup.addSquadUnallocated(squad);
		}
	}

	/**
	 * Sets the path of the DB Location
	 * 
	 * @param path
	 *            the new path
	 */
	public static void setPath(String path) {
		DBConnection.setPath(path);
	}

	/**
	 * Saves an received squad to the db
	 * 
	 * @param s
	 *            the s
	 * @param deviceType
	 *            the device type
	 */
	public static void saveReceivedSquad(Squad s, DeviceType deviceType) {
		dbConnection = new DBConnection();
		Collection<Athlete> ramAthletes = s.getAthlets();
		for (Athlete ramAthlete : ramAthletes) {
			Athlete dbAthlete = dbConnection.getEm().find(Athlete.class,
					ramAthlete.getStartNr());
			Mark ramMark = ramAthlete.getMarks().get(deviceType);
			dbConnection.persist(ramMark);
			dbAthlete.addMark(deviceType, ramMark);
			dbConnection.persist(dbAthlete);
		}
		commitAndClose();

	}

	/**
	 * Persist competition.
	 * 
	 * @param competition
	 *            the competition
	 */
	public static void saveCompetition(Competition competition) {
		dbConnection = new DBConnection();
		Competition dbComp = dbConnection.getEm().find(Competition.class,
				competition.getId());
		for (Squad s : competition.getSquads()) {
			Squad dbSquad = dbConnection.getEm().find(Squad.class, s.getId());
			dbComp.addSquad(dbSquad);
		}
		dbConnection.persist(dbComp);
		commitAndClose();

	}

	/**
	 * Update the athlete informations
	 * 
	 * @param tmpAthlete
	 *            the tmp athlete
	 */
	public static void updateAthlete(Athlete tmpAthlete) {
		dbConnection = new DBConnection();
		Athlete dbAthlete = dbConnection.getEm().find(Athlete.class,
				tmpAthlete.getId());
		dbAthlete.setAddress(tmpAthlete.getAddress());
		dbAthlete.setFirstName(tmpAthlete.getFirstName());
		dbAthlete.setLastName(tmpAthlete.getLastName());
		dbAthlete.setPrgClass(tmpAthlete.getPrgClass());
		dbAthlete.setStartNr(tmpAthlete.getStartNr());
		dbAthlete.setYearOfBirth(tmpAthlete.getYearOfBirth());
		dbAthlete.setAssociation(tmpAthlete.getAssociation());
		commitAndClose();
	}

	/**
	 * Adds the prg class to gym cup.
	 * 
	 * @param gymCup
	 *            the gym cup
	 * @param athlete
	 *            the athlete
	 */
	public static void addPrgClassToGymCup(GymCup gymCup, Athlete athlete) {
		dbConnection = new DBConnection();
		GymCup dbGymCup = dbConnection.getEm().find(GymCup.class,
				gymCup.getId());
		dbGymCup.addProgramClass(athlete.getPrgClass());
		commitAndClose();
	}

	/**
	 * Update the competition informations
	 * 
	 * @param newComp
	 *            the new comp
	 * @param oldComp
	 *            the old comp
	 */
	public static void updateCompetition(Competition newComp,
			Competition oldComp) {
		dbConnection = new DBConnection();
		Competition dbComp = dbConnection.getEm().find(Competition.class,
				oldComp.getId());
		dbComp.setDescription(newComp.getDescription());
		dbComp.setDate(newComp.getDate());
		dbComp.setEndTime(newComp.getEndTime());
		dbComp.setStartTime(newComp.getStartTime());
		dbComp.setProgramClass(newComp.getProgramClass());
		commitAndClose();

	}

	/**
	 * Gets the existing gym cup.
	 * 
	 * @return the existing gym cup
	 */
	public static GymCup getActualGymCup() {
		dbConnection = new DBConnection();
		GymCup gymCup = null;
		TypedQuery<GymCup> query = dbConnection.getEm().createQuery(
				"SELECT p FROM GymCup p", GymCup.class);
		List<GymCup> result = query.getResultList();
		if (result.size() == 1) {
			int first = 0;
			gymCup = result.get(first);
		}

		commitAndClose();

		return gymCup;
	}

	/**
	 * Deletes a competition from a gymcup.
	 * 
	 * @param oldComp
	 *            the old comp
	 * @param gymCup
	 *            the gym cup
	 */
	public static void deleteCompetitionFromGymCup(Competition oldComp,
			GymCup gymCup) {
		dbConnection = new DBConnection();
		Competition dbComp = dbConnection.getEm().find(Competition.class,
				oldComp.getId());
		GymCup dbGymCup = dbConnection.getEm().find(GymCup.class,
				gymCup.getId());
		
		dbGymCup.getCompetitions().remove(dbComp);
		dbConnection.remove(dbComp);
		commitAndClose();

	}

	/**
	 * Removes the squad from competition.
	 * 
	 * @param comp
	 *            the comp
	 * @param squad
	 *            the squad
	 */
	public static void removeSquadFromCompetition(Competition comp, Squad squad) {
		dbConnection = new DBConnection();
		Competition dbComp = dbConnection.getEm().find(Competition.class,
				comp.getId());
		Squad dbSquad = dbConnection.getEm().find(Squad.class, squad.getId());
		dbComp.removeSquad(dbSquad);
		commitAndClose();
	}

	/**
	 * Adds a competition to the gymcup.
	 * 
	 * @param competition
	 *            the competition
	 * @param gymCup
	 *            the gym cup
	 */
	public static void addCompetitionToGymCup(Competition competition,
			GymCup gymCup) {
		dbConnection = new DBConnection();
		GymCup tmpCup = dbConnection.getEm().find(GymCup.class, gymCup.getId());
		dbConnection.persist(competition);
		tmpCup.addCompetition(competition);
		dbConnection.persist(tmpCup);
		commitAndClose();

	}


	public static void updatedMark(Mark mark) {
		dbConnection = new DBConnection();
		Mark dbMark = dbConnection.getEm().find(Mark.class, mark.getId());
		dbMark.setdMark(mark.getdMark());
		commitAndClose();
	}

	private static void commitAndClose() {
		dbConnection.commit();
		dbConnection.closeConnection();
	}

}
