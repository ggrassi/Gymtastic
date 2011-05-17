package ch.hsr.gymtastic.server.application.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import ch.hsr.gymtastic.domain.Athlete;
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

	public static void saveSquad(Squad s, DeviceType deviceType) {
		
		dbConnection = new DBConnection();
		Collection<Athlete> ramAthletes = s.getAthlets();
		for (Athlete ramAthlete : ramAthletes) {
			Athlete dbAthlete = dbConnection.getEm().find(Athlete.class,
					ramAthlete.getId());
			Map<DeviceType, Mark> ramMarks = ramAthlete.getMarks();
			for (int i = 0; i < ramMarks.size(); i++) {
				Mark ramMark = ramMarks.get(deviceType);
				dbConnection.persist(ramMark);
				dbAthlete.getMarks().put(deviceType, ramMark);
				// Todo: pull pfiffners zeug und mach dasses lauft
//				for (DeviceType deviceType : DeviceType.values()) {
//					Mark mark = ramMarks.get(deviceType);
//					
//					dbConnection.persist(mark);
//					
//					dbAthlete.getMarks().put(deviceType, mark);
//
//				}
			}	
			dbConnection.persist(dbAthlete);
		}
		dbConnection.commit();
		dbConnection.closeConnection();
	}

}
