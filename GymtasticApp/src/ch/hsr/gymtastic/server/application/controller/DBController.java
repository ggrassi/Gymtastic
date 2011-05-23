package ch.hsr.gymtastic.server.application.controller;

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

public class DBController {
	private static DBConnection dbConnection;

	public static void importGymCupToDB(GymCup gymCup) {
		dbConnection = new DBConnection();
		dbConnection.persist(gymCup);
		dbConnection.commit();
		dbConnection.closeConnection();
	}

	public static void importAllSquads(GymCup gymCup) {
		dbConnection = new DBConnection();
		TypedQuery<Squad> query = dbConnection.getEm().createQuery(
				"SELECT p FROM Squad p", Squad.class);
		List<Squad> results = query.getResultList();
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

		dbConnection.commit();
		dbConnection.closeConnection();

	}

	public static void setPath(String path) {
		DBConnection.setPath(path);
	}

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
		dbConnection.commit();
		dbConnection.closeConnection();

	}

	public static void updateCompetition(Competition competition) {
		dbConnection = new DBConnection();
		Competition dbComp = dbConnection.getEm().find(Competition.class,
				competition.getId());
		for (Squad s : competition.getSquads()) {
			Squad dbSquad = dbConnection.getEm().find(Squad.class, s.getId());
			dbComp.addSquad(dbSquad);
			System.out.println("Squad in Wettkampf hinzugefügt [DBController]");
		}
		dbConnection.persist(dbComp);
		dbConnection.commit();
		dbConnection.closeConnection();

	}

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
		dbConnection.commit();
		dbConnection.closeConnection();
	}

	public static void addPrgClassToGymCup(GymCup gymCup, Athlete athlete) {
		dbConnection = new DBConnection();
		GymCup dbGymCup = dbConnection.getEm().find(GymCup.class,
				gymCup.getId());
		dbGymCup.addProgramClass(athlete.getPrgClass());
		dbConnection.commit();
		dbConnection.closeConnection();
	}

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
		dbConnection.commit();
		dbConnection.closeConnection();

	}

	public static GymCup getExistingGymCup() {
		dbConnection = new DBConnection();
		GymCup gymCup = null;
		TypedQuery<GymCup> query = dbConnection.getEm().createQuery(
				"SELECT p FROM GymCup p", GymCup.class);
		List<GymCup> result = query.getResultList();
		if (result.size() == 1) {
			int first = 0;
			gymCup = result.get(first);
		}

		dbConnection.commit();
		dbConnection.closeConnection();

		return gymCup;
	}

	public static void deleteCompetitionFromGymCup(Competition oldComp,
			GymCup gymCup) {
		dbConnection = new DBConnection();
		GymCup dbGymCup = dbConnection.getEm().find(GymCup.class,
				gymCup.getId());
		dbGymCup.getCompetitions().remove(oldComp);
		// dbConnection.remove(oldComp);
		dbConnection.commit();
		dbConnection.closeConnection();

	}

	public static void addCompetitionToGymCup(Competition competition,
			GymCup gymCup) {
		dbConnection = new DBConnection();
		GymCup tmpCup = dbConnection.getEm().find(GymCup.class, gymCup.getId());
		dbConnection.persist(competition);
		tmpCup.addCompetition(competition);
		dbConnection.persist(tmpCup);
		dbConnection.commit();
		dbConnection.closeConnection();

	}

	public static void removeSquadFromCompetition(Competition comp, Squad squad) {
		dbConnection = new DBConnection();
		Competition dbComp = dbConnection.getEm().find(Competition.class,
				comp.getId());
		Squad dbSquad = dbConnection.getEm().find(Squad.class, squad.getId());
		dbComp.removeSquad(dbSquad);
		dbConnection.commit();
		dbConnection.closeConnection();

	}

}
