package ch.hsr.gymtastic.server.application.controller;

import java.util.List;

import javax.persistence.TypedQuery;

import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.technicalServices.database.DBConnection;

public class DBController {
	private static DBConnection dbConnection;
	
	public static void importGymCupToDB(GymCup gymCup){
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
	public static void setPath(String path){
		DBConnection.setPath(path);
	}

}
