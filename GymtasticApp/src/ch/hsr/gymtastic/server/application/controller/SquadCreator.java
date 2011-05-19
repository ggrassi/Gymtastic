package ch.hsr.gymtastic.server.application.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import ch.hsr.gymtastic.domain.Association;
import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Mark;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.technicalServices.database.DBConnection;
import ch.hsr.gymtastic.technicalServices.utils.ImportStartList;

public class SquadCreator {

	private static final int associationPlacePositionImport = 8;
	private static final int associationNamePositionImport = 7;
	private static final int yearPositionImport = 6;
	private static final int addressPositionImport = 5;
	private static final int lastNamePositionImport = 4;
	private static final int firstNamePositionImport = 3;
	private static final int progClassPositionImport = 2;
	private static final int startNrPositionImport = 1;
	private static final int squadPositionImport = 0;

	private ImportStartList startList;
	private DBConnection db;

	public SquadCreator(ImportStartList importStartList) {
		this.startList = importStartList;
	}

	public Map<Integer, Squad> createSquads() {
		List<List<String>> importList = startList.getImportList();

		Set<Integer> squadsNrList = findSquadNumbers(importList);

		Map<Integer, Squad> squadMap = new TreeMap<Integer, Squad>();
		for (Integer squadNr : squadsNrList) {
			squadMap.put(squadNr, new Squad(squadNr));
		}

		
	for (List<String> line : importList) {
	    Athlete tmpAthlete = new Athlete(Integer.parseInt(line.get(squadPositionImport)), Integer.parseInt(line
		    .get(startNrPositionImport)), line.get(progClassPositionImport), line.get(firstNamePositionImport),
		    line.get(lastNamePositionImport), line.get(addressPositionImport), Integer.parseInt(line
			    .get(yearPositionImport)), new Association(line.get(associationNamePositionImport), line
			    .get(associationPlacePositionImport)));

	    Mark mark = new Mark(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
	    for (DeviceType dt : DeviceType.values()) {
		tmpAthlete.addMark(dt, mark);
	    }
	    squadMap.get(Integer.parseInt(line.get(squadPositionImport))).addAthlet(tmpAthlete);
	}


		return squadMap;

	}

	private Set<Integer> findSquadNumbers(List<List<String>> importList) {
		Set<Integer> squadsNrList = new TreeSet<Integer>();
		for (List<String> line : importList) {
			squadsNrList.add(Integer.parseInt(line.get(squadPositionImport)));
		}
		return squadsNrList;
	}

	public void insertImportToDB() {
		db = new DBConnection();
		List<List<String>> importList = startList.getImportList();
		Set<Integer> squadsNrList = findSquadNumbers(importList);
		for (Integer squadNr : squadsNrList) {
			Squad tempSquad = new Squad(squadNr);
			db.persist(tempSquad);
		}
		db.commit();
		db.closeConnection();
		db = new DBConnection();
		for (List<String> line : importList) {
			Squad temp = new Squad(Integer.parseInt(line
					.get(squadPositionImport)));
			temp = db.getEm().find(Squad.class, temp.getSquadId());

			Athlete atemp = new Athlete(Integer.parseInt(line
					.get(squadPositionImport)), Integer.parseInt(line
					.get(startNrPositionImport)),
					line.get(progClassPositionImport),
					line.get(firstNamePositionImport),
					line.get(lastNamePositionImport),
					line.get(addressPositionImport), Integer.parseInt(line
							.get(yearPositionImport)), new Association(
							line.get(associationNamePositionImport),
							line.get(associationPlacePositionImport)));
			db.persist(atemp);

			Mark mtemp = new Mark(0, 0, 0, 0, 0, 0);
			db.persist(mtemp);
			for (DeviceType dt : DeviceType.values()) {
				atemp.addMark(dt, mtemp);
			}
			db.persist(atemp);

			temp.addAthlet(atemp);
			db.persist(temp);

		}
		db.commit();

		db.closeConnection();
	}

}
